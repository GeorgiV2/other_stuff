import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class GUI implements ActionListener {

    JButton [] inputs = new JButton[10];
    JButton [] operators = new JButton[6];
    JButton addButton,subButton,divButton,mulButton,equButton,clrButton,comButton,delButton;
    JTextField text;
    JPanel panel;
    JFrame frame;
    Font font = new Font("SansSerif", Font.BOLD,30);
    double num1 = 0,num2 = 0,result = 0;
    String operator;

    GUI() {
        frame = new JFrame();
        frame.setSize(350,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(false);
        frame.getContentPane().setBackground(new Color(181, 101, 118));
        frame.setTitle("Gosho's Calculator");
        ImageIcon img = new ImageIcon("//C:\\Users\\Admin\\Desktop\\Projects\\calc\\Calculator\\src\\Calculator.png");
        frame.setIconImage(img.getImage());
        frame.setLayout(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4,4,10,10));
        panel.setBounds(25,100,285,275);
        panel.setBackground(new Color(181, 101, 118));

        text = new JTextField();
        text.setDisabledTextColor(new Color(255,255,255));
        text.setBackground(new Color(234, 172, 139));
        text.setBorder(BorderFactory.createEmptyBorder());
        text.setFont(font);
        text.setBounds(25,20,285,50);
        text.setEnabled(false);


        addButton = new JButton("+");
        subButton = new JButton("-");
        divButton = new JButton("/");
        mulButton = new JButton("*");
        equButton = new JButton("=");
        clrButton = new JButton("Clear");
        comButton = new JButton(",");
        delButton = new JButton("Delete");

        delButton.addActionListener(this);
        delButton.setFont(font);
        delButton.setBounds(25,400,125,50);
        delButton.setBorderPainted(false);
        delButton.setFocusPainted(false);
        delButton.setBackground(new Color(229, 107, 111));
        delButton.setForeground(SystemColor.WHITE);

        clrButton.addActionListener(this);
        clrButton.setFont(font);
        clrButton.setBounds(185,400,125,50);
        clrButton.setBorderPainted(false);
        clrButton.setFocusPainted(false);
        clrButton.setBackground(new Color(229, 107, 111));
        clrButton.setForeground(SystemColor.WHITE);


        operators[0] = addButton;
        operators[1] = subButton;
        operators[2] = divButton;
        operators[3] = mulButton;
        operators[4] = equButton;
        operators[5] = comButton;


        for(int i = 0;i<6;i++){
            operators[i].addActionListener(this);
            operators[i].setFont(font);
            operators[i].setFocusable(false);
            operators[i].setBorderPainted(false);
            operators[i].setFocusPainted(false);
            operators[i].setBackground(new Color(229, 107, 111));
            operators[i].setForeground(SystemColor.WHITE);
        }

        for(int i = 0;i <10;i++){
            inputs[i] = new JButton(String.valueOf(i));
            inputs[i].setFont(font);
            inputs[i].setFocusable(false);
            inputs[i].addActionListener(this);
            inputs[i].setBorderPainted(false);
            inputs[i].setFocusPainted(false);
            inputs[i].setBackground(new Color(229, 107, 111));
            inputs[i].setForeground(SystemColor.WHITE);
        }

        panel.add(inputs[1]);
        panel.add(inputs[2]);
        panel.add(inputs[3]);
        panel.add(addButton);
        panel.add(inputs[4]);
        panel.add(inputs[5]);
        panel.add(inputs[6]);
        panel.add(subButton);
        panel.add(inputs[7]);
        panel.add(inputs[8]);
        panel.add(inputs[9]);
        panel.add(divButton);
        panel.add(inputs[0]);
        panel.add(comButton);
        panel.add(equButton);
        panel.add(mulButton);

        frame.add(clrButton);
        frame.add(delButton);
        frame.add(text);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0;i<10;i++){
            if(e.getSource() == inputs[i]){
                text.setText(text.getText().concat(String.valueOf(i)));
            }
        }

        if(e.getSource() == addButton){
            num1 = Double.parseDouble(text.getText());
            operator = "+";
            text.setText("");

        }

        if(e.getSource() == subButton){
            num1 = Double.parseDouble(text.getText());
            operator = "-";
            text.setText("");
        }

        if(e.getSource() == divButton){
            num1 = Double.parseDouble(text.getText());
            operator = "/";
            text.setText("");
        }

        if(e.getSource() == mulButton){
            num1 = Double.parseDouble(text.getText());
            operator = "*";
            text.setText("");
        }

        if(e.getSource() ==comButton){
            text.setText(text.getText().concat("."));
        }

        if(e.getSource() == equButton){
            num2 = Double.parseDouble(text.getText());
            if(operator.equalsIgnoreCase("+")){
                result = num1 + num2;
            } else if(operator.equalsIgnoreCase("-")){
                result = num1 - num2;
            }else if(operator.equalsIgnoreCase("/")){
                result = num1 / num2;
            }else if(operator.equalsIgnoreCase("*")){
                result = num1 * num2;
            }else{
                frame.dispose();
            }
            text.setText(String.valueOf(result));
        }

        if(e.getSource() == clrButton){
            num1 = 0;
            num2 = 0;
            operator = "";
            result = 0;
            text.setText("");
        }
        if(e.getSource() == delButton){
            String newText = text.getText().substring(0,text.getText().length()-1);
            text.setText(newText);
        }
    }
}


