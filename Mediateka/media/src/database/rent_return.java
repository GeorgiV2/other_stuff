package database;

import GUI.Error;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Calendar;

public class rent_return {

    static JLabel lname,lbarcode;
    static JTextField name,barcode;
    static JButton submit;
    static Font font = new Font("Nanum Gothic", Font.BOLD, 30);
    static Connection cn = null;
    static PreparedStatement st = null;


    public static void rent_returnMedia(JFrame frame, JPanel panel,boolean index) {

        name = new JTextField();
        barcode = new JTextField();
        lname = new JLabel("Name of the user :");
        lbarcode = new JLabel("Barcode :");
        submit = new JButton("Submit");

        submit.setBorderPainted(false);
        submit.setFocusPainted(false);

        name.setFont(font);
        barcode.setFont(font);
        lname.setFont(font);
        lbarcode.setFont(font);
        submit.setFont(font);

        lname.setForeground(new Color(255, 255, 255));
        lbarcode.setForeground(new Color(255, 255, 255));

        panel.setLayout(new GridLayout(5, 1, 20, 10));



        panel.add(lname);
        panel.add(name);
        panel.add(lbarcode);
        panel.add(barcode);
        panel.add(submit);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

        //making some JTextFields that they can only accept numbers!!
        //Copied from https://www.tutorialspoint.com/how-can-we-make-jtextfield-accept-only-numbers-in-java
        //================================================================================
        barcode.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {

                if(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    barcode.setEditable(true);
                } else {
                    barcode.setEditable(false);
                    lbarcode.setText("* Enter only numeric digits(0-9)");
                }
            }
        });

        submit.addActionListener(e -> {
            int idU = 0;
            int idM = 0;
            String nameU = name.getText();
            String barcodeM = barcode.getText();

            if(!index){
                try {
                    cn = db.getConnection();
                    if(db.exists("users","name",nameU)){
                        st=cn.prepareStatement("SELECT idusers FROM mediateka.users WHERE name = '"+nameU+"';");
                        ResultSet rs = st.executeQuery();
                        rs.next();
                        idU = rs.getInt(1);
                    }else{
                        Error.errMessage("Wrong name !!");
                        frame.dispose();
                    }

                    if(db.exists("mediadetails","barcode",barcodeM)){
                        st=cn.prepareStatement("SELECT idmedia FROM mediateka.mediadetails WHERE barcode = '"+barcodeM+"';");
                        ResultSet rs = st.executeQuery();
                        rs.next();
                        idM = rs.getInt(1);
                    }else{
                        Error.errMessage("Wrong barcode!!");
                        frame.dispose();
                    }

                    if(db.exists("rent","id_users",idU) && db.exists("rent","id_media",idM)) {
                        Date endDate = new Date(Calendar.getInstance().getTime().getTime());
                        st = cn.prepareStatement("UPDATE mediateka.rent SET end_date = '"+endDate+"' "
                                + " WHERE id_media = '" + idM + "' AND id_users = '" + idU + "';");
                        st.executeUpdate();
                        barcode.setText("");
                        name.setText("");
                    }

                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }else{
                try {
                    cn = db.getConnection();
                    if(db.exists("users","name",nameU)){
                        st=cn.prepareStatement("SELECT idusers FROM mediateka.users WHERE name = '"+nameU+"';");
                        ResultSet rs = st.executeQuery();
                        rs.next();
                        idU = rs.getInt(1);
                    }else{
                        Error.errMessage("Wrong name !!");
                        frame.dispose();
                    }

                    if(db.exists("mediadetails","barcode",barcodeM)){
                        st=cn.prepareStatement("SELECT idmedia FROM mediateka.mediadetails WHERE barcode = '"+barcodeM+"';");
                        ResultSet rs = st.executeQuery();
                        rs.next();
                        idM = rs.getInt(1);
                    }else{
                        Error.errMessage("Wrong barcode !!");
                        frame.dispose();
                    }

                    if(!db.exists("rent","id_users",idU) && !db.exists("rent","id_media",idM)) {
                        Date startDate = new Date(Calendar.getInstance().getTime().getTime());
                        st = cn.prepareStatement("INSERT INTO mediateka.rent(`id_media`,`id_users`,`start_date`) "
                                + "VALUES('" + idM + "','" + idU + "','" + startDate + "');");
                        st.executeUpdate();
                        barcode.setText("");
                        name.setText("");
                    }

                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }


}
