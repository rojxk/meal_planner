package database;

import java.sql.*;

public class DatabaseUtils {
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

    // returns number of rows affected for INSERT DELETE UPDATE
    public static int executeUpdate(String sql, PreparedStatementSetter pss) {
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pss.setParameters(pstmt);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
