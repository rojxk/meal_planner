package database;

import java.sql.*;

/**
 * Utility class that provides static methods to interact with the database. These methods facilitate executing
 * SQL queries and updates efficiently using functional interfaces to set parameters and handle result sets.
 */
public class DatabaseUtils {

    /**
     * Executes a SQL query and returns the result processed by a {@link ResultSetExtractor}.
     * This method establishes a connection, prepares a statement, sets its parameters, executes the query,
     * and finally extracts data from the result set using the provided extractor.
     *
     * @param <T> The type of the object returned by the result set extractor.
     * @param sql The SQL query to execute.
     * @param pss An implementation of {@link PreparedStatementSetter} to set parameters on the prepared statement.
     * @param rse An implementation of {@link ResultSetExtractor} to extract data from the resulting ResultSet.
     * @return The object extracted from the ResultSet.
     * @throws RuntimeException If a SQLException is encountered during database operations.
     */
    public static <T> T executeQuery(String sql, PreparedStatementSetter pss, ResultSetExtractor<T> rse) {
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pss.setParameters(pstmt);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rse.extractData(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Executes an SQL update (INSERT, UPDATE, or DELETE) and returns the number of rows affected.
     * This method establishes a connection, prepares a statement, sets its parameters, and executes the update.
     *
     * @param sql The SQL update statement to execute.
     * @param pss An implementation of {@link PreparedStatementSetter} to set parameters on the prepared statement.
     * @return The number of rows affected by the update.
     * @throws RuntimeException If a SQLException is encountered during database operations.
     */
    public static int executeUpdate(String sql, PreparedStatementSetter pss) {
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pss.setParameters(pstmt);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Executes a SQL query and processes each row in the ResultSet using a {@link ResultSetHandler}.
     * This method establishes a connection, prepares a statement, sets its parameters, executes the query,
     * and then iteratively handles each row in the result set using the provided handler.
     *
     * @param sql The SQL query to execute.
     * @param pss An implementation of {@link PreparedStatementSetter} to set parameters on the prepared statement.
     * @param rsh An implementation of {@link ResultSetHandler} to handle each row of the resulting ResultSet.
     * @throws RuntimeException If a SQLException is encountered during database operations.
     */
    public static void executeQueryWithHandler(String sql, PreparedStatementSetter pss, ResultSetHandler rsh) {
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pss.setParameters(pstmt);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    rsh.handle(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
