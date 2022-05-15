package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow implements ActionListener {

    JFrame frame;
    JButton button1;
    JButton button2 ;
    LoginWindow(){

        button1 = new JButton("Borrow");
        button1.setBounds(200, 100, 100, 50);
        button1.addActionListener(this);

        button2= new JButton("Return");
        button2.setBounds(200, 200, 100, 50);
        button2.addActionListener(this);

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(0x123456));
        frame.setTitle("Strathmore Library: Is Book Being Borrowed or Returned");
        frame.setVisible(true);

        frame.add(button1);
        frame.add(button2);


    }

@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1){
            frame.dispose();
            BorrowWindow borrowWindow = new BorrowWindow();
        }
        else if(e.getSource()==button2){
            frame.dispose();
            ReturnWindow returnWindow = new ReturnWindow();
        }
    }
}
