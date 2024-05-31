package app;

import database.DatabaseConnector;
import database.Database;
import database.DatabaseHandler;
import model.Userdata;

import static mess.DisplayMessg.*;

/**
 * Main entry point for the Meal Planner application.
 * This class initializes the application, sets up necessary user data, and starts the interaction
 * process through the DatabaseHandler class. It is designed to manage meals using a database backend,
 * handling user inputs and system outputs.
 */
public class Main {

    /**
     * Main method to launch the application.
     * Initializes the system by setting the application title and starting the database interaction
     * handler with predefined user data. This setup assumes that the user data for 'Magda' is used
     * to demonstrate or test the application functionality.
     *
     * @param args Command-line arguments, which are not used in this application.
     */
    public static void main(String[] args) {
        printTitle();  // Displays the application title to the user.
        // Start handling actions for a predefined user, 'Magda', with a user ID of 1.
        DatabaseHandler.handleActionDB(new Userdata(1, "Magda"));
    }
}
