package Medical;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ScrollPaneLayout;
import javax.swing.table.*;

import java.awt.GridLayout;
import java.util.Objects;

public class BookList extends GUI{
    
    JPanel bookListPanel;
    JScrollPane books;

    JTable bookTable;

    //Book Table columns
    TableColumn bookName;
    TableColumn bookAuthor;

    BookList(){
        bookListPanel = new JPanel();
        frame.add(bookListPanel);
        bookListPanel.setLayout(null);

        
        //Scrollpane that holds the list of books
        books = new JScrollPane();
        books.setLayout(null);
        books.setBounds(width / 2 - 300, height / 2 - 300, 700, 500);
        books.setBackground(Color.lightGray);
        bookListPanel.add(books);
        
        
        //Book Columns
        //Book ID
        
        //Book Name
        bookName = new TableColumn();
        bookName.setWidth(1);
        
        //Book Author
        
        //List of columns and rows
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn(bookName);
        model.addColumn(bookName);
        model.addColumn("Author");
        model.addColumn("ID");
        model.setColumnIdentifiers(new Object[]{"book", "name", "author"});
        //model.addColumn("Price");
        model.addRow(new Object[]{"Testing", "Testing 2", "Testing 3", "Testing 4"});
        
        
        //Table that holds books
        bookTable = new JTable(model);
        bookTable.setBounds(0, 0, 700, 500);
        books.add(bookTable);
        bookListPanel.add(books);

        //Scrollpane to hold jtable

        //list();
        bookListPanel.setBackground(Color.darkGray);
        bookListPanel.setVisible(false);
    }

    public void open(){
        bookListPanel.setVisible(true);
    }

    public void close(){
        bookListPanel.setVisible(false);
    }

    public void list(){
        //mysql.listBooks(view);
    }




}
