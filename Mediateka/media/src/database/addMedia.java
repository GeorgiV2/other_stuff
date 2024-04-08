package database;
import Barcode.barcodeGenerator;
import GUI.Error;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;


public class addMedia {
        static JTextField name, author, year, description, genre, quantity;
         static JButton submit, upload;
         static JLabel lname, lauthor, lyear, ldescription, lgenre, lquantity, lupload;
        static Font font = new Font("Nanum Gothic", Font.BOLD, 30);
        static Connection cn = null;
        static Statement st = null;
         static int id;
         static File picture,barcode;

    public static void AddMedia(Frame frame, JPanel panel)  {

        frame.setSize(400, 800);

        //declaring the button,labels and textfields
        name = new JTextField();
        author = new JTextField();
        year = new JTextField();
        description = new JTextField();
        genre = new JTextField();
        quantity = new JTextField();
        submit = new JButton("Submit");
        upload = new JButton("Upload");
        lname = new JLabel("Name :");
        lupload = new JLabel("Upload a picture :");
        lauthor = new JLabel("Author :");
        lyear = new JLabel("Year :");
        ldescription = new JLabel("Description :");
        lgenre = new JLabel("Genre:");
        lquantity = new JLabel("Quantity :");

        //Adding font
        name.setFont(font);
        upload.setFont(font);
        lupload.setFont(font);
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
        lupload.setForeground(new Color(255, 255, 255));

        // buttons design
        submit.setBorderPainted(false);
        submit.setFocusPainted(false);
        submit.setBackground(new Color(0, 119, 182));
        upload.setBorderPainted(false);
        upload.setFocusPainted(false);
        upload.setBackground(new Color(0, 119, 182));

        //making some JTextFields that they can only accept numbers!!
        //Copied from https://www.tutorialspoint.com/how-can-we-make-jtextfield-accept-only-numbers-in-java
        //================================================================================
        year.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {

                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'|| ke.getKeyChar() == KeyEvent.VK_CLEAR) {
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
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'|| ke.getKeyChar() == KeyEvent.VK_CLEAR) {
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
        panel.setLayout(new GridLayout(15, 1, 20, 10));
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
        panel.add(lupload);
        panel.add(upload);
        panel.add(submit);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

        //Upload button for pictures
        upload.addActionListener(e -> {
            JFileChooser file_upload;
            file_upload = new JFileChooser(new File("C:\\Users\\Admin\\Desktop\\Projects\\Mediateka\\images\\"));
            file_upload.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
            file_upload.setAcceptAllFileFilterUsed(true);
            file_upload.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int res = file_upload.showSaveDialog(null);

            if(res == JFileChooser.APPROVE_OPTION){
                picture = new File(file_upload.getSelectedFile().getAbsolutePath());

            }
        });

        //Setting new Action listener because the method is static
        submit.addActionListener(e -> {

            try {
                //adding connection to the database
                cn = db.getConnection();
                st = cn.createStatement();



                //Declaring new Strings with the value of each JTextField
                String Mname = name.getText();
                String Mauthor = author.getText();
                int Myear = Integer.parseInt(year.getText());
                String Mdescription = description.getText();
                String Mgenre = genre.getText();
                int Mquantity = Integer.parseInt(quantity.getText());



                //Checks if there is another user with this name in the database
                if (Mname.isEmpty() && Mauthor.isEmpty() && Mdescription.isEmpty() && Mgenre.isEmpty() && Myear < 0 && Mquantity <=0) {
                    Error.errMessage("Please fill each textfield!!");
                    frame.dispose();
                } else {
                    if (!db.exists("media", "name", Mname)) {

                        String user = "INSERT INTO mediateka.media(`name`,`author`) " +
                                "VALUES('" + Mname + "','"+Mauthor+"');";
                        db.st = db.cn.prepareStatement(user);
                        db.st.executeUpdate();

                        //We are getting the id of the media we just added to our database and now we use it to add the other credentials
                        ResultSet rset = db.st.executeQuery("SELECT idmedia FROM mediateka.media WHERE name = '" + Mname + "';");
                        rset.next();
                         id = rset.getInt(1);

                        //generating barcode

                        int min = 100000;
                        int max = 999999;
                        int random_int1 = (int)Math.floor(Math.random()*(max-min+1)+min);
                        int random_int2 = (int)Math.floor(Math.random()*(max-min+1)+min);
                        String barcodeNum = random_int2 +""+ random_int1;

                        barcode = new File("C:\\Users\\Admin\\Desktop\\Projects\\Mediateka\\Barcodes\\"+barcodeNum+".png");
                        ImageIO.write(barcodeGenerator.generateBarcode(barcodeNum ), "png", barcode);

                        String userdetails = "INSERT INTO mediateka.mediadetails(idmedia,year,genre,quantity,description,picture,barcode) " +
                                "VALUES('" + id + "','" + Myear + "','" + Mgenre + "','" + Mquantity + "','" + Mdescription + "','"+id+".png','" + barcodeNum +"');";

                        PreparedStatement pst = db.cn.prepareStatement(userdetails);
                        pst.executeUpdate();

                    } else {
                       name.setText("Name is already used");
                        author.setText("");
                        year.setText("");
                        description.setText("");
                        genre.setText("");
                        quantity.setText("");
                    }
                    //We clear the values of each TextLabel after we press the submit button
                    name.setText("");
                    author.setText("");
                    year.setText("");
                    description.setText("");
                    genre.setText("");
                    quantity.setText("");
                }

                String picName = id + ".png";
                File newFile = new File("C:\\Users\\Admin\\Desktop\\Projects\\Mediateka\\images\\"+picName);
                try {
                   copyFile(picture,newFile);
                } catch (IOException es) {
                    es.printStackTrace();
                }


            } catch (ClassNotFoundException | IOException | SQLException ex) {
                ex.printStackTrace();
            }


        });


    }
    public static void copyFile( File from, File to ) throws IOException {
        Files.copy( from.toPath(), to.toPath() ,StandardCopyOption.REPLACE_EXISTING);
    }
}
