package app;

import model.InputHandler;
import model.Userdata;

import static mess.DisplayMessg.*;

/**
 * Entry point for the Meal Planner application.
 * This class initializes the application, sets up necessary objects, and starts the user interaction process.
 */
public class Main {
    /**
     * Main method to launch the application.
     * This method sets up the initial environment of the application, creating a meal dictionary for a specific user
     * and starting the interaction loop where the user can add, remove, or display meals.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        printTitle();  // Display the application title.
        // Create a new MealDictionary instance for a user with ID 1 and name "Magda".
        model.MealDictionary MagdaMeals = new model.MealDictionary(new Userdata(1, "Magda"));
        // Start handling user actions such as adding, removing, and displaying meals.
        InputHandler.handleAction(MagdaMeals);
    }
}

