import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
public class Main {

    public static void main(String[] args) {
        new Gui();

        Database.username = "root";
        Database.password = "8462";
        Database.port = 3306;
        Database.database = "FulkopingLibrary";

        Connection connection = Database.getConnection();
        if (connection == null) {
            System.out.println("Could not connect");
            System.exit(-1);
            return;
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("DESCRIBE users");
            while (result.next()) {
                System.out.println(result.getString("Username "));
            }
        } catch(SQLException ex) {
            Database.PrintSQLException(ex);
        }

        try {
            connection.close();
        } catch (SQLException ex) {
            Database.PrintSQLException(ex);
        }

    }
}