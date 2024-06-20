package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/projeto";
    private static final String user = "root";
    private static final String password = "Anthony8818@";

    private static Connection conn;

    public static Connection getConexao() {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
