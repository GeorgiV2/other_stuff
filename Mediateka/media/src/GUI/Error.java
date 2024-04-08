package GUI;

import javax.swing.*;

public class Error {
    public static void errMessage(String message){
        JOptionPane.showMessageDialog(new JFrame(),message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
