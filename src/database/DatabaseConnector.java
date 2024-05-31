package database;

import java.sql.*;

/**
 * Provides a mechanism to establish connections to a database using JDBC.
 * This class fetches database credentials from environment variables and uses them
 * to connect to the configured database.
 */
public class DatabaseConnector {
    private static final String URL = System.getenv("URL");
    private static final String USER = System.getenv("USER");
    private static final String PASSWORD = System.getenv("PASSWORD");

    /**
     * Establishes a connection to the database using the configured credentials.
     * This method fetches the database URL, user, and password from environment variables
     * and attempts to create a connection to the database. It throws SQLException if unable
     * to establish a connection.
     *
     * @return A Connection object representing the connection to the database.
     * @throws SQLException If a database access error occurs or the url is null.
     */
    public static Connection connect() throws SQLException {
        // Attempt to establish a connection to the database using the credentials provided.
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
