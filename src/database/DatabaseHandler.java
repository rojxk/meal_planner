package database;
import model.Category;
import model.InputHandler;
import model.Userdata;

import java.util.Scanner;
import static mess.DisplayMessg.printQuestionCategory;
import static mess.DisplayMessg.userInput;

public class DatabaseHandler {

    private static final Scanner scan = new Scanner(System.in);

    public static void addMealDB(Userdata user){
        int categoryID = getInputCategoryDB();
        int mealID;
        while (true){
            String name = InputHandler.getInputMealName();
            if(Database.checkMealName(user.getUsername(),name)){
                System.out.println("Name already exists!");
            } else{
                mealID = Database.addToMealTable(name,categoryID, user.getIdUser());
                break;
            }
        }
        System.out.println("List your ingredience: (press [x] to escape)");
        while(true){
            userInput();
            String ingredient = scan.nextLine();
            if("x".equalsIgnoreCase(ingredient)){
                break;
            } else {
                Database.addToIngrTable(mealID, ingredient);
            }
        }
    }

    private static int getInputCategoryDB() {
        printQuestionCategory();
        while (true) {
            userInput();
            String inputCategory = scan.next();
            scan.nextLine();
            for(Category category : Category.values()){
                if (category.name().equalsIgnoreCase(inputCategory)) {
                    return category.getDbCategory();
                }
            }
            System.out.println("Incorrect category, enter again:");
        }
    }


    private static void removeMealDB(){
        //method removes meal from db

    }





}
