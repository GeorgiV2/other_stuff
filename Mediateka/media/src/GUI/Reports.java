package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class Reports implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton rentedMedia,allMedia,allUsers;
    Font font = new Font("Nanum Gothic",Font.BOLD,30);

    Reports(){
        frame = new JFrame();
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(false);
        frame.getContentPane().setBackground(new Color(3, 4, 94));
        frame.setTitle("Media library administration");
        frame.setLayout(null);

        rentedMedia = new JButton("Rented Media");
        allMedia = new JButton("All Media");
        allUsers = new JButton("All Users");

        rentedMedia.addActionListener(this);
        allMedia.addActionListener(this);
        allUsers.addActionListener(this);

        rentedMedia.setFont(font);
        allMedia.setFont(font);
        allUsers.setFont(font);

        rentedMedia.setBackground(new Color(0, 119, 182));
        allMedia.setBackground(new Color(0, 119, 182));
        allUsers.setBackground(new Color(0, 119, 182));


        rentedMedia.setBorderPainted(false);
        rentedMedia.setFocusPainted(false);
        allMedia.setBorderPainted(false);
        allMedia.setFocusPainted(false);
        allUsers.setBorderPainted(false);
        allUsers.setFocusPainted(false);



        panel = new JPanel();
        panel.setLayout(new GridLayout(3,1,10,10));
        panel.setBounds(50,50,285,355);
        panel.setBackground(new Color(3, 4, 94));

        panel.add(rentedMedia);
        panel.add(allMedia);
        panel.add(allUsers);


        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == allUsers){
            panel.removeAll();
            frame.repaint();
            frame.revalidate();
            try {
                database.seeUser.allUsers(frame,panel);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }else if(e.getSource() == allMedia){
            panel.removeAll();
            frame.repaint();
            frame.revalidate();
            try {
                database.seeMedia.allMedia(frame,panel);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }else if(e.getSource() == rentedMedia){
            panel.removeAll();
            frame.repaint();
            frame.revalidate();

            try {
                database.seeMedia.rentedMedia(frame,panel);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        }

    }
}
