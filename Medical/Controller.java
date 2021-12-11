package Medical;

public class Controller{
    static Login login = new Login();
    static Home home = new Home();
    static BookList bookList = new BookList();
    static CreateAccount create = new CreateAccount();
    static TurnIn yb = new TurnIn();
    static PublishBook publish = new PublishBook();

    Controller(){
        closeAll();
    }

    public void closeAll(){
        login.close();
        home.close();
        bookList.close();
        create.close();
        yb.close();
        publish.close();
    }

    //Takes a parameter to find which panel is being opened
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
        else if(open.equals("CreateAccount")){
            create.open();
        }
        else if(open.equals("YourBooks")){
            yb.open();
        }
        else if(open.equals("Publish")){
            publish.open();
        }
    }
}
