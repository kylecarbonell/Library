package Medical;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends GUI implements ActionListener{
    
    JPanel homePanel;

    JLabel title;

    JButton bookList;
    JButton turnIn;
    JButton checkoutBook;


    Home(){
        homePanel = new JPanel();
        frame.add(homePanel);
        homePanel.setLayout(null);

        //Title 
        title = new JLabel();
        title.setText("Library");
        title.setForeground(Color.white);
        title.setBackground(Color.gray);
        title.setFont(titleFont);
        title.setBounds(width / 2 - 125, height / 2 -350, 400, 100);
        homePanel.add(title);
        
        //Book List button
        bookList = new JButton();
        bookList.setText("Books");
        bookList.setFont(font);
        bookList.setForeground(Color.white);
        bookList.setBackground(Color.gray);
        bookList.setBounds(width / 2 - 200, height / 2 -200, 400, 100);
        bookList.addActionListener(this);
        bookList.setActionCommand("list");
        homePanel.add(bookList);

        //Turn in books button
        turnIn = new JButton();
        turnIn.setText("Your Books");
        turnIn.setFont(font);
        turnIn.setForeground(Color.white);
        turnIn.setBackground(Color.gray);
        turnIn.setBounds(width / 2 - 200, height / 2 -50, 400, 100);
        turnIn.addActionListener(this);
        turnIn.setActionCommand("turnIn");
        homePanel.add(turnIn);

        homePanel.setBackground(Color.darkGray);
    }

    public void close(){
        homePanel.setVisible(false);
    }

    public void open(){
        homePanel.setVisible(true);
        frame.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if(command.equals("list")){
            control.open("BookList");
        }
        else if(command.equals("turnIn")){
            control.open("YourBooks");
        }
    }
}
