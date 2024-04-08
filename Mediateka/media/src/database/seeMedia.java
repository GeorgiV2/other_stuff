package database;
import Objects.Media;
import Objects.Rent;
import Objects.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class seeMedia {
    static Panel p = new Panel();
    static JList<Media> list;
    static Connection cn = null;
    static PreparedStatement st1,st2 = null;
    static Font font = new Font("Nanum Gothic",Font.BOLD,12);

    public static void allMedia(JFrame frame, JPanel panel)throws SQLException, ClassNotFoundException {
        JLabel id,name,author,year,genre,description,quantity,barcode;
        JButton seePic;

        id = new JLabel("ID");
        name = new JLabel("Name");
        author = new JLabel("Author");
        year = new JLabel("Year");
        genre = new JLabel("Genre");
        description = new JLabel("Description");
        quantity = new JLabel("Quantity");
        barcode = new JLabel("Barcode");
        seePic = new JButton("See Picture");

        seePic.setBorderPainted(false);
        seePic.setFocusPainted(false);
        seePic.setBackground(new Color(0, 119, 182));
        seePic.setBounds(250,510,200,50);

        id.setFont(font);
        name.setFont(font);
        author.setFont(font);
        year.setFont(font);
        genre.setFont(font);
        description.setFont(font);
        quantity.setFont(font);
        barcode.setFont(font);

        id.setForeground(new Color(255, 255, 255));
        name.setForeground(new Color(255, 255, 255));
        author.setForeground(new Color(255, 255, 255));
        year.setForeground(new Color(255, 255, 255));
        description.setForeground(new Color(255, 255, 255));
        genre.setForeground(new Color(255, 255, 255));
        quantity.setForeground(new Color(255, 255, 255));
        barcode.setForeground(new Color(255, 255, 255));

        p.setLayout(new GridLayout(1, 8, 10, 0));
        p.setBounds(50,0,600,30);
        p.setFont(font);
        p.setBackground(new Color(3, 4, 94));
        frame.setSize(700,600);
        panel.setSize(600,450);
        cn =  db.getConnection();
        st1 = cn.prepareStatement("SELECT * FROM mediateka.media;");
        st2 = cn.prepareStatement("SELECT * FROM mediateka.mediadetails;");

        list = new JList();
        ArrayList<Media> media = new ArrayList<Media>();

        ResultSet rset = st1.executeQuery();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> authors = new ArrayList<String>();
        while (rset.next()) {
            ids.add(rset.getInt("idmedia"));
            names.add(rset.getString("name"));
            authors.add(rset.getString("author"));
        }
        int i = 0;
        ResultSet rset2 = st2.executeQuery();

        while (rset2.next()) {
            media.add( new Media(ids.get(i),names.get(i),authors.get(i),
                    rset2.getString("year"),rset2.getString("genre"),
                    rset2.getString("description"),rset2.getString("barcode"),rset2.getInt("quantity")));
            i++;
        }
        ArrayList<String> output = new ArrayList<String>();

        for (Media m : media) {
            output.add(m.getId()+" " +m.getName()+" " +m.getAuthor()+" " +m.getYear()+" " +m.getGenre()+" " +m.getDescription()
                    +" " +m.getBarcode()+" " +m.getQuantity());
        }

        list = new JList(output.toArray());


        panel.setLayout(new GridLayout(1, 1, 20, 10));
        panel.add(list);

        p.add(id);
        p.add(name);
        p.add(author);
        p.add(year);
        p.add(genre);
        p.add(description);
        p.add(barcode);
        p.add(quantity);
        frame.add(p);
        frame.add(panel);
        frame.add(seePic);
        frame.setVisible(true);
        frame.setResizable(false);


        seePic.addActionListener(e -> {
            new seePic();
        });
    }

    public static void rentedMedia(JFrame frame, JPanel panel) throws SQLException, ClassNotFoundException {
        JLabel idM,idU,nameU,dateOfRent;
         Panel p = new Panel();
        idM = new JLabel("ID - Media");
        idU = new JLabel("ID - User");
        nameU = new JLabel("Name - User");
        dateOfRent = new JLabel("Date Rented");

        idM.setFont(font);
        idU.setFont(font);
        nameU.setFont(font);
        dateOfRent.setFont(font);

        idM.setForeground(new Color(255, 255, 255));
        idU.setForeground(new Color(255, 255, 255));
        nameU.setForeground(new Color(255, 255, 255));
        dateOfRent.setForeground(new Color(255, 255, 255));

        p.setLayout(new GridLayout(1, 4, 10, 0));
        p.setBounds(50,0,600,30);
        p.setFont(font);
        p.setBackground(new Color(3, 4, 94));

        frame.setSize(700,600);
        panel.setSize(600,450);

        ArrayList<Rent> rent = new ArrayList<>();
        ArrayList<Integer>id_media =new ArrayList<>();
        ArrayList<Integer>id_user =new ArrayList<>();
        ArrayList<Date> dates = new ArrayList<>();

        cn =  db.getConnection();
        st1 = cn.prepareStatement("SELECT * FROM mediateka.rent WHERE end_date IS NULL;");

        ResultSet rs = st1.executeQuery();

        while(rs.next()){
            id_media.add(rs.getInt("id_media"));
            id_user.add(rs.getInt("id_users"));
            dates.add(rs.getDate("start_date"));
        }

        int i = 0;

        st2 = cn.prepareStatement("SELECT name FROM mediateka.users ;");
        ResultSet rs2 = st2.executeQuery();
        rs2.next();

        while(rs2.next()){
            rent.add(new Rent(id_media.get(i),id_user.get(i),rs2.getString("name"),dates.get(i) ));
            i++;
        }
        ArrayList<String> output = new ArrayList<String>();

        for(Rent r : rent){
           output.add(r.getId_media()+" " +r.getId_user()+" " +r.getNameUser()+" " +r.getDate()+" ");
        }

        list = new JList(output.toArray());
        panel.setLayout(new GridLayout(1, 1, 20, 10));
        panel.add(list);

        p.add(idM);
        p.add(idU);
        p.add(nameU);
        p.add(dateOfRent);
        frame.add(p);
        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);

    }
}

