package Medical;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends GUI implements ActionListener {

    private String user;
    private String pass;

    private JPanel loginPanel;

    private JTextField username;
    private JPasswordField password;

    private JLabel usernameText;
    private JLabel passwordText;

    private JButton loginButton;
    
    public Login(){
        super();
        loginPanel = new JPanel();
        frame.add(loginPanel);
        loginPanel.setLayout(null);
        
        //Username text label
        usernameText = new JLabel();
        usernameText.setText("Username : ");
        usernameText.setFont(font);
        usernameText.setForeground(Color.white);
        usernameText.setBounds(width/2 - 350, height/2 - 300, 400, 50);
        loginPanel.add(usernameText);
        
        //Username input box
        username = new JTextField();
        username.setForeground(Color.black);
        username.setFont(font);
        username.setBounds(width/2 -50, height/2 - 300, 400, 50);
        loginPanel.add(username);

        //Password text label
        passwordText = new JLabel();
        passwordText.setText("Password : ");
        passwordText.setFont(font);
        passwordText.setForeground(Color.white);
        passwordText.setBounds(width/2 - 350, height/2 - 225, 400, 50);
        loginPanel.add(passwordText);

        //Password inoput box
        password = new JPasswordField();
        password.setForeground(Color.black);
        password.setFont(font);
        password.setBounds(width/2 -50, height/2 - 225, 400, 50);
        loginPanel.add(password);

        //Login button
        loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.setBackground(Color.lightGray);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(font);
        loginButton.setBounds(width/2 -150, height/2 - 100, 350, 50);
        loginButton.setActionCommand("login");
        loginButton.addActionListener(this);    
        loginPanel.add(loginButton);
        
        loginPanel.setBackground(Color.DARK_GRAY);
        loginPanel.setVisible(false);
        
    }

    public void open(){
        loginPanel.setVisible(true);
    }

    public void close(){
        loginPanel.setVisible(false);
    }

    public void loginAction(){
        user = username.getText();
        pass = String.valueOf(password.getPassword());
        
        //Implement user and pass check system
        //Iterate through mysql database and check for user with both user and pass 
        /*Missing code*/

        //Temporary Code
        System.out.println(user);
        System.out.println(pass);
    
        System.out.println("Logged in");
        control.open("Home");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String action = e.getActionCommand();

        if(action.equals("login")){
            loginAction();
        }
    }
}
