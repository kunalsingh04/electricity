package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class signup extends JFrame  implements ActionListener {
    JTextField empText,userText,passText,nameText,meterText;
    Choice loginchoice;
    JButton cratebutton,backbutton;
    signup() {
        super("Signup");
        getContentPane().setBackground(Color.CYAN);
        JLabel a = new JLabel("Create-Account");
        a.setBounds(5, 5, 125, 20);
        add(a);
        JLabel create = new JLabel("Create Account As");
        create.setBounds(30, 50, 125, 20);
        add(create);

        loginchoice = new Choice();
        loginchoice.add("Admin");
        loginchoice.add("Customer");
        loginchoice.setBounds(170, 50, 125, 20);
        add(loginchoice);

        JLabel employ = new JLabel("Employer ID");
        employ.setBounds(30, 90, 125, 20);
        employ.setVisible(true);
        add(employ);

        empText = new JTextField();
        empText.setBounds(170, 90, 125, 20);
        employ.setVisible(true);
        add(empText);

        JLabel meterno = new JLabel("Meter No.");
        meterno.setBounds(30, 90, 125, 20);
        meterno.setVisible(false);
        add(meterno);

        meterText = new JTextField();
        meterText.setBounds(170, 90, 125, 20);
        meterText.setVisible(false);
        add(meterText);

        JLabel username = new JLabel("Username");
        username.setBounds(30, 130, 125, 20);
        add(username);

        userText = new JTextField();
        userText.setBounds(170, 130, 125, 20);
        add(userText);



        JLabel name = new JLabel("Name");
        name.setBounds(30, 170, 125, 20);
        add(name);

        nameText = new JTextField(" ");
        nameText.setBounds(170, 170, 125, 20);
        add(nameText);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from Signup where meter_no +'"+meterText.getText()+"'");
                    if(resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                    }

                }catch(Exception E){
                    E.printStackTrace();
                }
            }
        });

        JLabel password = new JLabel("Password");
        password.setBounds(30, 210, 125, 20);
        add(password);

        passText = new JTextField();
        passText.setBounds(170, 210, 125, 20);
        add(passText);

        cratebutton = new JButton("Create");
        cratebutton.setBackground(new Color(66, 127, 219));
        cratebutton.setForeground(Color.black);
        cratebutton.setBounds(50, 280, 100, 25);
        cratebutton.addActionListener(this);
        add(cratebutton);

        backbutton = new JButton("Back");
        backbutton.setBackground(new Color(66, 127, 219));
        backbutton.setForeground(Color.black);
        backbutton.setBounds(180, 280, 100, 25);
        backbutton.addActionListener(this);
        add(backbutton);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/sign-up.png"));
        Image profileTow = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTow);
        JLabel profileLabel = new JLabel(fprofileOne);
        profileLabel.setBounds(320, 50, 250, 250);
        add(profileLabel);

        loginchoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginchoice.getSelectedItem();
                if(user.equals("Customer")){
                    employ.setVisible(false);
                    nameText.setEditable(false);
                    empText.setVisible(false);
                    meterno.setVisible(true);
                    meterText.setVisible(true);
                }else{
                    employ.setVisible(true);
                    empText.setVisible(true);
                    meterno.setVisible(false);
                    meterText.setVisible(false);
                }
            }
        });

        setSize(600, 380);
        setLocation(400, 240);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cratebutton){
            String schoice = loginchoice.getSelectedItem();
            String susername = userText.getText();
            String sname = nameText.getText();
            String spass = passText.getText();
            String smeter = meterText.getText();
            try{
                database c = new database();
                String query = null ;
                if(loginchoice.equals("Admin")) {
                    query = "insert into Signup value('" + smeter + "','" + susername + "','" + sname + "','" + spass + "','" + schoice + "')";
                }else{
                    query = "update Signup set username = '"+susername+"',password = '"+spass+"',usertype='"+schoice+"' where meter_no = '"+smeter+"'";
                }
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Account Created");
                setVisible(false);
                new login();
            }catch (Exception v){
                v.printStackTrace();
            }
        } else if (e.getSource()==backbutton) {
            setVisible(false);
            new login();

        }
    }

    public static void main(String[] args) {
        new signup();
    }
}
