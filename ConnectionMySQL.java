import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
    public static String status = "Não conectou";

    public ConnectionMySQL() {
    }

    public static Connection getConnectionMySQL() {
        Connection connection = null;
        String driverName = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException var9) {
            ClassNotFoundException e = var9;
            e.printStackTrace();
        }

        String server = "localhost";
        String db = "n2poo";
        String url = "jdbc:mysql://" + server + "/" + db;
        String username = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, username, password);
            status = "conectado";
        } catch (SQLException var8) {
            SQLException e = var8;
            e.printStackTrace();
        }

        return connection;
    }
}
