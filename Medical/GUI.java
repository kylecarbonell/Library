package Medical;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Font;



public class GUI{    
    //Size of screen
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    Font font = new Font("MonoSpaced", Font.PLAIN, 30);
    Font titleFont = new Font("MonoSpaced", Font.PLAIN, 50);

    //Mysql databse
    static MySql mysql = new MySql();
    
    //JFrames on all screens
    static JFrame frame = new JFrame();
    static JMenuBar menuBar = new JMenuBar();
    static JMenu menu = new JMenu();
    static JMenuItem homeItem = new JMenuItem();
    static JMenuItem publisherItem = new JMenuItem();

    static Controller control = new Controller();

    static int userId = 0;


    //Instantiates JFrame object for all GUI's
    public GUI(){
        frame.setTitle("Library");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        
        //Menu bar
        menuBar.setBounds(0, 0, width, 700);
        menuBar.setBackground(Color.gray);
        
        //Home Menu item
        menu.setBounds(100, 0, 200, 200);
        menu.setBackground(Color.blue);
        menu.setForeground(Color.white);
        menu.setText("Library");
        menu.setLayout(null);
        menu.setFont(new Font("MonoSpaced", Font.PLAIN, 20));

        //Menu Items
        homeItem.setFont(new Font("MonoSpaced", Font.PLAIN, 20));
        homeItem.setBounds(0, 0, 200, 600);
        homeItem.setText("Home");
        homeItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                control.open("Home");
            }
       
        });   

        //Publisher Items
        publisherItem.setFont(new Font("MonoSpaced", Font.PLAIN, 20));
        publisherItem.setBounds(0, 0, 200, 600);
        publisherItem.setText("Publish Book");
        publisherItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                control.open("Publish");
            }
       
        });   

        menu.add(homeItem);
        menu.add(publisherItem);
        menuBar.add(menu);
    }


}

