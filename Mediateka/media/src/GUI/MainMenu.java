package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MainMenu implements ActionListener {
    JFrame frame;
    JPanel panel;
    JButton insertUser,insertMedia,rent,reports;
    Font font = new Font("Nanum Gothic",Font.BOLD,30);

    public MainMenu(){
        frame = new JFrame();
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(false);
        frame.getContentPane().setBackground(new Color(3, 4, 94));
        frame.setTitle("Media library administration");
        frame.setLayout(null);

        insertUser = new JButton("Insert/Edit User");
        insertMedia = new JButton("Insert/Edit Media");
        rent = new JButton("Rent");
        reports = new JButton("Reports");

        insertMedia.addActionListener(this);
        insertUser.addActionListener(this);
        rent.addActionListener(this);
        reports.addActionListener(this);

        rent.setFont(font);
        reports.setFont(font);
        insertMedia.setFont(font);
        insertUser.setFont(font);

        rent.setBackground(new Color(0, 119, 182));
        reports.setBackground(new Color(0, 119, 182));
        insertMedia.setBackground(new Color(0, 119, 182));
        insertUser.setBackground(new Color(0, 119, 182));

        rent.setBorderPainted(false);
        rent.setFocusPainted(false);
        reports.setBorderPainted(false);
        reports.setFocusPainted(false);
        insertUser.setBorderPainted(false);
        insertUser.setFocusPainted(false);
        insertMedia.setBorderPainted(false);
        insertMedia.setFocusPainted(false);


        panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,10,10));
        panel.setBounds(50,50,285,355);
        panel.setBackground(new Color(3, 4, 94));

        panel.add(rent);
        panel.add(reports);
        panel.add(insertMedia);
        panel.add(insertUser);

        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == rent){
            new Rent();
        }else if(e.getSource() == reports){
            new Reports();
        }else if (e.getSource() == insertMedia){
            new InsertMedia();
        }else if(e.getSource() == insertUser){
            new InsertUsers();
        }

    }
}
