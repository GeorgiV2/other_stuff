package database;

import GUI.Error;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class seePic {
    JFrame frame;
    JPanel panel;
    static JLabel label;
    static JTextField text;
    static JButton submit;
    static Font font = new Font("Nanum Gothic", Font.BOLD, 25);
    static Connection cn = null;
    static PreparedStatement st = null;

    public seePic(){

        frame = new JFrame();
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(false);
        frame.getContentPane().setBackground(new Color(3, 4, 94));
        frame.setTitle("Media library administration");
        frame.setLayout(null);

        text = new JTextField();
        submit = new JButton("Submit");
        label = new JLabel("Enter id of the media :");

        label.setFont(font);
        text.setFont(font);
        submit.setFont(font);

        submit.setBorderPainted(false);
        submit.setFocusPainted(false);

        label.setForeground(new Color(255, 255, 255));

        panel = new JPanel();
        panel.setLayout(new GridLayout(3,1,10,10));
        panel.setBounds(50,50,285,355);
        panel.setBackground(new Color(3, 4, 94));

        panel.add(label);
        panel.add(text);
        panel.add(submit);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

        text.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'|| ke.getKeyChar() == KeyEvent.VK_CLEAR) {
                    text.setEditable(true);
                } else {
                    text.setEditable(false);
                    label.setText("* Enter only numeric digits(0-9)");
                }
            }
        });

        submit.addActionListener(e -> {
            int id = Integer.parseInt(text.getText());

            if(db.exists("mediadetails","idmedia",id)){
                try {
                    cn = db.getConnection();
                    st = cn.prepareStatement("SELECT picture FROM mediateka.mediadetails WHERE idmedia = '"+id+"';");
                    ResultSet rs = st.executeQuery();
                    rs.next();
                    String picName = rs.getString(1);
                    frame.setBackground(new Color(255,255,255));
                    panel.removeAll();
                    frame.repaint();
                    frame.revalidate();

                    pic(frame,panel,picName);

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }else{
                Error.errMessage("Wrong id !!");
                frame.dispose();
            }
        });

    }
    public static void pic(JFrame frame,JPanel panel,String picName){
        frame.remove(panel);
        Icon icon = new ImageIcon( "C:/Users/Admin/Desktop/Projects/Mediateka/images/" +picName);
        JLabel pic = new JLabel();
        pic.setIcon(icon);
        Dimension size = pic.getPreferredSize();
        pic.setBounds(100,100,size.width,size.height);
        frame.add(pic);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
