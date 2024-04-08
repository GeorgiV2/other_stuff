package database;
import GUI.Error;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class searchByMedia {
    static JLabel label;
    static JTextField text;
    static JButton submit;
    static Font font = new Font("Nanum Gothic", Font.BOLD, 30);
    static Connection cn = null;
    static Statement st = null;


    public static void searchMedia(JFrame frame, JPanel panel,String search){

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
                if(search.equalsIgnoreCase("barcode")) {
                    searchByISBN(frame, panel,index);
                }else if(search.equalsIgnoreCase("name")){
                    searchByName(frame, panel,index);
                }else if(search.equalsIgnoreCase("author")){
                    searchByAuthor(frame, panel,index);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });

    }



    private static void searchByISBN(JFrame frame, JPanel panel, String isbn) throws SQLException, ClassNotFoundException {
        cn =  db.getConnection();
        st = cn.createStatement();
        JTextField name, author, year, genre, description,quantity;
        JButton submit;
        JLabel lname, lauthor, lyear, ldescription, lgenre,lquantity;

        if(db.exists("mediadetails","barcode",isbn)){

            ResultSet rset = db.st.executeQuery( "SELECT idmedia FROM mediateka.mediadetails WHERE barcode = " + isbn + ";");
            rset.next();
            int idmedia = Integer.parseInt(rset.getString(1));
            ResultSet rset2 = db.st.executeQuery( "SELECT * FROM mediateka.mediadetails WHERE idmedia = '" +idmedia+ "';");
            rset2.next();

            frame.setSize(400, 800);



            //declaring the button,labels and textfields
            name = new JTextField();
            author = new JTextField();
            year = new JTextField(rset2.getString("year"));
            description = new JTextField(rset2.getString("description"));
            quantity = new JTextField(rset2.getString("quantity"));
            genre = new JTextField(rset2.getString("genre"));
            submit = new JButton("Submit");
            lname = new JLabel("Title :");
            lauthor = new JLabel("Author :");
            lyear = new JLabel("Year :");
            ldescription = new JLabel("Description :");
            lgenre = new JLabel("Genre :");
            lquantity = new JLabel("Quantity :");

            //Adding font
            name.setFont(font);
            author.setFont(font);
            year.setFont(font);
            description.setFont(font);
            genre.setFont(font);
            quantity.setFont(font);
            submit.setFont(font);
            lname.setFont(font);
            lauthor.setFont(font);
            lyear.setFont(font);
            ldescription.setFont(font);
            lgenre.setFont(font);
            lquantity.setFont(font);

            //labels Foreground
            lname.setForeground(new Color(255, 255, 255));
            lauthor.setForeground(new Color(255, 255, 255));
            lyear.setForeground(new Color(255, 255, 255));
            ldescription.setForeground(new Color(255, 255, 255));
            lquantity.setForeground(new Color(255, 255, 255));
            lgenre.setForeground(new Color(255, 255, 255));

            //Submit button design
            submit.setBorderPainted(false);
            submit.setFocusPainted(false);
            submit.setBackground(new Color(0, 119, 182));

            //making some JTextFields that they can only accept numbers!!
            //Copied from https://www.tutorialspoint.com/how-can-we-make-jtextfield-accept-only-numbers-in-java
            //================================================================================
            year.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {
                    if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                        year.setEditable(true);
                        lyear.setText("");
                    } else {
                        year.setEditable(false);
                        lyear.setText("* Enter only numeric digits(0-9)");
                    }
                }
            });
            //================================================================================
            quantity.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {
                    if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'|| ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                        quantity.setEditable(true);
                        lquantity.setText("");
                    } else {
                        quantity.setEditable(false);
                        lquantity.setText("* Enter only numeric digits(0-9)");
                    }
                }
            });
            //====================================================================================



            //panel's grid
            panel.setLayout(new GridLayout(13, 1, 20, 10));
            panel.setBounds(50, 50, 285, 655);


            panel.add(lname);
            panel.add(name);
            panel.add(lauthor);
            panel.add(author);
            panel.add(lyear);
            panel.add(year);
            panel.add(lgenre);
            panel.add(genre);
            panel.add(ldescription);
            panel.add(description);
            panel.add(lquantity);
            panel.add(quantity);
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
                    String Mname = name.getText();
                    String Mauthor = author.getText();
                    String Myear = year.getText();
                    String Mdescription = description.getText();
                    String Mgenre = genre.getText();
                    String Mquantity = quantity.getText();


                    if(Mname.isEmpty() && Mauthor.isEmpty()  && Myear.isEmpty() && Mgenre.isEmpty() && Mquantity.isEmpty()){
                        Error.errMessage("Please fill each textfield!!");
                        frame.dispose();
                    }else{

                            String update2 = "UPDATE media SET name = '"+Mname+"', author = '"+Mauthor+"';";
                            st.executeUpdate(update2);

                        String update = "UPDATE mediadetails SET year = '" +Myear + "', genre = '"+Mgenre+"',"+
                                " description = '"+Mdescription+"', quantity = '"+ Mquantity+"'" +
                                "WHERE idmedia = '"+idmedia+"';";
                        st.executeUpdate(update);
                        frame.dispose();

                    }


                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }

            });
        }else{
            Error.errMessage("Invalid barcode!");
            frame.dispose();
        }
    }


    private static void searchByName(JFrame frame, JPanel panel, String name1) throws SQLException, ClassNotFoundException {
        cn =  db.getConnection();
        st = cn.createStatement();
        JTextField name, author, year, genre, description,quantity;
        JButton submit;
        JLabel lname, lauthor, lyear, ldescription, lgenre,lquantity;

        if(db.exists("media","name",name1)){

            ResultSet rset = db.st.executeQuery( "SELECT * FROM mediateka.media WHERE name = '" + name1 + "';");
            rset.next();
            String name2 =rset.getString("name");
            String aut = rset.getString("author");
            int idmedia = Integer.parseInt(rset.getString("idmedia"));
            ResultSet rset2 = db.st.executeQuery( "SELECT * FROM mediateka.mediadetails WHERE idmedia = '" +idmedia+ "';");
            rset2.next();

            frame.setSize(400, 800);



            //declaring the button,labels and textfields
            name = new JTextField(name2);
            author = new JTextField(aut);
            year = new JTextField(rset2.getString("year"));
            description = new JTextField(rset2.getString("description"));
            quantity = new JTextField(rset2.getString("quantity"));
            genre = new JTextField(rset2.getString("genre"));
            submit = new JButton("Submit");
            lname = new JLabel("Title :");
            lauthor = new JLabel("Author :");
            lyear = new JLabel("Year :");
            ldescription = new JLabel("Description :");
            lgenre = new JLabel("Genre :");
            lquantity = new JLabel("Quantity :");

            //Adding font
            name.setFont(font);
            author.setFont(font);
            year.setFont(font);
            description.setFont(font);
            genre.setFont(font);
            quantity.setFont(font);
            submit.setFont(font);
            lname.setFont(font);
            lauthor.setFont(font);
            lyear.setFont(font);
            ldescription.setFont(font);
            lgenre.setFont(font);
            lquantity.setFont(font);

            //labels Foreground
            lname.setForeground(new Color(255, 255, 255));
            lauthor.setForeground(new Color(255, 255, 255));
            lyear.setForeground(new Color(255, 255, 255));
            ldescription.setForeground(new Color(255, 255, 255));
            lquantity.setForeground(new Color(255, 255, 255));
            lgenre.setForeground(new Color(255, 255, 255));

            //Submit button design
            submit.setBorderPainted(false);
            submit.setFocusPainted(false);
            submit.setBackground(new Color(0, 119, 182));

            //making some JTextFields that they can only accept numbers!!
            //Copied from https://www.tutorialspoint.com/how-can-we-make-jtextfield-accept-only-numbers-in-java
            //================================================================================
            year.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {

                    if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                        year.setEditable(true);
                        lyear.setText("");
                    } else {
                        year.setEditable(false);
                        lyear.setText("* Enter only numeric digits(0-9)");
                    }
                }
            });
            //================================================================================
            quantity.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {

                    if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'|| ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                        quantity.setEditable(true);
                        lquantity.setText("");
                    } else {
                        quantity.setEditable(false);
                        lquantity.setText("* Enter only numeric digits(0-9)");
                    }
                }
            });
            //====================================================================================



            //panel's grid
            panel.setLayout(new GridLayout(13, 1, 20, 10));
            panel.setBounds(50, 50, 285, 655);


            panel.add(lname);
            panel.add(name);
            panel.add(lauthor);
            panel.add(author);
            panel.add(lyear);
            panel.add(year);
            panel.add(lgenre);
            panel.add(genre);
            panel.add(ldescription);
            panel.add(description);
            panel.add(lquantity);
            panel.add(quantity);
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
                    String Mname = name.getText();
                    String Mauthor = author.getText();
                    String Myear = year.getText();
                    String Mdescription = description.getText();
                    String Mgenre = genre.getText();
                    String Mquantity = quantity.getText();


                    if(Mname.isEmpty() && Mauthor.isEmpty()  && Myear.isEmpty() && Mgenre.isEmpty() && Mquantity.isEmpty()){
                        Error.errMessage("Please fill each textfield!!");
                        frame.dispose();
                    }else{

                        String update2 = "UPDATE media SET name = '"+Mname+"', author = '"+Mauthor+"';";
                        st.executeUpdate(update2);

                        String update = "UPDATE mediadetails SET year = '" +Myear + "', genre = '"+Mgenre+"',"+
                                " description = '"+Mdescription+"', quantity = '"+ Mquantity+"'" +
                                "WHERE idmedia = '"+idmedia+"';";
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
    private static void searchByAuthor(JFrame frame, JPanel panel, String author1) throws SQLException, ClassNotFoundException {
        cn =  db.getConnection();
        st = cn.createStatement();
        JTextField name, author, year, genre, description,quantity;
        JButton submit;
        JLabel lname, lauthor, lyear, ldescription, lgenre,lquantity;

        if(db.exists("media","author",author1)){

            ResultSet rset = db.st.executeQuery( "SELECT * FROM mediateka.media WHERE author = '" + author1 + "';");
            rset.next();
            String name2 =rset.getString("name");
            String aut = rset.getString("author");
            int idmedia = Integer.parseInt(rset.getString("idmedia"));
            ResultSet rset2 = db.st.executeQuery( "SELECT * FROM mediateka.mediadetails WHERE idmedia = '" +idmedia+ "';");
            rset2.next();

            frame.setSize(400, 800);



            //declaring the button,labels and textfields
            name = new JTextField(name2);
            author = new JTextField(aut);
            year = new JTextField(rset2.getString("year"));
            description = new JTextField(rset2.getString("description"));
            quantity = new JTextField(rset2.getString("quantity"));
            genre = new JTextField(rset2.getString("genre"));
            submit = new JButton("Submit");
            lname = new JLabel("Title :");
            lauthor = new JLabel("Author :");
            lyear = new JLabel("Year :");
            ldescription = new JLabel("Description :");
            lgenre = new JLabel("Genre :");
            lquantity = new JLabel("Quantity :");

            //Adding font
            name.setFont(font);
            author.setFont(font);
            year.setFont(font);
            description.setFont(font);
            genre.setFont(font);
            quantity.setFont(font);
            submit.setFont(font);
            lname.setFont(font);
            lauthor.setFont(font);
            lyear.setFont(font);
            ldescription.setFont(font);
            lgenre.setFont(font);
            lquantity.setFont(font);

            //labels Foreground
            lname.setForeground(new Color(255, 255, 255));
            lauthor.setForeground(new Color(255, 255, 255));
            lyear.setForeground(new Color(255, 255, 255));
            ldescription.setForeground(new Color(255, 255, 255));
            lquantity.setForeground(new Color(255, 255, 255));
            lgenre.setForeground(new Color(255, 255, 255));

            //Submit button design
            submit.setBorderPainted(false);
            submit.setFocusPainted(false);
            submit.setBackground(new Color(0, 119, 182));

            //making some JTextFields that they can only accept numbers!!
            //Copied from https://www.tutorialspoint.com/how-can-we-make-jtextfield-accept-only-numbers-in-java
            //================================================================================
            year.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {

                    if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                        year.setEditable(true);
                    } else {
                        year.setEditable(false);
                        lyear.setText("* Enter only numeric digits(0-9)");
                    }
                }
            });
            //================================================================================
            quantity.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {
                    if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'|| ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                        quantity.setEditable(true);
                    } else {
                        quantity.setEditable(false);
                        lquantity.setText("* Enter only numeric digits(0-9)");
                    }
                }
            });
            //====================================================================================



            //panel's grid
            panel.setLayout(new GridLayout(13, 1, 20, 10));
            panel.setBounds(50, 50, 285, 655);


            panel.add(lname);
            panel.add(name);
            panel.add(lauthor);
            panel.add(author);
            panel.add(lyear);
            panel.add(year);
            panel.add(lgenre);
            panel.add(genre);
            panel.add(ldescription);
            panel.add(description);
            panel.add(lquantity);
            panel.add(quantity);
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
                    String Mname = name.getText();
                    String Mauthor = author.getText();
                    String Myear = year.getText();
                    String Mdescription = description.getText();
                    String Mgenre = genre.getText();
                    String Mquantity = quantity.getText();


                    if(Mname.isEmpty() && Mauthor.isEmpty()  && Myear.isEmpty() && Mgenre.isEmpty() && Mquantity.isEmpty()){
                        Error.errMessage("Please fill each textfield!!");
                        frame.dispose();
                    }else{

                        String update2 = "UPDATE media SET name = '"+Mname+"', author = '"+Mauthor+"';";
                        st.executeUpdate(update2);

                        String update = "UPDATE mediadetails SET year = '" +Myear + "', genre = '"+Mgenre+"',"+
                                " description = '"+Mdescription+"', quantity = '"+ Mquantity+"'" +
                                "WHERE idmedia = '"+idmedia+"';";
                        st.executeUpdate(update);
                        frame.dispose();

                    }


                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }

            });
        }else{
            Error.errMessage("Wrong author!!");
            frame.dispose();
        }
    }
}
