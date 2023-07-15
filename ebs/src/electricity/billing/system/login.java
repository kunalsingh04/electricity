package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener {
    JTextField userText, passText;
    Choice loginchoice;
    JButton loginButton,cancelButton,signupButton;
    login(){
        super("Login");
        getContentPane().setBackground(Color.pink);
        JLabel username = new JLabel("Username");
        username.setBounds(300,60,100,20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400,60,150,20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300,100,100,20);
        add(password);

        passText = new JTextField();
        passText.setBounds(400,100,150,20);
        add(passText);

        JLabel loginas = new JLabel("Login as");
        loginas.setBounds(300,140,100,20);
        add(loginas);

        loginchoice = new Choice();
        loginchoice.add("Admin");
        loginchoice.add("Customer");
        loginchoice.setBounds(400,140,150,20);
        add(loginchoice);

        loginButton = new JButton("Login");
        loginButton.setBounds(330,180,100,20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(450,180,100,20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(395,215,100,20);
        signupButton.addActionListener(this);
        add(signupButton);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profileTow = profileOne.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon( profileTow);
        JLabel profileLabel = new JLabel(fprofileOne);
        profileLabel.setBounds(7,7,250,250);
        add(profileLabel);


        setSize(640,300);
        setLocation(400,200);
        setLayout(null);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==signupButton){
            setVisible(false);
            new signup();
        } else if (e.getSource()==cancelButton) {
            setVisible(false);
        } else if (e.getSource()==loginButton) {
            String susername = userText.getText();
            String spassword = passText.getText();
            String suser = loginchoice.getSelectedItem();
            try{
                database c = new database();
                String query = "select * from Signup where username = '"+susername+"' and password = '"+spassword+"'and  usertype = '"+suser+"'";
                ResultSet resultSet = c.statement.executeQuery(query);
                if(resultSet.next()){
                    String  meter = resultSet.getString("meter_no");
                    setVisible(false);
                    new main_class(suser,meter);
                }else{

                    JOptionPane.showMessageDialog( null, "Invalid Login");
                }

            }catch (Exception E){
                E.printStackTrace();

            }
        }

    }

    public static void main(String[] args) {
        new login();
    }
}
