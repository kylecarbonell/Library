package Medical;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView.TableRow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TurnIn extends GUI implements ActionListener, MouseListener{
    int bookId;
    DefaultTableModel tempModel;
    Object[] columns = {"Book ID", "Book Name", "Author Name"};

    JPanel turnInPanel;
    JLabel title;

    DefaultTableModel checkedInModel;
    JTable checkedInTable;

    JScrollPane checkedInPane;
    
    DefaultTableModel wishlistModel;
    JTable wishlistTable;
    JScrollPane wishlistPane;

    JTabbedPane tb;

    JButton turnIn;
    JLabel turnInText;

    TurnIn(){
        //Main Panel
        turnInPanel = new JPanel();
        frame.add(turnInPanel);
        turnInPanel.setLayout(null);

        //Title 
        title = new JLabel();
        title.setText("Your Books");
        title.setForeground(Color.white);
        title.setBackground(Color.gray);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(titleFont);
        title.setBounds(width / 2 - 150, height / 2 - 400, 400, 100);
        turnInPanel.add(title);

        //Function that creates the checked in books
        makeCheckedIn();
        makeWishlist();

        //Adds actionlisteners
        checkedInTable.addMouseListener(this);
        wishlistTable.addMouseListener(this);

        //Holds Checkout and wishlist tables
        tb = new JTabbedPane();
        tb.setBounds(width / 2- 400, height / 2 - 300, 800, 350);
        tb.setFont(new Font("MonoSpaced", Font.PLAIN, 20));
        tb.addTab("Your Books", checkedInPane);
        tb.addTab("Wishlist", wishlistPane);

        //Remove / Turn in button
        turnIn = new JButton();
        turnIn = new JButton();
        turnIn.setText("turnIn");
        turnIn.setBackground(Color.lightGray);
        turnIn.setForeground(Color.BLACK);
        turnIn.setFont(font);
        turnIn.setBounds(width/2 - 175, height/2 + 200, 350, 50);
        turnIn.addActionListener(this);    
        turnIn.setActionCommand("turnIn");
        turnInPanel.add(turnIn);

        //Checkout text
        turnInText = new JLabel();
        turnInText.setFont(font);
        turnInText.setForeground(Color.white);
        turnInText.setBounds(width/2 - 525, height/2 + 100, 1000, 50);
        turnInText.setHorizontalAlignment(SwingConstants.CENTER);
        turnInPanel.add(turnInText);

        turnInPanel.add(tb);
        turnInPanel.setBackground(Color.darkGray);
    }

    public void makeWishlist(){
        //List holder
        wishlistModel = new DefaultTableModel();
        wishlistModel.setColumnIdentifiers(columns);

        //Checked in books Table
        wishlistTable = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        wishlistTable.setModel(wishlistModel);
        wishlistTable.setRowSelectionAllowed(false);
        wishlistTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        wishlistTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        wishlistTable.getColumnModel().getColumn(2).setPreferredWidth(400);
        wishlistTable.setRowHeight(50);
        wishlistTable.setFont(new Font("MonoSpaced", Font.PLAIN, 20));
        wishlistTable.setAutoCreateRowSorter(true);
        wishlistTable.addMouseListener(this);

        //Sorter for tables
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(wishlistModel);
        wishlistTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(1);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);

        wishlistPane = new JScrollPane(wishlistTable);
        wishlistPane.setBounds(width / 2- 400, height / 2 - 300, 800, 350);
    }

    public void makeCheckedIn(){
        //List holder
        checkedInModel = new DefaultTableModel();
        checkedInModel.setColumnIdentifiers(columns);

        //Checked in books Table
        checkedInTable = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        checkedInTable.setModel(checkedInModel);
        checkedInTable.setRowSelectionAllowed(false);
        checkedInTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        checkedInTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        checkedInTable.getColumnModel().getColumn(2).setPreferredWidth(400);
        checkedInTable.setRowHeight(71);
        checkedInTable.setFont(new Font("MonoSpaced", Font.PLAIN, 20));
        checkedInTable.setAutoCreateRowSorter(true);

        checkedInPane = new JScrollPane(checkedInTable);
        checkedInPane.setBounds(width / 2- 400, height / 2 - 300, 800, 350);
    }

    public void open(){
        turnInPanel.setVisible(true);

        listCheckedOut();
        listWishlist();
    }

    public void close(){
        turnInPanel.setVisible(false);
        checkedInModel.setRowCount(0);
        wishlistModel.setRowCount(0);
    }

    public void listCheckedOut(){
        checkedInModel.setRowCount(0);
        mysql.viewCheckedOutBooks(checkedInModel);
    }

    public void listWishlist(){
        wishlistModel.setRowCount(0);
        mysql.viewWishlistBooks(wishlistModel);
    }

    public void returnBooks(){
        mysql.turnInBooks(tempModel,bookId, turnInText);
        open();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        returnBooks();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        JTable table = (JTable)e.getSource();
        Point p = e.getPoint();
        int row = table.rowAtPoint(p);

        tempModel = (DefaultTableModel)table.getModel();
        bookId = Integer.valueOf((String)table.getValueAt(row, 0));
        System.out.println("Here");

        String bookName = (String)table.getValueAt(row, 1);
        turnInText.setText(bookName);
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
