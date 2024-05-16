package database;
import model.Category;
import model.InputHandler;
import model.MealDictionary;
import model.Userdata;

import java.util.Scanner;

import static mess.DisplayMessg.*;
import mess.Messages;

public class DatabaseHandler {

    enum Action{
        S,A,R,X
    }
    enum ActionShow{
        S,B
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void addMealDB(Userdata user){
        int categoryID = getInputCategoryDB();
        int mealID;
        String name;
        while (true){
            name = InputHandler.getInputMealName();
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
        System.out.println("Meal " + name + " added to planner.");
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


    private static void removeMealDB(Userdata user){
        if (!Database.checkMeals(user.getUsername())){
            System.out.println("There are no meals in the planner");
        } else {
            while(true){

                userInput();
                System.out.println("Name the meal to be removed: ");
                userInput();
                String mealName = scan.nextLine();
                if(!Database.checkMealName(user.getUsername(),mealName)){
                    System.out.println("No meal with that name!");
                } else{
                    Database.deleteFromMealTable(mealName,user.getIdUser());
                    System.out.println("Meal " + mealName +" removed.");
                    break;
                }
            }
        }

    }

    private static void showMealsDB(Userdata user){
        Database.showAllMeals(user);
    }

    private static void showIngr(Userdata user){
        System.out.println("What meal ?");
        String name;
        while (true){
            name = InputHandler.getInputMealName();
            if(!Database.checkMealName(user.getUsername(),name)){
                System.out.println("No such meal!");
            } else{

                break;
            }
        }
    }

    private static <E extends Enum<E>> E chooseAction(Class<E> enumClass, String question) {
        System.out.println(question);
        while (true) {
            userInput();
            String inputAction = scan.next();
            scan.nextLine();
            for (E act : enumClass.getEnumConstants()) {
                if (act.name().equalsIgnoreCase(inputAction)) {
                    return act; // Return the matched enum constant
                }
            }
            System.out.println("Incorrect action, enter again: ");
        }
    }

    public static void handleActionDB(Userdata user){
        while(true){
            Action action = chooseAction(Action.class, Messages.qAction);
            switch (action){
                case S -> {
                    showMealsDB(user);
                    handleActionShow(user);
                }
                case A -> {
                    addMealDB(user);
                }
                case R -> {
                    removeMealDB(user);
                }
                case X -> {
                    System.out.println("EXITING MEAL PLANNER...");
                    return;
                }
            }
        }
    }

    public static void handleActionShow(Userdata user){
        while(true){
            ActionShow action = chooseAction(ActionShow.class, Messages.qShow);
            switch (action){
                case S -> {
                    System.out.println("Showing ingr");
                }
                case B -> {
                    return;
                }
            }
        }
    }





}
