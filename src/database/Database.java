package database;

import model.Userdata;

import java.sql.*;

/**
 * Provides utility methods for database operations related to meal management.
 * This class includes methods to check and manipulate meal and ingredient data in the database.
 */
public class Database {

    /**
     * Checks if a meal with the specified name already exists for a given user.
     *
     * @param username The username to check the meal name against.
     * @param mealName The meal name to check for existence.
     * @return true if the meal name exists, false otherwise.
     */
    public static boolean checkMealName(String username, String mealName){
        String sql = "SELECT EXISTS (SELECT 1 FROM Meal JOIN Userdata U ON Meal.id_user = U.id_user WHERE U.user_name = ? AND Meal.meal_name = ?)";
        return DatabaseUtils.executeQuery(sql, pstmt -> {
            pstmt.setString(1, username);
            pstmt.setString(2, mealName);
        }, rs -> rs.next() && rs.getBoolean(1));
    }

    /**
     * Checks if any meals exist for a given user.
     *
     * @param username The username for which to check meals existence.
     * @return true if meals exist, false otherwise.
     */
    public static boolean checkMeals(String username){
        String sql = "SELECT EXISTS (SELECT 1 FROM Meal JOIN Userdata U ON Meal.id_user = U.id_user WHERE U.user_name = ?)";
        return DatabaseUtils.executeQuery(sql, pstmt -> {
            pstmt.setString(1, username);
        }, rs -> rs.next() && rs.getBoolean(1));
    }

    /**
     * Checks if a user with the specified username exists in the database.
     *
     * @param username The username to check.
     * @return true if the username exists, false otherwise.
     */
    public static boolean checkUsername(String username){
        String sql = "SELECT EXISTS (SELECT 1 FROM userdata WHERE user_name = ?)";
        return DatabaseUtils.executeQuery(sql, pstmt -> {
            pstmt.setString(1, username);
        }, rs -> rs.next() && rs.getBoolean(1));
    }

    /**
     * Adds a new user to the database if they do not already exist.
     *
     * @param username The username to add to the database.
     */
    public static void setUser(String username){
        if (!Database.checkUsername(username)) {
            String sql = "INSERT INTO Userdata (user_name) VALUES (?)";
            int affectedRows = DatabaseUtils.executeUpdate(sql, pstmt -> pstmt.setString(1, username));
            if (affectedRows == 0) {
                throw new RuntimeException("Inserting user failed, no rows affected.");
            }
        }
    }

    /**
     * Adds a new meal to the database and returns the generated meal ID.
     *
     * @param mealName The name of the meal.
     * @param categoryID The category ID of the meal.
     * @param userID The user ID to which the meal is associated.
     * @return The generated ID for the new meal.
     */
    public static int addToMealTable(String mealName, int categoryID, int userID) {
        String query = "INSERT INTO Meal (meal_name, id_category, id_user) VALUES (?,?,?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, mealName);
            pstmt.setInt(2, categoryID);
            pstmt.setInt(3, userID);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating meal failed, no rows affected.");
            }
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

    /**
     * Adds an ingredient to a meal in the database.
     *
     * @param mealID The ID of the meal to which the ingredient is added.
     * @param ingredient The name of the ingredient to add.
     */
    public static void addToIngrTable(int mealID, String ingredient){
        String sql = "INSERT INTO Ingredients (id_meal, ingr_name) VALUES (?,?)";
        int affectedRows = DatabaseUtils.executeUpdate(sql, pstmt -> {
            pstmt.setInt(1, mealID);
            pstmt.setString(2, ingredient);
        });
        if (affectedRows == 0) {
            throw new RuntimeException("Inserting ingredient failed, no rows affected.");
        }
    }

    /**
     * Deletes a meal from the database.
     *
     * @param mealName The name of the meal to delete.
     * @param userID The ID of the user associated with the meal.
     * @return true if the deletion was successful, false otherwise.
     */
    public static boolean deleteFromMealTable(String mealName, int userID){
        String sql = "DELETE FROM Meal WHERE meal_name = ? AND id_user = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mealName);
            pstmt.setInt(2, userID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting meal", e);
        }
    }

    /**
     * Lists all categories in the database.
     */
    public static void selectAllFromCategory() {
        String query = "SELECT * FROM category";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_category");
                String name = rs.getString("category_name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException e) {
            System.err.println("Error executing SELECT query: " + e.getMessage());
        }
    }

    /**
     * Displays all meal names for a specified user.
     *
     * @param user The user whose meals are to be displayed.
     */
    public static void showAllMeals(Userdata user) {
        String query = "SELECT meal_name FROM Meal m JOIN userdata u ON m.id_user = u.id_user WHERE u.user_name = ?";
        DatabaseUtils.executeQueryWithHandler(query, pstmt -> {
            pstmt.setString(1, user.getUsername());
        }, rs -> {
            String mealName = rs.getString("meal_name");
            System.out.print(" - "+ mealName);
        });
        System.out.println(" - ");
    }

    /**
     * Shows all ingredients for a specified meal and user.
     *
     * @param user The user associated with the meal.
     * @param mealName The meal whose ingredients are to be displayed.
     */
    public static void showIngr(Userdata user, String mealName){
        String query = "SELECT ingr_name FROM Ingredients I JOIN Meal m ON I.id_meal = m.id_meal JOIN Userdata u ON m.id_user = u.id_user WHERE id_user = ? AND meal_name = ?";
        DatabaseUtils.executeQueryWithHandler(query, pstmt -> {
            pstmt.setInt(1,user.getIdUser());
            pstmt.setString(2,mealName);
        }, rs -> {
            String ingrName = rs.getString("ingr_name");
            System.out.println(" - " + ingrName);
        });
    }
}
