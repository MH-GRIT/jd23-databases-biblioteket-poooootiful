import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class Gui implements ActionListener {
    //Frames
    JFrame LoginFrame = new JFrame("Login");
    JFrame ProfileFrame = new JFrame("Profile");
    JFrame Library = new JFrame("Fulkoping");
    static JFrame SearchFrame = new JFrame("Search Results");
    static JFrame StatusFrame = new JFrame("Status");
    static JFrame HistoryFrame = new JFrame("History");
    static JFrame BorrowFrame = new JFrame("Borrow");

    //TextFields/PasswordField
    static JTextField Username = new JTextField();
    static JPasswordField Password = new JPasswordField();
    static JTextField input = new JTextField();
    static JTextField idinput = new JTextField();
    JTextField name = new JTextField();
    JTextField email = new JTextField();
    JTextField password = new JPasswordField();
    JTextField SearchBox = new JTextField();

    //Labels

    static int id;

    static  JLabel ThingLabel = new JLabel();

    static ArrayList<Integer> numbers = new ArrayList<>();

    static ArrayList<String> types = new ArrayList<>();


    static JButton ConfirmBorrow = new JButton("ConfirmBorrow");

    static String [] choises = {"Book","Media"};

    static JComboBox chose = new JComboBox<String>(choises);

    static JLabel info = new JLabel("Pick a id of either a book or other to borrow");

    static JFrame returnFrame = new JFrame("Return");

    static JButton ConfirmReturn = new JButton("ConfirmReturn");


    Gui () {
        Library.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Library.setLayout(new GridLayout(10,6));
        Library.setSize(800,500);
        Library.setVisible(true);

        SearchBox.setVisible(true);
        Library.add(SearchBox);

        JButton SearchBtn = new JButton("Search");
        Library.add(SearchBtn);
        SearchBtn.setVisible(true);
        SearchBtn.addActionListener(this);

        JButton LoginBtn = new JButton("Login");
        Library.add(LoginBtn);
        LoginBtn.setVisible(true);
        LoginBtn.addActionListener(this);

        JButton Profile = new JButton("Profile");
        Library.add(Profile);
        Profile.setVisible(true);
        Profile.addActionListener(this);

        JButton Status = new JButton("Status");
        Library.add(Status);
        Status.setVisible(true);
        Status.addActionListener(this);

        JButton History = new JButton("History");
        Library.add(History);
        History.setVisible(true);
        History.addActionListener(this);

        JButton Borrow = new JButton("Borrow");
        Library.add(Borrow);
        Borrow.setVisible(true);
        Borrow.addActionListener(this);

        JButton Return = new JButton("Return");
        Library.add(Return);
        Return.setVisible(true);
        Return.addActionListener(this);
        ConfirmBorrow.addActionListener(this);
        chose.addActionListener(this);
        ConfirmReturn.addActionListener(this);
        idinput.setPreferredSize(new Dimension(20,20));

        JLabel Info = new JLabel("If a button don't do anything it means you need to log in");
        Library.add(Info);
    }

    public static void ResultGui(String id, String name,String type) {
        SearchFrame.setSize(250,250);
        //Label for id + name
        ThingLabel.setText(type+" with the name "+name+" and with id "+id);
        SearchFrame.add(ThingLabel);
        SearchFrame.setVisible(true);
    }

    public void LoginWindow() {
        LoginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        LoginFrame.setSize(250,100);
        LoginFrame.setVisible(true);
        LoginFrame.setLayout(new GridLayout(3,1));

        LoginFrame.add(Username);
        LoginFrame.add(Password);

        JButton Login = new JButton("Log In");
        LoginFrame.add(Login);
        Login.addActionListener(this);
    }

    public void Profile (int id) {
        if(id>0) {
            System.out.println("Profile");

            JLabel ChangeName = new JLabel("Change Name");
            JLabel ChangeEmail = new JLabel("Change Email");
            JLabel ChangePassword = new JLabel("Change Password");
            JLabel warning = new JLabel("Remember to write in all 3 boxes");

            ProfileFrame.setSize(200,200);
            ProfileFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            ProfileFrame.setLayout(new GridLayout(4,3));

            ProfileFrame.add(ChangeName);
            ProfileFrame.add(name);
            ProfileFrame.add(ChangeEmail);
            ProfileFrame.add(email);
            ProfileFrame.add(ChangePassword);
            ProfileFrame.add(password);
            ProfileFrame.add(warning);
            JButton confirm = new JButton("Confirm");
            confirm.addActionListener(this);
            ProfileFrame.add(confirm);

            ProfileFrame.setVisible(true);


        }else {
            ProfileFrame.setSize(200,200);
            ProfileFrame.setVisible(true);
            JLabel NotLoggedIn = new JLabel("You need to log in first");
            ProfileFrame.add(NotLoggedIn);
        }
    }

    public static void StatusGui (int id, Date borrowedDate, Date lastReturnDate, String type) {

        StatusFrame.setSize(650,250);
        StatusFrame.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel idLabel = new JLabel(" with id " + id);
        JLabel borrowLabel = new JLabel(" that was borrowed on the " + borrowedDate);
        JLabel typeLabel = new JLabel("There is a " + type);
        JLabel lastReturnLabel = new JLabel(" and the last day to return it is/was " + lastReturnDate);


        StatusFrame.add(typeLabel);
        StatusFrame.add(idLabel);
        StatusFrame.add(borrowLabel);
        StatusFrame.add(lastReturnLabel);

        StatusFrame.setVisible(true);
    }

    public static void HistoryGui (int id,String type ,Date borrowedDate, Date ReturnDate, Date lastReturnDate) {

        HistoryFrame.setSize(625,250);
        HistoryFrame.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel idLabel = new JLabel(" with id " + id);
        JLabel borrowLabel = new JLabel(" that was borrowed on the " + borrowedDate);
        JLabel typeLabel = new JLabel("There is a " + type);
        JLabel returnday = new JLabel(" was returned on " +ReturnDate);

        HistoryFrame.add(typeLabel);
        HistoryFrame.add(idLabel);
        HistoryFrame.add(borrowLabel);
        HistoryFrame.add(returnday);

        HistoryFrame.setVisible(true);
    }

    public static void clearStatusFrame (){
        StatusFrame.getContentPane().removeAll();
        StatusFrame.revalidate();
        StatusFrame.repaint();
    }

    public static void clearHistoryFrame (){
        HistoryFrame.getContentPane().removeAll();
        HistoryFrame.revalidate();
        HistoryFrame.repaint();
    }

    public static void clearSearchFrame () {
        SearchFrame.getContentPane().removeAll();
        SearchFrame.revalidate();
        SearchFrame.repaint();
    }

    public static void clearReturnFrame () {
        returnFrame.getContentPane().removeAll();
        returnFrame.revalidate();
        returnFrame.repaint();
    }

    public static void clearBorrowFrame () {
        BorrowFrame.getContentPane().removeAll();
        BorrowFrame.revalidate();
        BorrowFrame.repaint();
    }

    public static void borrowGui(){

        BorrowFrame.setSize(600,100);
        BorrowFrame.setLayout(new GridLayout(1,4));

        if (id>0) {
            BorrowFrame.add(info);
            BorrowFrame.add(input);
            BorrowFrame.add(chose);
            BorrowFrame.add(ConfirmBorrow);
            BorrowFrame.setVisible(true);

        }else {
            JLabel Label = new JLabel("You need to logged in to borrow books");
            BorrowFrame.add(Label);
            BorrowFrame.setVisible(true);

        }
    }

    public static void returnGui (int typeid, String type){
        returnFrame.setSize(250,600);
        returnFrame.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel types = new JLabel("You have a "+type+" borrowed");
        JLabel with = new JLabel("with id");
        JLabel id = new JLabel(String.valueOf(typeid));
        returnFrame.add(types);
        returnFrame.add(with);
        returnFrame.add(id);

        returnFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();

        if (btn.matches("Search")) {
            clearSearchFrame();

            String term = SearchBox.getText();

            GetInfo.BookSearch(term);
            GetInfo.AuthorSearch(term);
            GetInfo.OtherSearch(term);

        }
        else if (btn.matches("Login")) {
            LoginWindow();
        }
        if (btn.matches("Log In")) {
            String username = Username.getText();
            String password = Password.getText();
            id = GetInfo.Login(username, password);
            System.out.println(id);
            if (id>0) {
                LoginFrame.setVisible(false);
            }
        }
        else if (btn.matches("Profile")) {
            Profile(id);
        }
        if (btn.matches("Confirm")){
            GetInfo.UpdateLogin(name.getText(), email.getText(), password.getText(), id);
            ProfileFrame.setVisible(false);
        }
        else if (btn.matches("Status")) {
            clearStatusFrame();
            GetInfo.AllStatus(id);
        }
        else if (btn.matches("History")) {
            clearHistoryFrame();
            GetInfo.getHistory(id);
        }
        else if (btn.matches("Borrow")) {
            clearBorrowFrame();
            numbers.clear();
            types.clear();
            //Function to get all the unavalible ints
            GetInfo.getAllInts();

        }if (btn.matches("ConfirmBorrow")){

            String pickid = input.getText();
            String pick = chose.getSelectedItem().toString();

            if (pick.matches("Book")) {
                String type = "books";
                GetInfo.MaybeRemoveIdFromList(pickid,type,"book");
            }else if (pick.matches("Media")){
                String type = "other";
                GetInfo.MaybeRemoveIdFromList(pickid,type,"other");
            }

            System.out.println(numbers);
            System.out.println(types);
            System.out.println(id);
            System.out.println(pickid);

            int number = GetInfo.CheckIdBorrowed(pickid);

            System.out.println(number);

            if (number==-2) {
                JLabel No = new JLabel("The id has already been borrow as both a book and a media");
                BorrowFrame.add(No);
            }
            else if (number>0) {
                String type = GetInfo.checkType(number);

                if (type.matches("Book")) {
                    if (pick.matches("Book")) {
                        JLabel BorrowNo = new JLabel("You can't borrow that book right now");
                        BorrowFrame.add(BorrowNo);
                    }else {
                        GetInfo.addBorrowOther(id,pickid);
                        JLabel BorrowYes = new JLabel("You have borrowed the Media with id: "+pickid);
                        BorrowFrame.add(BorrowYes);
                    }
                }
                else if (type.matches("Media")) {
                    if (pick.matches("Media")) {
                        JLabel BorrowNo = new JLabel("You can't borrow that media right now");
                        BorrowFrame.add(BorrowNo);
                    }else {
                        GetInfo.addBorrowBook(id,pickid);
                        JLabel BorrowYes = new JLabel("You have borrowed the Book with id: "+pickid);
                        BorrowFrame.add(BorrowYes);
                    }
                }
            }
            else {
                if (pick.matches("Book")) {
                    GetInfo.addBorrowBook(id,pickid);
                }else if (pick.matches("Media")){
                    GetInfo.addBorrowOther(id,pickid);
                }
            }

        }
        else if (btn.matches("Return")) {
            clearReturnFrame();
            returnFrame.add(idinput);
            returnFrame.add(chose);
            returnFrame.add(ConfirmReturn);
            GetInfo.getWhatYouBorrowed(id);

        }if (btn.matches("ConfirmReturn")) {
            String inputid = idinput.getText();
            String type = chose.getSelectedItem().toString();
            GetInfo.returns(id,type,inputid);
        }
    }
}