package database;

import Objects.User;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class seeUser {
  static Panel p = new Panel();
   static JList<User> list;
    static Connection cn = null;
    static PreparedStatement st1,st2 = null;
   static Font font = new Font("Nanum Gothic",Font.BOLD,12);

    public static void allUsers(JFrame frame,JPanel panel) throws SQLException, ClassNotFoundException {
        JLabel id,name,egn,gsm,adress,description;

        id = new JLabel("ID");
        name = new JLabel("Name");
        egn = new JLabel("EGN");
        gsm = new JLabel("GSM");
        adress = new JLabel("Adress");
        description = new JLabel("Description");

        id.setFont(font);
        name.setFont(font);
        egn.setFont(font);
        gsm.setFont(font);
        adress.setFont(font);
        description.setFont(font);

        id.setForeground(new Color(255, 255, 255));
        name.setForeground(new Color(255, 255, 255));
        egn.setForeground(new Color(255, 255, 255));
        gsm.setForeground(new Color(255, 255, 255));
        description.setForeground(new Color(255, 255, 255));
        adress.setForeground(new Color(255, 255, 255));

        p.setLayout(new GridLayout(1, 6, 10, 0));
        p.setBounds(50,0,500,30);
        p.setFont(font);
        p.setBackground(new Color(3, 4, 94));
        frame.setSize(600,600);
        panel.setSize(500,450);
        cn =  db.getConnection();
        st1 = cn.prepareStatement("SELECT * FROM mediateka.users;");
        st2 = cn.prepareStatement("SELECT * FROM mediateka.userdetails;");

        list = new JList();
        ArrayList<User> users = new ArrayList<User>();

        ResultSet rset = st1.executeQuery();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> names = new ArrayList<String>();
        while (rset.next()) {
            ids.add(rset.getInt("idusers"));
            names.add(rset.getString("name"));
        }
        int i = 0;
        ResultSet rset2 = st2.executeQuery();

        while (rset2.next()) {
            users.add( new User(ids.get(i),names.get(i),
                    rset2.getString("egn"),rset2.getString("gsm"),
                    rset2.getString("description"),rset2.getString("adress")));
            i++;
        }
        ArrayList<String> output = new ArrayList<String>();

        for (User u : users) {
            output.add(u.getId()+" " +u.getName()+" " +u.getEgn()+" " +u.getGsm()+" " +u.getAdress()+" " +u.getDescription());
        }

        list = new JList(output.toArray());


        panel.setLayout(new GridLayout(1, 1, 20, 10));
        panel.add(list);
        p.add(id);
        p.add(name);
        p.add(egn);
        p.add(gsm);
        p.add(adress);
        p.add(description);
        frame.add(p);
        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
