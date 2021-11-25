package Medical;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends GUI implements ActionListener{
    
    JPanel homePanel;
    JPanel menuPanel;

    JLabel title;

    JButton bookList;
    JButton addBook;
    JButton checkoutBook;


    Home(){
        homePanel = new JPanel();
        frame.add(homePanel);
        homePanel.setLayout(null);

        //Menu bar
        menuPanel = new JPanel();
        menuPanel.setBounds(0, 0, width, height/15);
        menuPanel.setBackground(Color.gray);
        homePanel.add(menuPanel);

        //Title 
        title = new JLabel();
        title.setText("Library");
        title.setForeground(Color.white);
        title.setBackground(Color.gray);
        title.setFont(titleFont);
        title.setBounds(width / 2 - 125, height / 2 -350, 400, 100);
        homePanel.add(title);
        
        //Book List
        bookList = new JButton();
        bookList.setText("Books");
        bookList.setForeground(Color.white);
        bookList.setBackground(Color.gray);
        bookList.setBounds(width / 2 - 200, height / 2 -200, 400, 100);
        bookList.addActionListener(this);
        bookList.setActionCommand("list");
        homePanel.add(bookList);

        //Add Book
        addBook = new JButton();
        addBook.setText("Add Books");
        addBook.setForeground(Color.white);
        addBook.setBackground(Color.gray);
        addBook.setBounds(width / 2 - 200, height / 2 -50, 400, 100);
        homePanel.add(addBook);

        //Checkout books
        checkoutBook = new JButton();
        checkoutBook.setText("Add Books");
        checkoutBook.setForeground(Color.white);
        checkoutBook.setBackground(Color.gray);
        checkoutBook.setBounds(width / 2 - 200, height / 2 +100, 400, 100);
        homePanel.add(checkoutBook);

        homePanel.setBackground(Color.darkGray);
        homePanel.setVisible(false);
    }

    public void close(){
        homePanel.setVisible(false);
    }

    public void open(){
        homePanel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String command = e.getActionCommand();

        if(command.equals("list")){
            control.open("BookList");
        }
    }
}
