package Medical;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CreateAccount extends GUI implements ActionListener{
    private JPanel createAccountPanel;
    private JLabel title;

    private JTextField username;
    private JTextField password;
    private JTextField confirmPassword;
    private JTextField firstName;
    private JTextField lastName;

    private JLabel usernameText;
    private JLabel passwordText;
    private JLabel confirmPasswordText;
    private JLabel firstNameText;
    private JLabel lastNameText;

    private JButton createAccountButton;
    private JLabel errorText;

    CreateAccount(){
        createAccountPanel = new JPanel();
        frame.add(createAccountPanel);
        createAccountPanel.setLayout(null);

        //Title text
        title = new JLabel();
        title.setText("Create Account");
        title.setBounds(width/2 - 200, height/2 -400, 600, 50);
        title.setFont(new Font("MonoSpaced", Font.PLAIN, 50));
        title.setForeground(Color.white);
        createAccountPanel.add(title);
        
        //Username text label
        usernameText = new JLabel();
        usernameText.setText("Username : ");
        usernameText.setFont(font);
        usernameText.setForeground(Color.white);
        usernameText.setBounds(width/2 - 400, height/2 - 300, 400, 50);
        createAccountPanel.add(usernameText);
        
        //Username input box
        username = new JTextField();
        username.setForeground(Color.black);
        username.setFont(font);
        username.setBounds(width/2 + 50, height/2 - 300, 400, 50);
        createAccountPanel.add(username);

        //Password text label
        passwordText = new JLabel();
        passwordText.setText("Password : ");
        passwordText.setFont(font);
        passwordText.setForeground(Color.white);
        passwordText.setBounds(width/2 - 400, height/2 - 225, 400, 50);
        createAccountPanel.add(passwordText);

        //Password inoput box
        password = new JTextField();
        password.setForeground(Color.black);
        password.setFont(font);
        password.setBounds(width/2 + 50, height/2 - 225, 400, 50);
        createAccountPanel.add(password);

        //Confirm Password text label
        confirmPasswordText = new JLabel();
        confirmPasswordText.setText("Confirm Password : ");
        confirmPasswordText.setFont(font);
        confirmPasswordText.setForeground(Color.white);
        confirmPasswordText.setBounds(width/2 - 400, height/2 - 150, 400, 50);
        createAccountPanel.add(confirmPasswordText);

        //Confirm Password inoput box
        confirmPassword = new JTextField();
        confirmPassword.setForeground(Color.black);
        confirmPassword.setFont(font);
        confirmPassword.setBounds(width/2 + 50, height/2 - 150, 400, 50);
        createAccountPanel.add(confirmPassword);

        //First name text label
        firstNameText = new JLabel();
        firstNameText.setText("First Name : ");
        firstNameText.setFont(font);
        firstNameText.setForeground(Color.white);
        firstNameText.setBounds(width/2 - 400, height/2 - 75, 400, 50);
        createAccountPanel.add(firstNameText);

        //First name input box
        firstName = new JTextField();
        firstName.setForeground(Color.black);
        firstName.setFont(font);
        firstName.setBounds(width/2 + 50, height/2 - 75, 400, 50);
        createAccountPanel.add(firstName);

        //Last name text label
        lastNameText = new JLabel();
        lastNameText.setText("Last Name : ");
        lastNameText.setFont(font);
        lastNameText.setForeground(Color.white);
        lastNameText.setBounds(width/2 - 400, height/2, 400, 50);
        createAccountPanel.add(lastNameText);

        //Last name input box
        lastName = new JTextField();
        lastName.setForeground(Color.black);
        lastName.setFont(font);
        lastName.setBounds(width/2 + 50, height/2 , 400, 50);
        createAccountPanel.add(lastName);

        //Create Account button
        createAccountButton = new JButton();
        createAccountButton.setText("Create Account");
        createAccountButton.setBackground(Color.lightGray);
        createAccountButton.setForeground(Color.BLACK);
        createAccountButton.setFont(font);
        createAccountButton.setBounds(width/2 -175, height/2 + 150, 350, 50);
        createAccountButton.setActionCommand("login");
        createAccountButton.addActionListener(this);    
        createAccountPanel.add(createAccountButton);

        //Error Text
        errorText = new JLabel();
        errorText.setFont(font);
        errorText.setForeground(Color.white);
        errorText.setBounds(width/2 - 400, height/2 + 75, 800, 50);
        errorText.setHorizontalAlignment(SwingConstants.CENTER);
        errorText.setVerticalAlignment(SwingConstants.CENTER);
        createAccountPanel.add(errorText);
        createAccountPanel.setBackground(Color.DARK_GRAY);
    }

    public void open(){
        createAccountPanel.setVisible(true);
    }

    public void close(){
        createAccountPanel.setVisible(false);
    }

    //Runs action
    //Takes in all text from the textfields and passes it into the mysql function
    @Override
    public void actionPerformed(ActionEvent e) {
        String user = username.getText();
        String pass = password.getText();
        String conPass = confirmPassword.getText();
        String fn = firstName.getText();
        String ln = lastName.getText();

        //Checks if any box is empty
        if(user.isEmpty() || pass.isEmpty() || conPass.isEmpty() || fn.isEmpty() || ln.isEmpty()){
            errorText.setText("Please fill out all the information");
            return;
        }

        //Checks if passwords equal each other
        if(!pass.equals(conPass)){
            errorText.setText("Passwords are not equal to each other");
            return;
        }

        //Adds to Mysql
        String[] info = {fn, ln, user, pass};
        mysql.makeAccount(info);

        //Sets all text to empty
        username.setText("");
        password.setText("");
        confirmPassword.setText("");
        firstName.setText("");
        lastName.setText("");

        //Opens the login gui
        control.open("Login");
    }
}
