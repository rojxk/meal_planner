package database;

import java.sql.*;

@FunctionalInterface
interface ResultSetHandler {
    void handle(ResultSet rs) throws SQLException;
}
