package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a meal with a specific name, category, and a set of ingredients.
 * This class serves as a base for different types of meals and provides common functionality
 * for managing ingredients and meal information.
 */
class Meal {
    private String name; // Stores the name of the meal.
    private Set<String> ingredients = new HashSet<>(); // Stores ingredients with no duplicates.
    protected final Category category; // The category of the meal (e.g., Breakfast, Lunch).

    /**
     * Constructs a new Meal with the specified name and category.
     *
     * @param name the name of the meal.
     * @param category the category of the meal.
     */
    public Meal(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    /**
     * Returns the name of the meal.
     *
     * @return the meal's name.
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the category of the meal.
     *
     * @return the meal's category.
     */
    public Category getCategory(){
        return category;
    }

    /**
     * Provides a copy of the ingredients set to prevent direct modifications.
     *
     * @return a copy of the ingredients set.
     */
    public Set<String> getIngredients() {
        return new HashSet<>(ingredients);
    }

    /**
     * Adds a new ingredient to the meal.
     *
     * @param ingredient the ingredient to add.
     */
    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    /**
     * Removes an ingredient from the meal.
     *
     * @param ingredient the ingredient to remove.
     * @return true if the ingredient was successfully removed, false otherwise.
     */
    public boolean removeIngredient(String ingredient) {
        return ingredients.remove(ingredient);
    }

    /**
     * Factory method to create instances of Meal based on the specified category.
     * Each category corresponds to a different subclass of Meal.
     *
     * @param name the name of the meal.
     * @param category the category of the meal.
     * @return an instance of a subclass of Meal corresponding to the given category.
     */
    static Meal setInputMeal(String name, Category category) {
        return switch (category) {
            case B -> new Breakfast(name);
            case L -> new Lunch(name);
            case D -> new Dinner(name);
            case S -> new Snack(name);
        };
    }

    /**
     * Prints details of the meal including its name, category, and ingredients.
     *
     * @param meal the meal to print.
     */
    public static void printMeal(Meal meal) {
        System.out.println("model.Meal: " + meal.getName() + "\n" + "(model.Category: " + meal.getCategory() + ")");
        System.out.println("Ingredients:");
        for (String ingredient : meal.getIngredients()) {
            System.out.println("- " + ingredient);
        }
    }
}

/**
 * Represents a breakfast meal.
 */
class Breakfast extends Meal {
    public Breakfast(String name) {
        super(name, Category.B); // Initialize with the breakfast category.
    }
}

/**
 * Represents a lunch meal.
 */
class Lunch extends Meal {
    public Lunch(String name) {
        super(name, Category.L); // Initialize with the lunch category.
    }
}

/**
 * Represents a dinner meal.
 */
class Dinner extends Meal {
    public Dinner(String name) {
        super(name, Category.D); // Initialize with the dinner category.
    }
}

/**
 * Represents a snack.
 */
class Snack extends Meal {
    public Snack(String name){
        super(name, Category.S); // Initialize with the snack category.
    }
}
