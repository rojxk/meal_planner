package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A functional interface that defines a single method to handle a {@link ResultSet}.
 * This interface is designed for implementing logic that operates directly on a ResultSet,
 * such as processing rows, extracting data, or performing any operation that requires
 * direct interaction with the data returned from a SQL query. The use of a functional
 * interface allows this to be used with lambda expressions, enhancing the flexibility
 * and conciseness of the database interaction code.
 *
 * The handle method is intended for situations where the ResultSet needs to be processed
 * without returning a value, making it suitable for operations like data validation, logging,
 * or iterative reading and processing of each row.
 */
@FunctionalInterface
interface ResultSetHandler {

    /**
     * Processes a ResultSet as per the implementation's specific logic.
     * This method might involve iterating over the ResultSet, processing each row,
     * performing calculations, or any other operations required by the application.
     * It is critical that implementers handle the ResultSet carefully, ensuring
     * that resources are properly managed and that the ResultSet is not closed within
     * this method, as it may still be in use elsewhere.
     *
     * @param rs The ResultSet to be handled. It is the responsibility of the caller to close the ResultSet.
     * @throws SQLException If an SQL error occurs during the processing of the ResultSet.
     */
    void handle(ResultSet rs) throws SQLException;
}
