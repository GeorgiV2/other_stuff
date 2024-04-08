package GUI;
import database.addUser;
import database.searchByUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class InsertUsers implements ActionListener  {
    JFrame frame;
    JPanel panel;
    JButton insertUser,searchN,searchID;
    JLabel editUser;
    Font font = new Font("Nanum Gothic",Font.BOLD,30);


    InsertUsers(){
        frame = new JFrame();
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(false);
        frame.getContentPane().setBackground(new Color(3, 4, 94));
        frame.setTitle("Media library administration");
        frame.setLayout(null);

        insertUser = new JButton("Insert New User");
        editUser = new JLabel("Edit Existing User: ");
        searchN = new JButton("Search by Name");
        searchID = new JButton("Search by ID");



        insertUser.addActionListener(this);
        searchN.addActionListener(this);
        searchID.addActionListener(this);



        insertUser.setFont(font);
        editUser.setFont(new Font("Nanum Gothic",Font.BOLD,20));
        editUser.setForeground(new Color(255,255,255));
        searchN.setFont(font);
        searchID.setFont(font);


        insertUser.setBackground(new Color(0, 119, 182));
        editUser.setBackground(new Color(0, 119, 182));
        searchN.setBackground(new Color(0, 119, 182));
        searchID.setBackground(new Color(0, 119, 182));



        insertUser.setBorderPainted(false);
        insertUser.setFocusPainted(false);
        searchN.setBorderPainted(false);
        searchN.setFocusPainted(false);
        searchID.setBorderPainted(false);
        searchID.setFocusPainted(false);



        panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,10,10));
        panel.setBounds(50,50,285,355);
        panel.setBackground(new Color(3, 4, 94));

        panel.add( insertUser);
        panel.add(editUser);
        panel.add(searchN);
        panel.add( searchID);



        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

         if(e.getSource() == insertUser){
               panel.removeAll();
               frame.repaint();
               frame.revalidate();
               addUser.AddUsers(frame,panel);

         }else if(e.getSource() == searchN){
             panel.removeAll();
             frame.repaint();
             frame.revalidate();
            searchByUser.searchUser(frame,panel,"name");

         }else if(e.getSource() == searchID){
             panel.removeAll();
             frame.repaint();
             frame.revalidate();
             searchByUser.searchUser(frame,panel,"id");
        }
    }
}
