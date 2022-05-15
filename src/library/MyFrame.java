package library;

import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    JLabel label1;
    JLabel label2;
    JPanel panel1;
    JPanel panel2;
    JTextField textField1;
    JPasswordField passwordField1;
    JButton button;

    MyFrame() {
        label1 = new JLabel();
        label1.setText("Enter Staff_ID   ");
        label1.setBounds(50, 50, 150, 150);
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setText("Enter Password");
        label2.setBounds(50, 50, 150, 150);
        label2.setVisible(true);

        panel1 = new JPanel();
        panel1.setBackground(Color.GRAY);
        panel1.setBounds(50, 50, 400, 100);

        panel2 = new JPanel();
        panel2.setBackground(Color.GRAY);
        panel2.setBounds(50, 200, 400, 100);

        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(250, 40));
        textField1.setVisible(true);

        passwordField1 = new JPasswordField();
        passwordField1.setPreferredSize(new Dimension(250, 40));
        passwordField1.setVisible(true);

        button = new JButton("Login");
        button.setBounds(220, 350, 100, 50);//add actionlistener
        button.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(32,53,24));
        this.setTitle("Strathmore Library:  Librarian Section");
        //this.setLayout(new FlowLayout());
        //this.pack();
        this.setVisible(true);
        panel1.add(label1);
        panel1.add(textField1);
        panel2.add(label2);
        panel2.add(passwordField1);
        this.add(panel1);
        this.add(panel2);
        this.add(button);
        


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            try{
                loginUser();
                this.dispose();
                LoginWindow loginWindow = new LoginWindow();
            } catch (NumberFormatException ev) {
                JOptionPane.showMessageDialog(null, "Check that Staff_ID is in number format", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.out.println(ev.getMessage());//exception not working
            }
        }
        }

    private void loginUser() {
        String Staff_ID=textField1.getText();
        String password=String.valueOf(passwordField1.getPassword());

        while(Staff_ID.isEmpty() ||password.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter all fields","Try Again",JOptionPane.ERROR_MESSAGE);
             this.setVisible(true);


        }
    }
}
