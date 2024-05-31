package database;

import model.Category;
import model.InputHandler;
import model.MealDictionary;
import model.Userdata;

import java.util.Scanner;

import static mess.DisplayMessg.*;
import mess.Messages;

/**
 * Handles database interactions for meal management in the application,
 * including adding, removing, and displaying meals and ingredients.
 */
public class DatabaseHandler {

    enum Action {
        S, A, R, X  // Show, Add, Remove, Exit
    }
    enum ActionShow {
        S, B  // Show ingredients, Back
    }

    private static final Scanner scan = new Scanner(System.in);

    /**
     * Adds a meal to the database. This method prompts the user for meal details,
     * checks for duplicate names, and adds the meal and its ingredients to the database.
     *
     * @param user The user for whom the meal is being added.
     */
    public static void addMealDB(Userdata user) {
        int categoryID = getInputCategoryDB();
        int mealID;
        String name;
        while (true) {
            name = InputHandler.getInputMealName();
            if (Database.checkMealName(user.getUsername(), name)) {
                System.out.println("Name already exists!");
            } else {
                mealID = Database.addToMealTable(name, categoryID, user.getIdUser());
                break;
            }
        }
        System.out.println("List your ingredients: (press [x] to escape)");
        while (true) {
            userInput();
            String ingredient = scan.nextLine();
            if ("x".equalsIgnoreCase(ingredient)) {
                break;
            } else {
                Database.addToIngrTable(mealID, ingredient);
            }
        }
        System.out.println("Meal " + name + " added to planner.");
    }

    /**
     * Prompts the user to select a meal category and validates input against the Category enum.
     *
     * @return the database category ID corresponding to the user's selection.
     */
    private static int getInputCategoryDB() {
        printQuestionCategory();
        while (true) {
            userInput();
            String inputCategory = scan.next();
            scan.nextLine();
            for (Category category : Category.values()) {
                if (category.name().equalsIgnoreCase(inputCategory)) {
                    return category.getDbCategory();
                }
            }
            System.out.println("Incorrect category, enter again:");
        }
    }

    /**
     * Removes a meal from the database. This method prompts the user for the meal's name
     * and deletes it if it exists.
     *
     * @param user The user whose meal is to be removed.
     */
    private static void removeMealDB(Userdata user) {
        if (!Database.checkMeals(user.getUsername())) {
            System.out.println("There are no meals in the planner");
        } else {
            while (true) {
                userInput();
                System.out.println("Name the meal to be removed: ");
                userInput();
                String mealName = scan.nextLine();
                if (!Database.checkMealName(user.getUsername(), mealName)) {
                    System.out.println("No meal with that name!");
                } else {
                    Database.deleteFromMealTable(mealName, user.getIdUser());
                    System.out.println("Meal " + mealName + " removed.");
                    break;
                }
            }
        }
    }

    /**
     * Displays all meals for the specified user.
     *
     * @param user The user whose meals are to be displayed.
     */
    private static void showMealsDB(Userdata user) {
        Database.showAllMeals(user);
    }

    /**
     * Prompts the user to enter a meal name and checks its existence in the database.
     * This function is part of showing meal details including ingredients.
     *
     * @param user The user associated with the meal.
     */
    private static void showIngr(Userdata user) {
        System.out.println("What meal?");
        String name;
        while (true) {
            name = InputHandler.getInputMealName();
            if (!Database.checkMealName(user.getUsername(), name)) {
                System.out.println("No such meal!");
            } else {
                break;
            }
        }
    }

    /**
     * Generic method to handle user actions based on specified enums.
     * This method abstracts the action selection process, allowing for cleaner code when handling different types of actions.
     *
     * @param enumClass The enum class reflecting the type of actions.
     * @param question The question to prompt the user with for action selection.
     * @return The selected enum constant that represents the chosen action.
     */
    private static <E extends Enum<E>> E chooseAction(Class<E> enumClass, String question) {
        System.out.println(question);
        while (true) {
            userInput();
            String inputAction = scan.next();
            scan.nextLine();
            for (E act : enumClass.getEnumConstants()) {
                if (act.name().equalsIgnoreCase(inputAction)) {
                    return act;  // Return the matched enum constant
                }
            }
            System.out.println("Incorrect action, enter again: ");
        }
    }

    /**
     * Handles high-level actions including showing, adding, and removing meals.
     *
     * @param user The user context in which actions are to be handled.
     */
    public static void handleActionDB(Userdata user) {
        while (true) {
            Action action = chooseAction(Action.class, Messages.qAction);
            switch (action) {
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

    /**
     * Handles actions related to displaying detailed meal information.
     *
     * @param user The user context in which detailed actions are handled.
     */
    public static void handleActionShow(Userdata user) {
        while (true) {
            ActionShow action = chooseAction(ActionShow.class, Messages.qShow);
            switch (action) {
                case S -> {
                    System.out.println("Showing ingredients");
                }
                case B -> {
                    return;
                }
            }
        }
    }
}
