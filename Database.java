import com.mysql.cj.jdbc.MysqlDataSource;

public class Database {

    //Database Configuration

    static String url = "localhost";
    static int port = 3306;
    static String database = "FulköpingLibrary";
    static String username = "root";
    static String password = "8462";

    //Private variables

    private static Database db;

    private MysqlDataSource dataSource;

    private void DatabaseConnect () {
        dataSource = new MysqlDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL("jdbc:mysql://" + url + ":" + port + "/" + database + "?serverTimezone=UTC");
    }

    Database() {
        DatabaseConnect();
    }
}