package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// file đg dân JDBC  vào Mysql
public class JDBCutil{
    public static Connection connectJDBC() {
        final String url = "jdbc:mysql://localhost:3306/LibraryManagement";
        final String user ="root";
        final String password ="Nhansu123@";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,user,password);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
