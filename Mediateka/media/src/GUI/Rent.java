package GUI;
import database.rent_return;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Rent implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton rentMedia,returnMedia;
    Font font = new Font("Nanum Gothic",Font.BOLD,30);

    public Rent(){
        frame = new JFrame();
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(false);
        frame.getContentPane().setBackground(new Color(3, 4, 94));
        frame.setTitle("Media library administration");
        frame.setLayout(null);

        rentMedia = new JButton("Rent Media");
        returnMedia = new JButton("Return Media");


        rentMedia.addActionListener(this);
        returnMedia.addActionListener(this);


        rentMedia.setFont(font);
        returnMedia.setFont(font);


        rentMedia.setBackground(new Color(0, 119, 182));
        returnMedia.setBackground(new Color(0, 119, 182));


        rentMedia.setBorderPainted(false);
        rentMedia.setFocusPainted(false);
        returnMedia.setBorderPainted(false);
        returnMedia.setFocusPainted(false);



        panel = new JPanel();
        panel.setLayout(new GridLayout(2,1,10,10));
        panel.setBounds(50,50,285,355);
        panel.setBackground(new Color(3, 4, 94));

        panel.add(rentMedia);
        panel.add(returnMedia);


        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==rentMedia){
            panel.removeAll();
            frame.repaint();
            frame.revalidate();
            rent_return.rent_returnMedia(frame,panel,true);
        }else if(e.getSource()==returnMedia){
            panel.removeAll();
            frame.repaint();
            frame.revalidate();
            rent_return.rent_returnMedia(frame,panel,false);
        }
    }
}
