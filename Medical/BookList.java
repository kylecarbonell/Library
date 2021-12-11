package Medical;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.*;

public class BookList extends GUI implements KeyListener, ActionListener, MouseListener{
    int bookId;
    Object[] columns = {"ID", "Book Name", "Author Name", "Quantity"};

    JPanel bookListPanel;
    JScrollPane books;

    JTable bookTable;
    DefaultTableModel model;

    JTextField search;
    JLabel searchText;
    JButton searchCancel;

    JButton checkout;
    JButton wishlist;
    JLabel checkoutText;

    BookList(){
        //Main Panel
        bookListPanel = new JPanel();
        frame.add(bookListPanel);
        bookListPanel.setLayout(null);

        //Username text label
        searchText = new JLabel();
        searchText.setText("Search : ");
        searchText.setFont(font);
        searchText.setForeground(Color.white);
        searchText.setBounds(width/2 - 400, height/2 -400, 400, 50);
        bookListPanel.add(searchText);
        
        //Username input box
        search = new JTextField();
        search.setForeground(Color.black);
        search.setFont(font);
        search.setBounds(width/2 -200, height/2 - 400, 525, 50);
        search.addKeyListener(this);
        bookListPanel.add(search);
         
        //Cancel search box
        searchCancel = new JButton();
        searchCancel.setForeground(Color.black);
        searchCancel.setFont(font);
        searchCancel.setBounds(width/2 +350, height/2 - 400, 50, 50);
        searchCancel.addActionListener(this);
        searchCancel.setActionCommand("cancel");
        bookListPanel.add(searchCancel);

        //Sets up inside of JTABLE
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        listAll();
        
        //Edits content of the JTABLE
        bookTable = new JTable(){

            public boolean isCellEditable(int row, int column) {                
                return false;               
            };
        };
        //Properties of table columns and rows
        bookTable.setModel(model);
        bookTable.setRowSelectionAllowed(false);
        bookTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        bookTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        bookTable.getColumnModel().getColumn(2).setPreferredWidth(400);
        bookTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        bookTable.setRowHeight(30);
        bookTable.setFont(new Font("MonoSpaced", Font.PLAIN, 20));
        bookTable.addMouseListener(this);
        bookTable.setAutoCreateRowSorter(true);

        //Holder of JTable
        books = new JScrollPane(bookTable);
        books.setBounds(width / 2- 400, height / 2 - 300, 800, 350);
        bookListPanel.add(books);
        
        //Checkout text
        checkoutText = new JLabel();
        checkoutText.setFont(font);
        checkoutText.setForeground(Color.white);
        checkoutText.setBounds(width/2 - 525, height/2 + 100, 1000, 50);
        checkoutText.setHorizontalAlignment(SwingConstants.CENTER);
        bookListPanel.add(checkoutText);

        //Checkout button
        checkout = new JButton();
        checkout.setText("Checkout");
        checkout.setBackground(Color.lightGray);
        checkout.setForeground(Color.BLACK);
        checkout.setFont(font);
        checkout.setBounds(width/2 +50, height/2 + 200, 350, 50);
        checkout.addActionListener(this);    
        checkout.setActionCommand("checkout");
        bookListPanel.add(checkout);

        //Wishlist button
        wishlist = new JButton();
        wishlist.setText("Wishlist");
        wishlist.setBackground(Color.lightGray);
        wishlist.setForeground(Color.BLACK);
        wishlist.setFont(font);
        wishlist.setBounds(width/2 -400, height/2 + 200, 350, 50);
        wishlist.addActionListener(this);    
        wishlist.setActionCommand("wishlist");
        bookListPanel.add(wishlist);
        
        bookListPanel.setBackground(Color.darkGray);
    }

    public void open(){
        bookListPanel.setVisible(true);
        listAll();
    }

    public void close(){
        bookListPanel.setVisible(false);
    }

    public void listAll(){
        model.setRowCount(0);
        mysql.listBooks(model);
    }

    public void listSearch(String word){
        model.setRowCount(0);
        mysql.searchBooks(word, model);
    }  

    public void checkOut(int bookId){
        mysql.checkoutBooks(bookId, checkoutText);
        listAll();
    }

    public void wishlist(int bookId){
        mysql.wishlistBooks(bookId, checkoutText);
        listAll();
    }

    //Implemented methods
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    //Checks if searching for word
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            String word = search.getText();
            listSearch(word);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    //Runs actions for each button
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String action = e.getActionCommand();
        if(action.equals("cancel")){
            search.setText("");
            listAll();
        }
        else if(action.equals("checkout")){
            checkOut(bookId);
        }
        else if(action.equals("wishlist")){
            wishlist(bookId);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    //Gets book clicked on and point on table
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        JTable table = (JTable)e.getSource();
        Point p = e.getPoint();
        int row = table.rowAtPoint(p);

        bookId = Integer.valueOf((String)table.getValueAt(row, 0));
        String bookName = (String)table.getValueAt(row, 1);

        checkoutText.setText(bookName);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

}
