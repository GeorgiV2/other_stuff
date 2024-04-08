package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db {

    public static Connection cn = null;


    public static  PreparedStatement st = null;


    public static Connection getConnection() throws
            ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/mediateka";
        String user = "root";
        String pass = "passwordis12345";
        Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;

    }

    public static Boolean exists( String table,String index,String tag){
        boolean e = false;
        try {
            cn = getConnection();
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        if (cn != null){
            try {
                String SQL = "SELECT * FROM " + table +" WHERE EXISTS(SELECT * FROM " + table +" WHERE "+index+" = '" + tag + "');";
                st = cn.prepareStatement(SQL);
                ResultSet rs = st.executeQuery();
                if (isFilled(rs))
                    e = true;
            } catch (SQLException l) {
                l.printStackTrace();
            }
        }
        return e;


    }

    public static Boolean exists( String table,String index,int tag){
        boolean e = false;
        try {
            cn = getConnection();
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        if (cn != null){
            try {
                String SQL = "SELECT * FROM " + table +" WHERE EXISTS(SELECT * FROM " + table +" WHERE "+index+" = '" + tag + "');";
                st = cn.prepareStatement(SQL);
                ResultSet rs = st.executeQuery();
                if (isFilled(rs))
                    e = true;
            } catch (SQLException l) {
                l.printStackTrace();
            }
        }
        return e;


    }

    public static boolean isFilled(ResultSet rs){
        boolean isEmpty = true;
        try {
            while(rs.next()){
                isEmpty = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !isEmpty;
    }

}
