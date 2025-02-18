import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/lib";
    private static final String USER = "root"; // Ändra till ditt användarnamn
    private static final String PASSWORD = "7JdB@981234&1"; // Ändra till ditt lösenord

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}