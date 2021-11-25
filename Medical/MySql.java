package Medical;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JViewport;

public class MySql {

    Connection con;

    MySql(){
        loadDriver();
    }

    public void loadDriver(){
            
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "lu2nglu2";
        try {
            con = DriverManager.getConnection(url, user, password);

            System.out.println("Connected to databse");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void listBooks(JViewport bookListView){
        try {
            String sql = "SELECT * FROM books";
            Statement stmnt = con.createStatement();
            ResultSet result = stmnt.executeQuery(sql);

            while(result.next()){
                String bookName = result.getString("name");
                String authorName = result.getString("author");

                JLabel book = new JLabel();
                book.setText(bookName);
                book.setForeground(Color.black);
                book.setFont(new Font("MonoSpaced", Font.PLAIN, 15));
                bookListView.add(book);

                System.out.println(bookName + " " + authorName);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
