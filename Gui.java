import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Gui implements ActionListener {

    //Login components
    JFrame LoginFrame = new JFrame("Login");
    JTextField Username = new JTextField();
    JPasswordField Password = new JPasswordField();
    JButton Login = new JButton("Login");
    JButton Search = new JButton("Search");
    JTextField SearchField = new JTextField();
    JTextArea SearchResult = new JTextArea();
    int id;

    //Login window
    public void LoginWindow() {
        LoginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        LoginFrame.setSize(250,100);
        LoginFrame.setVisible(true);
        LoginFrame.setLayout(new GridLayout(3,1));

        LoginFrame.add(Username);
        LoginFrame.add(Password);
        LoginFrame.add(Login);

        Login.addActionListener(this);
    }

    public int Login() {
        String username = Username.getText();
        String password = Password.getText();

        Connection connection = Database.getConnection();
        if (connection == null) {
            System.out.println("Could not connect");
            System.exit(-1);
            return 0;
        }

        try {
            //Get id for who logged in
            String sql = "SELECT id FROM users WHERE Username = \""+username+"\" and password = \""+password+"\";";

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            if(!rs.next()) {
                System.out.println("User not found");
                return 0;
            }
            int id1 = rs.getInt("id");
            return id1;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //The interface for the library of books and other media
    public void InterFace() {
        JFrame library = new JFrame("Library");
        library.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        library.setSize(500,500);
        library.setVisible(true);
        library.setLayout(new GridLayout(3,3));

        library.add(SearchField);
        library.add(Search);
        Search.addActionListener(this);
        library.add(SearchResult);

    }

    public void InfoCollect() {

        String SearchV = SearchField.getText();


        Connection connection = Database.getConnection();
        if (connection == null) {
            System.out.println("Could not connect");
            System.exit(-1);
            return;
        }
        try {
            //Search tru the database for the Search variable
            String sql = "SELECT name FROM books WHERE name LIKE \""+SearchV+"\";";
            String sql2 = "SELECT name FROM other WHERE name LIKE \""+SearchV+"\";\n";

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            ResultSet rs2 = statement.executeQuery(sql2);

            if(!rs.next()) {
                System.out.println("Text not found");
            }



            SearchResult.setText(rs.getString("name") + rs2.getString("name"));
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    Gui () {
        LoginWindow();

        if (id>0) {
            InterFace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();

        if (btn.matches("Login")) {
                id = Login();
                LoginFrame.setVisible(false);
            if (id>0) {
                InterFace();
            }
        }
        if (btn.matches("Search")) {
            InfoCollect();
        }
        
    }
}