package database;

import GUI.Error;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class addUser {
    static JTextField username, egn, gsm, description, adress;
    static JButton submit;
    static JLabel lusername, legn, lgsm, ldescription, ladress;
    static Font font = new Font("Nanum Gothic", Font.BOLD, 30);
    static Connection cn = null;
    static Statement st = null;

    public static void AddUsers(Frame frame, JPanel panel) {

        frame.setSize(400, 800);

        //declaring the button,labels and textfields
        username = new JTextField();
        egn = new JTextField();
        gsm = new JTextField();
        description = new JTextField();
        adress = new JTextField();
        submit = new JButton("Submit");
        lusername = new JLabel("Name :");
        legn = new JLabel("EGN :");
        lgsm = new JLabel("Phone Number :");
        ldescription = new JLabel("Description :");
        ladress = new JLabel("Adress :");

        //Adding font
        username.setFont(font);
        egn.setFont(font);
        gsm.setFont(font);
        description.setFont(font);
        adress.setFont(font);
        submit.setFont(font);
        lusername.setFont(font);
        legn.setFont(font);
        lgsm.setFont(font);
        ldescription.setFont(font);
        ladress.setFont(font);

        //labels Foreground
        lusername.setForeground(new Color(255, 255, 255));
        legn.setForeground(new Color(255, 255, 255));
        lgsm.setForeground(new Color(255, 255, 255));
        ldescription.setForeground(new Color(255, 255, 255));
        ladress.setForeground(new Color(255, 255, 255));

        //Submit button design
        submit.setBorderPainted(false);
        submit.setFocusPainted(false);
        submit.setBackground(new Color(0, 119, 182));


        //panel's grid
        panel.setLayout(new GridLayout(11, 1, 20, 10));
        panel.setBounds(50, 50, 285, 655);

        panel.add(lusername);
        panel.add(username);
        panel.add(legn);
        panel.add(egn);
        panel.add(lgsm);
        panel.add(gsm);
        panel.add(ladress);
        panel.add(adress);
        panel.add(ldescription);
        panel.add(description);
        panel.add(submit);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

        //Setting new Action listener because the method is static
        submit.addActionListener(e -> {

            try {
                //adding connection to the database
                cn = db.getConnection();
                st = cn.createStatement();


                //Declaring new Strings with the value of each JTextField
                String name = username.getText();
                String EGN = egn.getText();
                String number = gsm.getText();
                String Description = description.getText();
                String Adress = adress.getText();

                //Checks if there is another user with this name in the database
                if(EGN.isEmpty() && number.isEmpty()  && Adress.isEmpty() && name.isEmpty()){
                    Error.errMessage("Please fill each textfield!!");
                    frame.dispose();
                }else{
                    if(!db.exists("users", "name", name)){

                        String user = "INSERT INTO mediateka.users(`name`) " +
                                "VALUES('" + name + "');";
                        db.st = db.cn.prepareStatement(user);
                        db.st.executeUpdate();

                        //We are getting the id of the user we just added to our database and now we use it to add the other credentials
                        ResultSet rset = db.st.executeQuery("SELECT idusers FROM mediateka.users WHERE name = '" + name + "';");
                        rset.next();
                        int id = rset.getInt(1);
                        String userdetails = "INSERT INTO mediateka.userdetails(iduserdetails,egn,gsm,adress,description) " +
                                "VALUES('"+ id +"','"+ EGN +"','"+ number +"','" + Description + "','"+Adress+"');";

                        PreparedStatement pst = db.cn.prepareStatement(userdetails);
                        pst.executeUpdate();

                    }else{
                        username.setText("Name is already used");
                        egn.setText("");
                        gsm.setText("");
                        description.setText("");
                        adress.setText("");
                    }
                    //We clear the values of each TextLabel after we press the submit button
                    username.setText("");
                    egn.setText("");
                    gsm.setText("");
                    description.setText("");
                    adress.setText("");
                }


            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }


        });


    }
}

