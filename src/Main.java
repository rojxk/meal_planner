import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println(Messages.title);
        System.out.println(Messages.qCategory);
        Messages.userInput();
        String category = getCategory();

        System.out.println(Messages.qMealName);
        scan.nextLine(); // Clear the scanner buffer
        Messages.userInput();
        String name = scan.nextLine();

        String[] ingredients = getIngredients();

        Meal meal = createMeal(name, ingredients, category);
        printMeal(meal);
    }

    static String getCategory() {
        while (true) {
            String category = scan.next();
            if ("breakfast".equals(category) || "lunch".equals(category) ||
                    "dinner".equals(category) || "snack".equals(category)) {
                return category;
            } else {
                System.out.println("Incorrect category, enter again:");
            }
        }
    }

    static Meal createMeal(String name, String[] ingredients, String category) {
        return switch (category.toLowerCase()) {
            case "breakfast" -> new Breakfast(name, ingredients);
            case "lunch" -> new Lunch(name, ingredients);
            case "dinner" -> new Dinner(name, ingredients);
            case "snack" -> new Snack(name, ingredients);
            default -> throw new IllegalArgumentException("Unknown category: " + category);
        };
    }
    static String[] getIngredients() {
        String[] ingredients = new String[5];
        int fill = 0;
        System.out.println("Input the ingredients(to stop type q):");

        while (true) {
            Messages.userInput();
            String ingredient = scan.nextLine();
            if ("q".equals(ingredient)) {
                break;
            }
            if (fill >= ingredients.length) {
                ingredients = biggerArr(ingredients);
            }
            ingredients[fill++] = ingredient;
        }
        return Arrays.copyOf(ingredients, fill); // Trim the array to the actual number of ingredients
    }

    static void printMeal(Meal meal) {
        System.out.println("Meal: " + meal.getName()+ "\n" + "(Category: " + meal.getCategory() + ")");
        System.out.println("Ingredients:");
        for (String ingredient : meal.getIngredients()) {
            System.out.println("- " + ingredient);
        }
    }

    static String[] biggerArr(String[] original) {
        return Arrays.copyOf(original, original.length * 2);
    }
}
