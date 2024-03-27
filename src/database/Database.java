package database;
import model.MealDictionary;
import model.Category;

import java.sql.*;

public class Database {


    public static boolean checkMealName(String username, String mealName){
        String sql = "SELECT EXISTS (SELECT 1 FROM Meal JOIN Userdata U ON Meal.id_user = U.id_user WHERE U.user_name = ? AND Meal.meal_name = ?)";
        return DatabaseUtils.executeQuery(sql, pstmt -> {
            pstmt.setString(1, username);
            pstmt.setString(2, mealName);
        }, rs -> rs.next() && rs.getBoolean(1));
    }

    public static boolean checkUsername(String username){
        String sql = "SELECT EXISTS (SELECT 1 FROM userdata WHERE user_name = ?)";
        return DatabaseUtils.executeQuery(sql, pstmt -> {
            pstmt.setString(1, username);
        }, rs -> rs.next() && rs.getBoolean(1));
    }



    public static void setUser(String username){
        if (!Database.checkUsername(username)) {
            String sql = "INSERT INTO Userdata (user_name) VALUES (?)";
            int affectedRows = DatabaseUtils.executeUpdate(sql, pstmt -> pstmt.setString(1, username));

            if (affectedRows == 0) {
                throw new RuntimeException("Inserting user failed, no rows affected.");
            }
        }
    }


    public static int addToMealTable(String mealName, int categoryID, int userID) {
        // Query to add a new meal
        String queryAdd = "INSERT INTO Meal (meal_name, id_category, id_user) VALUES (?,?,?)";

        // Use try-with-resources to ensure resources are closed properly
        try (Connection conn = DatabaseConnector.connect();
             // Prepare the statement for insert operation with the option to return generated keys
             PreparedStatement pstmt = conn.prepareStatement(queryAdd, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the insert operation
            pstmt.setString(1, mealName);
            pstmt.setInt(2, categoryID);
            pstmt.setInt(3, userID);

            // Execute the insert operation
            int affectedRows = pstmt.executeUpdate();

            // Check if the insert was successful
            if (affectedRows == 0) {
                throw new SQLException("Creating meal failed, no rows affected.");
            }

            // Retrieve the generated key for the inserted meal
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Assume `id_meal` is the first column
                } else {
                    throw new SQLException("Creating meal failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void addToIngrTable(int mealID, String ingredient){
        String sql = "INSERT INTO Ingredients (id_meal, ingr_name) VALUES (?,?)";
        int affectedRows = DatabaseUtils.executeUpdate(sql, pstmt -> {
            pstmt.setInt(1, mealID);
            pstmt.setString(2, ingredient);
        });

        if (affectedRows == 0) {
            throw new RuntimeException("Inserting user failed, no rows affected.");
        }

    }

    public static void selectAllFromCategory() {
        String query = "SELECT * FROM category";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Assuming 'id' and 'name' as columns in your 'category' table
                int id = rs.getInt("id_category");
                String name = rs.getString("category_name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException e) {
            System.err.println("Error executing SELECT query: " + e.getMessage());
        }
    }
}
