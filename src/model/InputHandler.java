package model;

import java.util.Scanner;
import static mess.DisplayMessg.*;  // Importing static methods from DisplayMessg to handle user output.

public class InputHandler {
    private static final Scanner scan = new Scanner(System.in); // Scanner object for input handling.

    /**
     * Enumerates the possible actions a user can take in the application.
     * Each action corresponds to a specific command within the meal planner:
     * - S: Show all meals
     * - A: Add a new meal
     * - R: Remove an existing meal
     * - X: Exit the application
     */
    public enum Action {
        S, // Show command
        A, // Add command
        R, // Remove command
        X  // Exit command
    }

    /**
     * Collects ingredients from the user and adds them to the specified Meal object.
     * The user is prompted to enter one ingredient at a time. Entering 'x' will stop the process.
     * This method continuously prompts and reads user input until the escape character ('x') is entered,
     * allowing the user to add multiple ingredients to a meal.
     *
     * @param meal The meal object to which ingredients will be added.
     */
    private static void makeIngredientList(Meal meal) {
        System.out.println("List your ingredients: (press [x] to escape)");
        while (true) {
            userInput();  // Prompt user for input.
            String ingredient = scan.nextLine();
            if ("x".equalsIgnoreCase(ingredient)) {  // Check if user wishes to exit the loop.
                break;
            } else {
                meal.addIngredient(ingredient);  // Add entered ingredient to the meal.
            }
        }
    }


    /**
     * Prompts the user to enter a meal category and validates the input against the defined Category enum.
     * This method displays a category question, collects user input, and checks if it matches any enum value.
     * If a match is found, the corresponding Category enum is returned. If no match is found, the user is
     * informed of the incorrect input and prompted to try again. This process repeats until a valid category is entered.
     *
     * @return The Category enum value that matches the user's input.
     */
    private static Category getInputCategory() {
        printQuestionCategory();  // Display the category query message.
        while (true) {
            userInput();  // Prompt for user input.
            String inputCategory = scan.next();  // Read the category input from the user.
            scan.nextLine();  // Consume the rest of the line to avoid input errors on subsequent reads.
            for (Category category : Category.values()) {  // Iterate through all available Category enum values.
                if (category.name().equalsIgnoreCase(inputCategory)) {  // Case-insensitive comparison to handle user input.
                    return category;  // Return the matching Category enum.
                }
            }
            System.out.println("Incorrect category, enter again:");  // Prompt re-entry on incorrect input.
        }
    }


    /**
     * Prompts the user to enter the name of a meal and retrieves that input.
     * This method displays a question asking for the meal's name, waits for user input, and then captures the entered string.
     * The input is taken in via standard input, ensuring that any text entered by the user is returned as the meal's name.
     *
     * @return The name of the meal as entered by the user.
     */
    public static String getInputMealName() {
        printQuestionName();  // Display the prompt for entering the meal name.
        userInput();  // Indicate where the user should input text.
        return scan.nextLine();  // Read and return the user's input.
    }


    /**
     * Adds a meal to the dictionary if it doesn't already exist and collects ingredients from user input.
     * Ensures that each meal has a unique name within the dictionary to prevent duplicates.
     *
     * @param userName The meal dictionary to which the meal will be added.
     */
    static void addMeal(MealDictionary userName) {
        Category cat = getInputCategory();  // Get the category of the meal from user input.
        while (true) {
            String name = getInputMealName();  // Get the name of the meal from user input.
            if (userName.exists(name)) {
                System.out.println("Name already exists");  // Check for duplicate names.
            } else {
                userName.addMealToDict(Meal.setInputMeal(name, cat));  // Add meal if name is unique.
                Meal meal = userName.getMealFromDict(name);
                makeIngredientList(meal);  // Collect and add ingredients to the meal.
                System.out.println("The meal has been added!");
                break;
            }
        }
    }


    /**
     * Removes a specified meal from the MealDictionary if it exists.
     * This method prompts the user to enter the name of the meal they wish to remove.
     * If the meal is found, it is removed from the dictionary and a confirmation message is displayed.
     * If the meal is not found, an error message is shown.
     *
     * @param userName The meal dictionary from which the meal will be removed.
     */
    static void removeMeal(MealDictionary userName) {
        if (userName.isEmpty()) {
            System.out.println("There are no meals in the planner");  // Check if the dictionary is empty.
        } else {
            System.out.println("Your meals: ");
            userName.printKeyset();  // Display all current meals.
            System.out.println();
            while (true) {
                System.out.println("Name the meal to be removed: ");  // Ask for the meal to be removed.
                userInput();  // Prompt user input.
                String name = scan.nextLine();
                Meal removedMeal = userName.removeMeal(name);  // Attempt to remove the specified meal.
                if (removedMeal != null) {
                    System.out.println("Meal removed successfully.");  // Confirm successful removal.
                    break;
                } else {
                    System.out.println("Meal not found.");  // Notify user if the meal does not exist.
                }
            }
        }
    }

    /**
     * Displays all meals currently stored in the MealDictionary.
     * If there are no meals in the dictionary, a message indicating that the planner is empty is shown.
     * Otherwise, it lists all the meal names in the dictionary.
     *
     * @param userName The MealDictionary from which meals will be displayed.
     */
    static void showMeal(MealDictionary userName) {
        if (userName.isEmpty()) {
            System.out.println("There are no meals in the planner");  // Notify user the planner is empty.
        } else {
            System.out.println("Your meals: ");
            userName.printKeyset();  // Print all meal names stored in the dictionary.
            System.out.println();  // Ensures a clean separation after listing meals.
        }
    }


    /**
     * Prompts the user to choose an action by entering a specific key (S, A, R, X) and validates this against the Action enum.
     * The method continues to prompt the user until a valid action is entered. It performs a case-insensitive comparison
     * to determine if the entered string matches any enum values.
     *
     * @return The validated Action enum value corresponding to the user's input.
     */
    private static Action chooseAction() {
        printQuestionAction();  // Display prompt for choosing an action.
        while (true) {
            userInput();  // Display input cursor.
            String inputAction = scan.next();  // Read user input for the action.
            scan.nextLine();  // Clear input buffer to avoid reading leftovers in future inputs.
            for (Action act : Action.values()) {  // Check each Action enum for a match.
                if (act.name().equalsIgnoreCase(inputAction)) {
                    return act;  // Return the matching Action enum.
                }
            }
            System.out.println("Incorrect action, enter again: ");  // Prompt re-entry on invalid input.
        }
    }


    /**
     * Manages the main loop of the application, handling user actions continuously until the 'Exit' action is chosen.
     * Based on the user's action selection, it calls the appropriate method to either show, add, remove meals, or exit.
     *
     * @param userName The MealDictionary instance used to store and manage meals.
     */
    public static void handleAction(MealDictionary userName) {
        while (true) {
            Action action = chooseAction();  // Get the user's chosen action.
            switch (action) {  // Execute the corresponding method based on the chosen action.
                case S -> showMeal(userName);  // Show all meals.
                case A -> addMeal(userName);  // Add a new meal.
                case R -> removeMeal(userName);  // Remove an existing meal.
                case X -> {
                    System.out.println("EXITING MEAL PLANNER...");  // Inform user of the application exit.
                    return;  // Exit the application loop.
                }
            }
        }
    }

}
