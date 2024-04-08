package GUI;
import database.addMedia;
import database.searchByMedia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InsertMedia implements ActionListener  {
    JFrame frame;
    JPanel panel;
    JButton insertMedia,searchA,searchT,searchISBN;
    JLabel editMedia;
    Font font = new Font("Nanum Gothic",Font.BOLD,30);

    InsertMedia(){
        frame = new JFrame();
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(false);
        frame.getContentPane().setBackground(new Color(3, 4, 94));
        frame.setTitle("Media library administration");
        frame.setLayout(null);

        insertMedia = new JButton("Insert New Media");
        editMedia = new JLabel("Edit Existing Media: ");
        searchA = new JButton("Search by Author");
        searchT = new JButton("Search by Title");
        searchISBN = new JButton("Search by Barcode");


        insertMedia.addActionListener(this);
        searchA.addActionListener(this);
        searchT.addActionListener(this);
        searchISBN.addActionListener(this);


        insertMedia.setFont(font);
        editMedia.setFont(new Font("Nanum Gothic",Font.BOLD,20));
        editMedia.setForeground(new Color(255,255,255));
        searchA.setFont(new Font("Nanum Gothic",Font.BOLD,28));
        searchT.setFont(font);
        searchISBN.setFont(font);


        insertMedia.setBackground(new Color(0, 119, 182));
        editMedia.setBackground(new Color(0, 119, 182));
        searchA.setBackground(new Color(0, 119, 182));
        searchT.setBackground(new Color(0, 119, 182));
        searchISBN.setBackground(new Color(0, 119, 182));


        insertMedia.setBorderPainted(false);
        insertMedia.setFocusPainted(false);
        searchA.setBorderPainted(false);
        searchA.setFocusPainted(false);
        searchT.setBorderPainted(false);
        searchT.setFocusPainted(false);
        searchISBN.setBorderPainted(false);
        searchISBN.setFocusPainted(false);


        panel = new JPanel();
        panel.setLayout(new GridLayout(5,1,10,10));
        panel.setBounds(50,50,285,355);
        panel.setBackground(new Color(3, 4, 94));

        panel.add(insertMedia);
        panel.add(editMedia);
        panel.add(searchT);
        panel.add(searchA);
        panel.add(searchISBN);


        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == insertMedia){
            panel.removeAll();
            frame.repaint();
            frame.revalidate();
            addMedia.AddMedia(frame,panel);
        }else if(e.getSource() == searchA){
            panel.removeAll();
            frame.repaint();
            frame.revalidate();
            searchByMedia.searchMedia(frame,panel,"author");
        }else if(e.getSource() == searchISBN){
            panel.removeAll();
            frame.repaint();
            frame.revalidate();
            searchByMedia.searchMedia(frame,panel,"barcode");
        }else if(e.getSource() == searchT){
            panel.removeAll();
            frame.repaint();
            frame.revalidate();
            searchByMedia.searchMedia(frame,panel,"name");
        }
    }
}
