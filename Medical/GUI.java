package Medical;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;

public class GUI {    
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
    static Controller control = new Controller();


    //Instantiates JFrame object for all GUI's
    public GUI(){
        frame.setTitle("Library");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

