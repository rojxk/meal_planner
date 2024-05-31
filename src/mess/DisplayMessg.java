package mess;

public class DisplayMessg {
    // General method for displaying a prompt message with a newline.
    private static void printMessage(String message) {
        System.out.println(message);
    }

    // Method for displaying the user input prompt.
    public static void userInput() {
        System.out.print(">");
    }

    // Method for displaying the title of the application.
    public static void printTitle() {
        printMessage(Messages.title);
    }

    // Method for displaying the question about the category.
    public static void printQuestionCategory() {
        printMessage(Messages.qCategory);
    }

    // Method for displaying the question about the meal name.
    public static void printQuestionName() {
        printMessage(Messages.qMealName);
    }

    // Method for displaying the question about the user's next action.
    public static void printQuestionAction() {
        printMessage(Messages.qAction);
    }
}
