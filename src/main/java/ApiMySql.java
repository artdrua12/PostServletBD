
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.DatabaseMetaData;

import scala.collection.mutable.HashMap;
import com.mysql.jdbc.Driver;

public class ApiMySql {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String username = "root";
    private static final String password = "1234567";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static java.sql.Statement stmt;
    private static PreparedStatement pstmt;
    private static ResultSet rs;

    public static Boolean createDB() {
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }

        String crtDB = "CREATE SCHEMA IF NOT EXISTS mydb DEFAULT CHARACTER SET utf8 ;";
        String crtusers = "CREATE TABLE IF NOT EXISTS mydb.users (\n" +
                " id INT NOT NULL AUTO_INCREMENT,\n" +
                " personName VARCHAR(45) NULL,\n" +
                " personPassword VARCHAR(45) NULL,\n" +
                "PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;";
        try {
            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement();
            con.setAutoCommit(false); // атомарная операция
            System.out.println(stmt.executeUpdate(crtDB));
            stmt.executeUpdate(crtusers);
            con.commit(); // подтверждение операции
            con.setAutoCommit(true); // атомарная операция конец
            return true;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return false;
        }
    }

    // проверка существует ли таблица
    public static boolean isTableExists(String tableName) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        DatabaseMetaData meta = (DatabaseMetaData) con.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, tableName, new String[] { "TABLE" });
        return resultSet.next();
    }

    public static void insertUsers(String personName, String personPassword) {
        String query = "INSERT INTO mydb.users (personName, personPassword) VALUES (?, ?)";
        try {
            con = DriverManager.getConnection(url, username, password);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personName);
            pstmt.setString(2, personPassword);
            pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public static HashMap<String, String> readUsers() {
        String query = "select * from mydb.users;";
        HashMap<String, String> persons = new HashMap<>();
        try {
            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(1);
                String personName = rs.getString(2);
                String personPassword = rs.getString(3);
                System.out.printf("id: %d, personName: %s , personPassword: %s %n", id, personName, personPassword);
                persons.put(personName, personPassword);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return persons;
    }

    public static void closeConnectDB() {
        try {
            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
