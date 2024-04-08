package database;
import GUI.Error;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class searchByUser {

    static JLabel label;
    static JTextField text;
    static JButton submit;
    static Font font = new Font("Nanum Gothic", Font.BOLD, 30);
    static Connection cn = null;
    static Statement st = null;


    public static void searchUser(JFrame frame, JPanel panel, String search){

        text = new JTextField();
        submit = new JButton("Submit");
        label = new JLabel("Enter  " + search + " :");

        label.setFont(font);
        text.setFont(font);
        submit.setFont(font);

        submit.setBorderPainted(false);
        submit.setFocusPainted(false);

        label.setForeground(new Color(255, 255, 255));
        panel.setLayout(new GridLayout(3, 1, 20, 10));

        panel.add(label);
        panel.add(text);
        panel.add(submit);
        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);

        submit.addActionListener(e -> {
            String index = text.getText();
            panel.removeAll();
            frame.repaint();
            frame.revalidate();
            try {
                if(search.equalsIgnoreCase("name")) {
                    searchByName(frame, panel,index);
                }else if(search.equalsIgnoreCase("id")) {
                    searchByID(frame, panel, index);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });

    }



    private static void searchByID(JFrame frame, JPanel panel, String id2) throws SQLException, ClassNotFoundException {
        cn =  db.getConnection();
        st = cn.createStatement();
        JTextField username, egn, gsm, description, adress;
        JButton submit;
        JLabel lusername, legn, lgsm, ldescription, ladress;
        int id = Integer.parseInt(id2);

        if(db.exists("users","idusers",id) ){

            ResultSet rset = db.st.executeQuery( "SELECT name FROM mediateka.users WHERE idusers = " + id + ";");
            rset.next();
            String realname = rset.getString("name");
            ResultSet rset2 = db.st.executeQuery( "SELECT * FROM mediateka.userdetails WHERE iduserdetails = '" +id+ "';");
            rset2.next();

            frame.setSize(400, 800);



            //declaring the button,labels and textfields
            username = new JTextField(realname);
            egn = new JTextField(rset2.getString("egn"));
            gsm = new JTextField(rset2.getString("gsm"));
            description = new JTextField(rset2.getString("description"));
            adress = new JTextField(rset2.getString("adress"));
            submit = new JButton("Submit");
            lusername = new JLabel("ID :");
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


                    if(EGN.isEmpty() && number.isEmpty()  && Adress.isEmpty() && name.isEmpty()){
                        frame.dispose();
                    }else{
                        if(!realname.equalsIgnoreCase(name)){
                            String update2 = "UPDATE users SET name = '"+name+"';";
                            st.executeUpdate(update2);
                        }
                        String update = "UPDATE userdetails SET egn = '" +EGN + "', gsm = '"+number+"',"+
                                " description = '"+Description+"', adress = '"+ Adress+"'" +
                                "WHERE iduserdetails = '"+id+"';";
                        st.executeUpdate(update);
                        frame.dispose();

                    }


                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }


            });
        }else{
            frame.dispose();
        }
    }


    private static void searchByName(JFrame frame, JPanel panel, String name1) throws SQLException, ClassNotFoundException {
        cn =  db.getConnection();
        st = cn.createStatement();
        JTextField username, egn, gsm, description, adress;
        JButton submit;
        JLabel lusername, legn, lgsm, ldescription, ladress;

        if(db.exists("users","name",name1)){

            ResultSet rset = db.st.executeQuery("SELECT idusers FROM mediateka.users WHERE name = '" + name1 + "';");
            rset.next();
            int id = rset.getInt(1);

            ResultSet rset2 = db.st.executeQuery( "SELECT * FROM mediateka.userdetails WHERE iduserdetails = '" +id+ "';");
            rset2.next();
            frame.setSize(400, 800);

            //declaring the button,labels and textfields
            username = new JTextField(name1);
            egn = new JTextField(rset2.getString("egn"));
            gsm = new JTextField(rset2.getString("gsm"));
            description = new JTextField(rset2.getString("description"));
            adress = new JTextField(rset2.getString("adress"));
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


                    if(EGN.isEmpty() && number.isEmpty()  && Adress.isEmpty() && name.isEmpty()){
                        Error.errMessage("Please fill each textfield !!");
                        frame.dispose();
                    }else{
                        if(!name1.equalsIgnoreCase(name)){
                            String update2 = "UPDATE users SET name = '"+name+"';";
                            st.executeUpdate(update2);
                        }
                        String update = "UPDATE userdetails SET egn = '" +EGN + "', gsm = '"+number+"',"+
                                " description = '"+Description+"', adress = '"+ Adress+"'" +
                                "WHERE iduserdetails = '"+id+"';";
                        st.executeUpdate(update);
                        frame.dispose();

                    }


                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }

            });
        }else{
            Error.errMessage("Wrong name !!");
          frame.dispose();
        }

    }
}
