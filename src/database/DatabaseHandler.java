package database;
import model.Category;
import model.InputHandler;
import model.Userdata;

import java.util.Scanner;
import static mess.DisplayMessg.printQuestionCategory;
import static mess.DisplayMessg.userInput;

public class DatabaseHandler {

    private static final Scanner scan = new Scanner(System.in);

    private static void addMealDB(Userdata user){
        int categoryID = getInputCategoryDB();
        while (true){
            String name = InputHandler.getInputMealName();
            if(Database.checkMealName(user.getUsername(),name)){
                System.out.println("Name already exists!");
            } else{
                //add meal to database (id_meal, meal_name, cathegory,id_user
                //Database.newMeal(String name, int cathegory,
                int mealID = Database.addToMealTable(name,categoryID, user.getIdUser());

            }



        }
        //metod is adding ingredience to given meal of a user
        //insert into Ingredients (id_meal, ingr_name) VALUES (...)
    }

    private static int getInputCategoryDB() {
        printQuestionCategory();
        while (true) {
            userInput();
            String inputCategory = scan.next();
            scan.nextLine();
            for(Category category : Category.values()){
                if (category.name().equalsIgnoreCase(inputCategory)) {
                    return Database.categoryId(category);
                }
            }
            System.out.println("Incorrect category, enter again:");
        }
    }

    private static void addMealNameDB(){
        //metod gets an input from user and puts it into the database
    }

    //metod checking if name of meal exists
    //

    private static void removeMealDB(){
        //method removes meal from db
    }





}
