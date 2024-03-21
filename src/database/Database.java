package database;
import model.MealDictionary;
import model.Category;

import java.sql.*;

public class Database {

    public static int categoryId(Category category){
        return switch (category){
            case B -> 1;
            case L -> 2;
            case D -> 3;
            case S -> 4;
        };
    }

    public static boolean checkMealName(String username, String mealName){
        String sql = "SELECT EXISTS ( SELECT 1 FROM Meal JOIN Userdata U ON Meal.id_user = U.id_user WHERE U.user_name = ? AND Meal.meal_name = ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,username);
            pstmt.setString(2,mealName);
            ResultSet rs = pstmt.executeQuery();
            return rs.getBoolean(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean checkUsername(String username){
        String sql = "SELECT EXISTS ( SELECT 1 FROM userdata WHERE user_name = ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            return rs.getBoolean(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setUser(String username){
        if (Database.checkUsername(username)){
            String sql = "INSERT INTO Userdata (user_name) VALUES (?)";
            try (Connection conn = DatabaseConnector.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1,username);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Inserting user failed, no rows affected.");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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

    public static int addToMealTable(String mealName, int categoryID, int userID) {
        int idMeal = 0;
        String queryAdd = "INSERT INTO Meal (meal_name, id_category, id_user) VALUES (?,?,?)";
        String queryGet = "SELECT id_meal FROM Meal WHERE id_user = ? AND meal_name = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt1 = conn.prepareStatement(queryAdd)) {
            pstmt1.setString(1, mealName);
            pstmt1.setInt(2, categoryID);
            pstmt1.setInt(3, userID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(queryGet);
             ResultSet rs = pstmt.executeQuery()) {
            idMeal = rs.getInt(1);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idMeal;
    }

    public static void addToIngrTable(){

    }
}
