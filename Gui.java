import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class Gui implements ActionListener {

    static JTextField Username = new JTextField();
    static JPasswordField Password = new JPasswordField();
    JFrame LoginFrame = new JFrame("Login");
    static JFrame Library = new JFrame("Fulkoping");

    JFrame ProfileFrame = new JFrame("Profile");
    JTextField name = new JTextField();
    JTextField email = new JTextField();
    JTextField password = new JPasswordField();
    public static JTextField Errors = new JTextField();
    JTextField SearchBox = new JTextField();

    static JFrame SearchFrame = new JFrame("Search Results");

    static JFrame StatusFrame = new JFrame("Status");

    static JFrame HistoryFrame = new JFrame("History");

    static int id;

    static  JLabel ThingLabel = new JLabel();

    static ArrayList<Integer> numbers = new ArrayList<>();

    static ArrayList<String> types = new ArrayList<>();

    static JFrame BorrowFrame = new JFrame("Borrow");

    static JTextField input = new JTextField();

    static JButton ConfirmBorrow = new JButton("ConfirmBorrow");


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
            ProfileFrame.setVisible(true);
            JLabel NotLoggedIn = new JLabel("You need to log in first");
            ProfileFrame.add(NotLoggedIn);
        }
    }

    public static void StatusGui (int id, Date borrowedDate, Date lastReturnDate, String type) {

        StatusFrame.setSize(650,250);
        StatusFrame.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel idLabel = new JLabel(", with id " + id);
        JLabel borrowLabel = new JLabel(", that was borrowed on the " + borrowedDate);
        JLabel typeLabel = new JLabel("This is a " + type);
        JLabel lastReturnLabel = new JLabel(", and the last day to return it is/was " + lastReturnDate);
        JLabel returnday = new JLabel("");

        StatusFrame.add(typeLabel);
        StatusFrame.add(idLabel);
        StatusFrame.add(borrowLabel);
        StatusFrame.add(lastReturnLabel);

        StatusFrame.setVisible(true);
    }

    public static void HistoryGui (int id,String type ,Date borrowedDate, Date ReturnDate, Date lastReturnDate) {

        HistoryFrame.setSize(1100,250);
        HistoryFrame.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel idLabel = new JLabel(", with id " + id);
        JLabel borrowLabel = new JLabel(", that was borrowed on the " + borrowedDate);
        JLabel typeLabel = new JLabel("This is a " + type);
        JLabel returnday = new JLabel(", was returned on " +ReturnDate);

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

    public static void borrowGui(){

        BorrowFrame.setSize(600,600);
        BorrowFrame.setLayout(new GridLayout(2,2));
        BorrowFrame.add(input);

        if (id>0) {
            BorrowFrame.add(input);
            BorrowFrame.add(ConfirmBorrow);
            BorrowFrame.setVisible(true);

        }else {
            JLabel Label = new JLabel("You need to logged in to borrow books");
            BorrowFrame.add(Label);
            BorrowFrame.setVisible(true);

        }
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
            //Function to get all the unavalible ints
            GetInfo.getAllInts();
        }if (btn.matches("ConfirmBorrow")){
            String id = input.getText();
            System.out.println(numbers);
            System.out.println(types);
            System.out.println(id);

        }
        else if (btn.matches("Return")) {
            
        }
    }
}