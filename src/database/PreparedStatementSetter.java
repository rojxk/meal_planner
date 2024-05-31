package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * An interface defining a method to set parameters on a {@link PreparedStatement}.
 * This interface is designed to be implemented by classes that handle the preparation
 * of statements by setting specific values on them, facilitating dynamic SQL query execution.
 *
 * Implementing this interface allows for separation of the setting of parameters
 * from the creation and execution of the SQL statement, enhancing modularity and
 * reusability of the database access logic.
 */
public interface PreparedStatementSetter {

    /**
     * Sets parameters on the provided PreparedStatement object.
     * Implementors are expected to set all necessary parameters on the PreparedStatement
     * provided as an argument. This method is typically called to prepare the PreparedStatement
     * before it is executed.
     *
     * @param pstmt The PreparedStatement on which to set the parameters.
     * @throws SQLException If a database access error occurs or setting the parameters fails.
     */
    void setParameters(PreparedStatement pstmt) throws SQLException;
}
