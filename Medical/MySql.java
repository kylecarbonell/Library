package Medical;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class MySql {
    Connection con;

    MySql(){
        loadDriver();
    }

    //Create connection to library database
    public void loadDriver(){
        //Creates a user specific database
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "dazaichan";
        String password = "lu2nglu2";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to databse");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Lists out all books onto table
    public void listBooks(DefaultTableModel list){
        try {
            String sql = "SELECT * FROM books";
            Statement stmnt = con.createStatement();
            ResultSet result = stmnt.executeQuery(sql);

            while(result.next()){
                String bookId = String.valueOf(result.getInt("ID"));
                String bookName = result.getString("name");
                String authorName = result.getString("author");
                String bookQuantity = String.valueOf(result.getInt("Quantity"));
                String[] row = {bookId, bookName, authorName, bookQuantity};
                
                list.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Login to database
    public int login(String user, String pass, JLabel error){
        String findLogin = "SELECT * FROM logins";
        
        try {
            Statement stmnt = con.createStatement();
            ResultSet result = stmnt.executeQuery(findLogin);

            while(result.next()){
                String username = result.getString("username");
                String password = result.getString("password");

                
                if(username.equals(user) && password.equals(pass)){
                    return result.getInt("id");
                }

                if((username.equals(user) && !password.equals(pass)) || (!username.equals(user) && password.equals(pass))){
                    error.setText("Username or Password is incorrect");
                }
                else{
                    error.setText("User not found");
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 1;
       
    }

    //Gets user id
    public void getUser(int id){
        String get = "SELECT * FROM users WHERE id = " + id;
        
        try {
            Statement stmnt = con.createStatement();
            ResultSet result = stmnt.executeQuery(get);
            
            while(result.next()){
                int userId = result.getInt("ID");
                String username = result.getString("username");
                String password = result.getString("password");
                if(userId == id){
                    System.out.println(username);
                    System.out.println(password);
                    System.out.println(userId);
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Creates a new account
    public void makeAccount(String[] info){
        String addUsers = "INSERT INTO users VALUES (null";
        for (String string : info) {
            addUsers += ", '" + string + "'";
        }
        addUsers += ")";

        String addLogins = "INSERT INTO logins VALUES(null, '" + info[2] + "' , '" + info[3] + "')";
        String addInventory = "INSERT INTO inventory (id) SELECT MAX(id) FROM users";
        
        
        try {
            Statement stmnt = con.createStatement();
            stmnt.execute(addUsers);
            stmnt.execute(addLogins);
            stmnt.execute(addInventory);


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Search book function
    public void searchBooks(String word, DefaultTableModel model){
        String search = "SELECT * FROM books WHERE name LIKE '%" + word + "%' OR author LIKE '%" + word + "%'";
        
        try {
            Statement stmnt = con.createStatement();
            ResultSet result = stmnt.executeQuery(search);

            while(result.next()){
                String bookId = String.valueOf(result.getInt("ID"));
                String bookName = result.getString("name");
                String authorName = result.getString("author");
                String bookQuantity = String.valueOf(result.getInt("Quantity"));
                String[] row = {bookId, bookName, authorName, bookQuantity};
                
                model.addRow(row);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //Checks for open book spots and checks in new book
    public void checkoutBooks(int bookId, JLabel error){
        String openSpot;
        String findNull = "Select case " +
        " when book1ID is null then 'book1ID'" +
        " when book2ID is null then 'book2ID'" +
        " when book3ID is null then 'book3ID'"+
        " when book4ID is null then 'book4ID'"+
        " else 'No space'" +
        " end as 'case' from inventory where id = " + GUI.userId;

        String book = "SELECT book1ID, book2ID, book3ID, book4ID FROM inventory WHERE id = " + GUI.userId;

        String getQuantity = "SELECT quantity FROM books WHERE id = " + bookId;
            
        try {
            Statement stmnt = con.createStatement();
            
            ResultSet amountResultSet = stmnt.executeQuery(getQuantity);
            amountResultSet.next();
            int quantity = amountResultSet.getInt(1);

            if(quantity <= 0){
                error.setText("No more books in stock");
                return;
            }

            ResultSet find = stmnt.executeQuery(findNull);
            String addBook;

            //Implement check Return system
            find.next();
            openSpot = find.getString("case");

            //Set to 0 for test purposes -> GUI.userId
            if(!openSpot.equals("No space")){
                ResultSet findBook = stmnt.executeQuery(book);
                while(findBook.next()){
                    for(int i = 1; i <= 4; i++){
                        if(bookId == findBook.getInt(i)){
                            System.out.println(findBook.getInt(i));
                            error.setText("You already have this book!");
                            return;
                        }
                    }
                }

                addBook = "UPDATE inventory SET " + openSpot + " = " + bookId + " WHERE id = " + GUI.userId;
                stmnt.execute(addBook);

                quantity -= 1;
                String setQuantity = "UPDATE books SET quantity = " + quantity + " WHERE id = " + bookId;
                stmnt.execute(setQuantity);
                error.setText("Checkout Complete!");
            }
            else{
                error.setText("Please turn in your books before checking out new ones");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void wishlistBooks(int bookId, JLabel text){
        String wishlist = "INSERT INTO wishlist VALUES(" + bookId + ", " + GUI.userId + ")";
        String check = "SELECT bookId FROM wishlist WHERE userId = " + GUI.userId;
        try {
            Statement stmnt = con.createStatement();
            ResultSet result = stmnt.executeQuery(check);
            while(result.next()){
                if(result.getInt("bookId") == bookId){
                    text.setText("This book is already in your wishlist!");
                    return;
                }
            }
            stmnt.execute(wishlist);

            text.setText("Added to wishlist!");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //Lists all checkedout books of the user
    public void viewCheckedOutBooks(DefaultTableModel model){
        String checkedOutBooks = 
        "SELECT books.id, books.name, books.author " + 
        "FROM books " + 
        "JOIN inventory inv " +
        "ON inv.book1ID = books.id OR inv.book2ID = books.id OR inv.book3ID = books.id OR inv.book4ID = books.id " + 
        "WHERE inv.id = " + GUI.userId;

        try {
            Statement stmnt = con.createStatement();
            ResultSet result = stmnt.executeQuery(checkedOutBooks);

            while(result.next()){
                String bookId = String.valueOf(result.getInt("ID"));
                String bookName = result.getString("name");
                String authorName = result.getString("author");
                String[] row = {bookId, bookName, authorName};
                
                model.addRow(row);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Lists all wishlist books of the user
    public void viewWishlistBooks(DefaultTableModel model){
        String wishlistBooks = 
        "SELECT books.id, books.name, books.author " +
        "FROM books " + 
        "JOIN wishlist wish " +
        "ON wish.bookId = books.id " + 
        "WHERE wish.userId = " + GUI.userId +
        " ORDER BY bookId ASC";

        try {
            Statement stmnt = con.createStatement();
            ResultSet result = stmnt.executeQuery(wishlistBooks);

            while(result.next()){
                String bookId = String.valueOf(result.getInt("ID"));
                String bookName = result.getString("name");
                String authorName = result.getString("author");
                String[] row = {bookId, bookName, authorName};
                
                model.addRow(row);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //Returns books from a users inventory and adds it back into the booklist
    public void turnInBooks(int bookId, JLabel text){
        
        String turnIn = "Select case " +
        " when book1ID = " + bookId + " then 'book1ID'" +
        " when book2ID = " + bookId + " then 'book2ID'" +
        " when book3ID = " + bookId + " then 'book3ID'"+
        " when book4ID = " + bookId + " then 'book4ID'"+
        " else 'No space'" +
        " end as 'case' from inventory where id = " + GUI.userId;
        String getQuantity = "SELECT quantity FROM books WHERE id = " + bookId;

        
        try {
            String thisBook = "";
            String returnBook = "";
            String updateQuantity = "";
            int quantity = 0;
            Statement stmnt = con.createStatement();
            ResultSet findBook = stmnt.executeQuery(turnIn);
            
            if(findBook.next()){
                thisBook = findBook.getString(1);
                returnBook = "UPDATE inventory SET " + thisBook + " = null WHERE id = " + GUI.userId;
            }
            
            ResultSet findQuantity = stmnt.executeQuery(getQuantity);
            if(findQuantity.next()){
                quantity = findQuantity.getInt("quantity");
                quantity += 1;
                updateQuantity = "UPDATE books SET quantity = " + quantity + " WHERE id = " + bookId;
            }

            if(stmnt.execute(returnBook) && stmnt.execute(updateQuantity)){
                text.setText("Book returned");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void removeFromWishlist(int bookId, JLabel text){
        String removeWishlist = "DELETE FROM wishlist WHERE bookID = " + bookId + " AND userID = " + GUI.userId;

        try {
            Statement stmnt = con.createStatement();

            if(stmnt.execute(removeWishlist)){
                text.setText("Book removed");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Publishes a book and adds it to the book 
    public void publishBook(JLabel error, String[] info, int quantity){
        String publish = "INSERT INTO books VALUES (null";
        for(String i : info){
            publish += ", '" + i + "'";
        }
        publish += ", " + quantity + ")";

        try {
            Statement stmnt = con.createStatement();
            if(stmnt.execute(publish)){
                error.setText("Your book has been published!");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
