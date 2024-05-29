import java.sql.*;
import java.util.Date;

public class GetInfo {

    public static void BookSearch (String term) {
        Connection connection = Database.getConnection();
        if (connection == null) {
            System.out.println("Could not connect");
            Gui.Errors.setText("Could not Connect");
            System.exit(-1);
        }
        String BookSearch = "SELECT name, id FROM books WHERE name LIKE '"+term+"'";
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(BookSearch);

            while (rs.next()){
                String books = rs.getString("name");
                String bookId = rs.getString("id");
                Gui.ResultGui(bookId,books,"Book");
            }
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void OtherSearch (String term) {
        Connection connection = Database.getConnection();
        if (connection == null) {
            System.out.println("Could not connect");
            Gui.Errors.setText("Could not Connect");
            System.exit(-1);
        }
        String OtherSearch = "SELECT name, id FROM other WHERE name LIKE '"+term+"'";
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(OtherSearch);

            while (rs.next()){
                String others = rs.getString("name");
                String OtherId = rs.getString("id");
                Gui.ResultGui(OtherId,others, "Media");
            }

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void AuthorSearch (String term) {
        Connection connection = Database.getConnection();
        if (connection == null) {
            System.out.println("Could not connect");
            Gui.Errors.setText("Could not Connect");
            System.exit(-1);
        }
        String AuthorSearch = "SELECT name, id FROM authors WHERE name LIKE '"+term+"'";
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(AuthorSearch);

            while (rs.next()){
                String AuthorName = rs.getString("name");
                String AuthorId = rs.getString("id");
                Gui.ResultGui(AuthorId,AuthorName, "Author");
            }

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static int Login(String Username, String Password) {

        Connection connection = Database.getConnection();

        if (connection == null) {
            System.out.println("Could not connect");
            Gui.Errors.setText("Could not Connect");
            System.exit(-1);
        }

        try {
            //Get id for who logged in
            String sql = "SELECT id FROM users WHERE Username = \"" + Username + "\" and password = \"" + Password + "\";";

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            if (!rs.next()) {
                System.out.println("User not found");
                return 0;
            }
            int id = rs.getInt("id");
            return id;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void getHistory (int userid){
        BookHistory(userid);
        OtherHistory(userid);
    }

    public static void BookHistory (int userid) {
        Connection connection = Database.getConnection();

        if (connection == null) {
            System.out.println("Could not connect");
            Gui.Errors.setText("Could not Connect");
            System.exit(-1);
        }
        String sql = "SELECT bookid, borrowedday,ruturnday, lastreturnday from borrowedbooks WHERE userid ="+userid;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String book = "Book";
                Date returnday = resultSet.getDate("ruturnday");
                if (returnday != null) {
                    int bookid = resultSet.getInt("bookid");
                    Date borrowedday = resultSet.getDate("borrowedday");
                    Date lastreturn = resultSet.getDate("lastreturnday");


                    Gui.HistoryGui(bookid,book,borrowedday,returnday,lastreturn);

                } else if (returnday == null){
                    System.out.println("There is a returnday that = null in book history");
                }
            }

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void OtherHistory (int userid) {
        Connection connection = Database.getConnection();

        if (connection == null) {
            System.out.println("Could not connect");
            Gui.Errors.setText("Could not Connect");
            System.exit(-1);
        }
        String sql = "SELECT otherid, borrowedday,ruturnday, lastreturnday from borrowedother WHERE userid ="+userid;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String other = "other";
                Date returnday = resultSet.getDate("ruturnday");
                if (returnday != null) {
                    int otherid = resultSet.getInt("otherid");
                    Date borrowedday = resultSet.getDate("borrowedday");
                    Date lastreturn = resultSet.getDate("lastreturnday");

                    Gui.HistoryGui(otherid,other,borrowedday,returnday,lastreturn);

                } else if (returnday == null){
                    System.out.println("There is returnday that = null result in other history");
                }
            }

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void UpdateLogin (String name, String email, String password, int id) {

        Connection connection = Database.getConnection();

        if (connection == null) {
            System.out.println("Could not connect");
            Gui.Errors.setText("Could not Connect");
            System.exit(-1);
        }
        System.out.println(name);
        System.out.println(email);
        System.out.println(password);

        String UpdateAll = "UPDATE users SET Username = '"+name+"', email = '"+email+"', password = '"+password+"' WHERE id ="+id;
        System.out.println(UpdateAll);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(UpdateAll);

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void AllStatus (int userId){
        BookStatus(userId);
        OtherStatus(userId);
    }

    public static void BookStatus (int userId) {
        Connection connection = Database.getConnection();
        String sql = "SELECT bookid, borrowedday, ruturnday, lastreturnday FROM borrowedbooks WHERE userid = "+userId+";";
        String book = "Book";
        try {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

           while (result.next()){
               int bookid = result.getInt("bookid");
               Date borrowedday = result.getDate("borrowedday");
               Date ruturnday = result.getDate("ruturnday");
               Date lastreturnday = result.getDate("lastreturnday");

               Gui.StatusGui(bookid, borrowedday,lastreturnday,book);

           }

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void OtherStatus (int userId) {
        Connection connection = Database.getConnection();
        String sql = "SELECT otherid, borrowedday, ruturnday, lastreturnday FROM borrowedother WHERE userid = "+userId+";";
        String media = "Media";
        try {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);


            while (result.next()){
                int otherid = result.getInt("otherid");
                Date borrowedday = result.getDate("borrowedday");
                Date ruturnday = result.getDate("ruturnday");
                Date lastreturnday = result.getDate("lastreturnday");

                Gui.StatusGui(otherid, borrowedday,lastreturnday,media);

            }

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public static void getAllInts () {
        getBookInts();
        getOtherInts();
    }

    public static void getBookInts () {
        Connection connection = Database.getConnection();
        String sql = "SELECT bookid FROM borrowedbooks";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                int id =result.getInt("bookid");
                String type = "book";
                Gui.numbers.add(id);
                Gui.types.add(type);
            }
            Gui.borrowGui();

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public static void getOtherInts () {
        Connection connection = Database.getConnection();
        String sql = "SELECT otherid FROM borrowedother";
        try {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                int id =result.getInt("otherid");
                String type = "Media";
                Gui.numbers.add(id);
                Gui.types.add(type);
            }
            Gui.borrowGui();

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}