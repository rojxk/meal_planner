package database;

import java.sql.*;

public class DatabaseConnector {

    private static final String URL = System.getenv("URL");
    private static final String USER = System.getenv("USER");
    private static final String PASSWORD = System.getenv("PASSWORD");

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

//    public static void doDatabaseOperation() {
//        // Using try-with-resources to ensure the connection is closed after use
//        try (Connection conn = DatabaseConnector.connect()) {
//            if (conn != null) {
//                // Perform database operations here
//                System.out.println("Performing database operations...");
//            }
//        } catch (SQLException e) {
//            System.out.println("database.Database connection problem: " + e.getMessage());
//        }
//    }
//
//    public static void selectAllFromCategory() {
//        String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'public' AND table_type = 'BASE TABLE'";
//        try (Connection conn = DatabaseConnector.connect();
//             PreparedStatement pstmt = conn.prepareStatement(query);
//             ResultSet rs = pstmt.executeQuery()) {
//
//            if (rs.next()) { // Move the cursor to the first (and only) row of the result set
//                int tableCount = rs.getInt(1); // Retrieve the count from the first column of the result set
//                System.out.println("Number of tables in the 'public' schema: " + tableCount);
//            } else {
//                System.out.println("Could not retrieve the table count.");
//            }
//
//        } catch (SQLException e) {
//            System.err.println("Error executing SELECT query: " + e.getMessage());
//        }
//    }
//
//
//    public static void doDatabaseOperation2() {
//        // Using try-with-resources to ensure the connection is closed after use
//        try (Connection conn = DatabaseConnector.connect()) {
//            if (conn != null) {
//                // Define SQL query
//                String query = "SELECT * FROM category";
//                // Create a PreparedStatement
//                try (PreparedStatement pstmt = conn.prepareStatement(query);
//                     ResultSet rs = pstmt.executeQuery()) {
//                    // Process the ResultSet
//                    while (rs.next()) {
//                        // Assuming 'id' and 'name' as columns in your 'category' table
//                        String name = rs.getString("category_name");
//                        System.out.println("Name: " + name);
//                    }
//                } catch (SQLException e) {
//                    System.out.println("Query execution problem: " + e.getMessage());
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Database connection problem: " + e.getMessage());
//        }
//    }
}
