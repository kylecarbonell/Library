package Medical;

public class Controller{
    static Login login = new Login();
    static Home home = new Home();
    static BookList bookList = new BookList();

    Controller(){
        System.out.println("Working");
    }

    public void closeAll(){
        login.close();
        home.close();
        bookList.close();
    }

    public void open(String open){
        closeAll();
        if(open.equals("Login")){
            login.open();
        }
        else if(open.equals("Home")){
            home.open();
        }
        else if(open.equals("BookList")){
            bookList.open();
        }
    }
}
