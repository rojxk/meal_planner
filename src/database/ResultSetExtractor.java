package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An interface that defines a method to extract data from a {@link ResultSet}.
 * This interface is intended to be implemented by classes that encapsulate the
 * logic necessary to extract data from the rows of a ResultSet obtained from executing
 * a SQL query. It is particularly useful for decoupling the extraction logic from
 * database operations, enhancing the modularity and testability of the code.
 *
 * @param <T> The type of object that results from the data extraction. This generic type
 *            allows implementers to define what type of object each specific extractor
 *            will return, based on the contents of the ResultSet.
 */
public interface ResultSetExtractor<T> {

    /**
     * Extracts and returns data from the given ResultSet.
     * This method is responsible for processing the ResultSet and extracting data
     * to form an object of type T. Implementors need to handle the ResultSet according
     * to their specific requirements, which may involve iterating over the ResultSet rows,
     * retrieving values based on column names or indexes, and constructing an object of type T.
     *
     * @param rs The ResultSet from which data needs to be extracted. Implementors should ensure
     *           that they do not close the ResultSet within this method, as it may still be in use elsewhere.
     * @return An object of type T that represents the data extracted from the ResultSet.
     * @throws SQLException If an SQL error occurs while accessing the ResultSet.
     */
    T extractData(ResultSet rs) throws SQLException;
}
