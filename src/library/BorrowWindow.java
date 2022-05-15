package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BorrowWindow implements ActionListener {

    Connection conn;
    Statement stmt;
    ResultSet rs;
    JButton save,delete,update,newrec,back,next;
    JFrame frame;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JTextField textField1;
    JTextField textField2;
    JTextField textField3;
    JTextField textField4;

    public void DoConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/:3307/library","root","");

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            rs = stmt.executeQuery("Select * from borrow");

            rs.next();
               textField1.setText(String.valueOf(rs.getString("Student_ID")));
               textField2.setText(rs.getString("Title of book(s)"));
               textField3.setText(String.valueOf(rs.getDate(" Date Borrowed")));
               textField4.setText(String.valueOf(rs.getDate("Due Date")));


        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "There is an error!SQL exception", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("There is an sql exception");
            // e.printStackTrace();
        }catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "There is an error! class not found", "ERROR", JOptionPane.ERROR_MESSAGE);
           // e.printStackTrace();
        }
    }

    BorrowWindow(){

        panel1 = new JPanel();
        panel1.setBackground(Color.GRAY);
        panel1.setBounds(50, 50, 400, 50);

        panel2 = new JPanel();
        panel2.setBackground(Color.GRAY);
        panel2.setBounds(50, 150, 400, 50);

        panel3 = new JPanel();
        panel3.setBackground(Color.GRAY);
        panel3.setBounds(50, 250, 400, 50);

        panel4 = new JPanel();
        panel4.setBackground(Color.GRAY);
        panel4.setBounds(50, 350, 400, 50);

        label1 = new JLabel();
        label1.setText("Student_ID      ");
        label1.setBounds(50, 0, 150, 150);
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setText("Title of book(s)");
        label2.setBounds(50, 0, 150, 150);
        label2.setVisible(true);

        label3 = new JLabel();
        label3.setText("Date Borrowed   ");
        label3.setBounds(50, 0, 150, 150);
        label3.setVisible(true);

        label4 = new JLabel();
        label4.setText("Due   Date       ");
        label4.setBounds(50, 0, 150, 150);
        label4.setVisible(true);

        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(250, 40));
        textField1.setVisible(true);

        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(250, 40));
        textField2.setVisible(true);

        textField3 = new JTextField();
        textField3.setPreferredSize(new Dimension(250, 40));
        textField3.setVisible(true);

        textField4 = new JTextField();
        textField4.setPreferredSize(new Dimension(250, 40));
        textField4.setVisible(true);

        save=new JButton("Save"); save.setBounds(500,320,100,30);
        save.addActionListener(this);

        delete=new JButton("Delete"); delete.setBounds(500,380,100,30);
        delete.addActionListener(this);

        update=new JButton("Update"); update.setBounds(500,80,100,30);
        update.addActionListener(this);

        newrec=new JButton("New"); newrec.setBounds(500,140,100,30);
        newrec.addActionListener(this);

        back=new JButton("Back"); back.setBounds(500,200,100,30);
        back.addActionListener(this);

        next=new JButton("Next"); next.setBounds(500,260,100,30);
        next.addActionListener(this);


        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground( Color.pink);
        frame.setTitle("Strathmore Library:      BORROW");
        frame.setVisible(true);

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(save);
        frame.add(delete);
        frame.add(update);
        frame.add(newrec);
        frame.add(back);
        frame.add(next);
        panel1.add(label1);
        panel2.add(label2);
        panel3.add(label3);
        panel4.add(label4);
        panel1.add(textField1);
        panel2.add(textField2);
        panel3.add(textField3);
        panel4.add(textField4);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==save){
            try{

                //int textField11 = Integer.parseInt(textField1.getText());

               stmt.executeUpdate("INSERT INTO borrow  VALUES ('"+textField1.getText()+ "\",'"+textField2.getText()+"','"+textField3.getText()+textField4.getText()+"')");
                JOptionPane.showMessageDialog(null, "Record saved!","Message",JOptionPane.INFORMATION_MESSAGE);
                Statement stmtshow;
                stmtshow = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs=stmtshow.executeQuery("select Student_ID from borrow where Title of book(s)='"+textField2+"'");
                rs.next();
                textField1.setText(rs.getString("Student_ID"));
               // save.setEnabled(false);
            }
            catch(SQLException err){
                JOptionPane.showMessageDialog(null, "There is an error!", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.out.println(err.getMessage());
            }
            catch(NumberFormatException ev){
                JOptionPane.showMessageDialog(null, "Check that Student_ID is in number format", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.out.println(ev.getMessage());
            }
            catch(NullPointerException ex){
                System.out.println("There is null");
            }
        }

        if(e.getSource()==delete){
            try{
                stmt.executeUpdate("delete from borrow where Student_ID='"+textField1.getText()+"'");
                JOptionPane.showMessageDialog(null, "Record deleted!","Message",JOptionPane.INFORMATION_MESSAGE);

                //Execute query
                rs=stmt.executeQuery("select * from borrow");
                rs.next();

//STEP 5: Extract data from result set
                textField1.setText(Integer.toString(rs.getInt("Student_ID")));
                textField2.setText(rs.getString("Title of book(s)"));
                textField3.setText(rs.getString(rs.getInt(" Date Borrowed")));
                textField4.setText(Integer.toString(rs.getInt("Due Date")));


            }
            catch(SQLException err){
                JOptionPane.showMessageDialog(null, "There is an error!", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.out.println(err.getMessage());
            }
            catch(NullPointerException ex){
                System.out.println("There is null");
            }
        }

        if(e.getSource()==update){
            try{
                stmt.executeUpdate("update borrow set Student_ID='"+textField1.getText()+"',Title of book(s)='"+textField2.getText()+"',Date Borrowed='"+textField3.getText()+"',Due Date='"+textField4.getText()+"' WHERE Student_ID ='"+textField1.getText()+"'");
                JOptionPane.showMessageDialog(null, "Record updated!","Message",JOptionPane.INFORMATION_MESSAGE);
            }
            catch(SQLException err){
                JOptionPane.showMessageDialog(null, "There is an error!", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.out.println(err.getMessage());
            }
            catch(NullPointerException ex){
                System.out.println("There is null");
            }
        }

        if(e.getSource()==newrec){
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            save.setEnabled(true);
    }

        if(e.getSource()==back){
            try {
                if(rs.previous()){
                    textField1.setText(Integer.toString(rs.getInt("Student_ID")));
                    textField2.setText(rs.getString("Title of book(s)"));
                    textField3.setText(rs.getString(rs.getInt(" Date Borrowed")));
                    textField4.setText(Integer.toString(rs.getInt("Due Date")));


                }
                else{
                    rs.next();
                    JOptionPane.showMessageDialog(null, "Start of file.","Message",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "There is an error!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            catch(NullPointerException ex){
                System.out.println("There is null");
            }
        }

        if(e.getSource()==next){
            try {
                if(rs.next()){
                    textField1.setText(Integer.toString(rs.getInt("Student_ID")));
                    textField2.setText(rs.getString("Title of book(s)"));
                    textField3.setText(rs.getString(rs.getInt(" Date Borrowed")));
                    textField4.setText(Integer.toString(rs.getInt("Due Date")));


                }
                else{
                    rs.previous();
                    JOptionPane.showMessageDialog(null, "End of file.","Message",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "There is an error!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            catch(NullPointerException ex){
                System.out.println("There is null");
            }
        }

DoConnect();
    }}
