package electricity.billing.system;

import javax.sql.RowSetListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class newCustomer extends JFrame implements ActionListener {
    JLabel heading,meterText,customername,meterno,address,city,state,email,phone;

    TextField nameText,addtext,citytext,statetext,emailtext,phonetext;

    JButton nextButton,cancelButton;
    newCustomer(){
        super("New Customer");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252,186,3));
        add(panel);

        heading = new JLabel("New Customer");
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        heading.setBounds(180,10,200,20);
        panel.add(heading);

        customername = new JLabel("Customer Name");
        customername.setBounds(50,80,100,20);
        panel.add(customername);

        nameText = new TextField();
        nameText.setBounds(180,80,150,20);
        panel.add(nameText);

        meterno = new JLabel("Meter No.");
        meterno.setBounds(50,120,100,20);
        panel.add(meterno);

        meterText = new JLabel("");
        meterText.setBounds(180,120,150,20);
        panel.add(meterText);

        Random ram = new Random();
        long number = ram.nextLong() % 1000000;
        meterText.setText(""+Math.abs(number));


        address = new JLabel("Address");
        address.setBounds(50,160,100,20);
        panel.add(address);

        addtext = new TextField();
        addtext.setBounds(180,160,150,20);
        panel.add(addtext);

        city = new JLabel("City");
        city.setBounds(50,200,100,20);
        panel.add(city);

        citytext = new TextField();
        citytext.setBounds(180,200,150,20);
        panel.add(citytext);

        state = new JLabel("State");
        state.setBounds(50,240,100,20);
        panel.add(state);

        statetext = new TextField();
        statetext.setBounds(180,240,150,20);
        panel.add(statetext);

        email = new JLabel("E-mail");
        email.setBounds(50,280,100,20);
        panel.add(email);

        emailtext = new TextField();
        emailtext.setBounds(180,280,150,20);
        panel.add(emailtext);

        phone = new JLabel("Phone Number");
        phone.setBounds(50,320,100,20);
        panel.add(phone);

        phonetext = new TextField();
        phonetext.setBounds(180,320,150,20);
        panel.add(phonetext);


        nextButton = new JButton("Next");
        nextButton.setBounds(120,390,100,25);
        nextButton.setBackground(Color.black);
        nextButton.setForeground(Color.white);
        nextButton.addActionListener(this);
        panel.add(nextButton);


        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(230,390,100,25);
        cancelButton.setBackground(Color.black);
        cancelButton.setForeground(Color.white);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        setLayout(new BorderLayout());
        add(panel,"Center");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgalable = new JLabel(i3);
        add(imgalable,"West");


        setSize(700,500);
        setLocation(400,200);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==nextButton){
            String sname = nameText.getText();
            String smeter = meterText.getText();
            String saddress = addtext.getText();
            String scity = citytext.getText();
            String sstate = statetext.getText();
            String semail = emailtext.getText();
            String sphone = phonetext.getText();



            String query_customer = "insert into new_customer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+semail+"','"+sphone+"')";
            String query_signup = "insert into Signup values('"+smeter+"','"+sname+"',' ',' ','')";

            try{
                database c = new database();
                c.statement.executeUpdate( query_customer );
                c.statement.executeUpdate( query_signup );
                JOptionPane.showMessageDialog(null,"Customer details added successfully. ");
                setVisible(false);
                new meterInfo(smeter);

            }catch(Exception E){
                E.printStackTrace();
            }




        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new newCustomer();
    }
}
