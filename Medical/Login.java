package Medical;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends GUI implements ActionListener {

    private String user;
    private String pass;

    private JPanel loginPanel;
    
    private JLabel title;

    private JTextField username;
    private JPasswordField password;

    private JLabel usernameText;
    private JLabel passwordText;

    private JButton loginButton;
    private JButton createAccountButton;

    private JLabel errorText;
    
    public Login(){
        super();
        loginPanel = new JPanel();
        frame.add(loginPanel);
        loginPanel.setLayout(null);
        
        //Title text
        title = new JLabel();
        title.setText("Welcome to The Library");
        title.setBounds(width/2 - 400, height/2 - 400, 800, 50);
        title.setFont(new Font("MonoSpaced", Font.PLAIN, 50));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        loginPanel.add(title);

        
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
        loginButton.setBounds(width/2 -350, height/2 - 100, 350, 50);
        loginButton.setActionCommand("login");
        loginButton.addActionListener(this);    
        loginPanel.add(loginButton);

        //Create account button
        createAccountButton = new JButton();
        createAccountButton.setText("Create Account");
        createAccountButton.setBackground(Color.lightGray);
        createAccountButton.setForeground(Color.BLACK);
        createAccountButton.setFont(font);
        createAccountButton.setBounds(width/2 + 10, height/2 - 100, 350, 50);
        createAccountButton.setActionCommand("create");
        createAccountButton.addActionListener(this);    
        loginPanel.add(createAccountButton);

        //Error Text
        errorText = new JLabel();
        errorText.setFont(font);
        errorText.setForeground(Color.white);
        errorText.setBounds(width/2 - 400, height/2 - 160, 800, 50);
        errorText.setHorizontalAlignment(SwingConstants.CENTER);
        errorText.setVerticalAlignment(SwingConstants.CENTER);
        loginPanel.add(errorText);
        
        loginPanel.setBackground(Color.DARK_GRAY);        
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
    
        userId = mysql.login(user, pass, errorText);
        
        //Checks if userId is not null
        if(userId != 1){
            control.open("Home");
            username.setText("");
            password.setText("");
            frame.setJMenuBar(menuBar);
            return;
        }

        password.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String action = e.getActionCommand();

        if(action.equals("login")){
            loginAction();
            System.out.println(userId);
        }
        else if(action.equals("create")){
            control.open("CreateAccount");
        }
    }
}
