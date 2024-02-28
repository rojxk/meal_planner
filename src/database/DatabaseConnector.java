package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = System.getenv("URL");
    private static final String USER = System.getenv("USER");
    private static final String PASSWORD = System.getenv("PASSWORD");

//    public static Connection connect() {
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Connected to the PostgreSQL server successfully.");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return conn;
//    }



    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void doDatabaseOperation() {
        // Using try-with-resources to ensure the connection is closed after use
        try (Connection conn = DatabaseConnector.connect()) {
            if (conn != null) {
                // Perform database operations here
                System.out.println("Performing database operations...");
            }
        } catch (SQLException e) {
            System.out.println("database.Database connection problem: " + e.getMessage());
        }
    }
}
