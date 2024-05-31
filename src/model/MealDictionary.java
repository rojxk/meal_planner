package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a dictionary to manage meals associated with a specific user.
 * This class provides functionality to add, retrieve, and remove meals, as well as to check the existence of meals.
 */
public class MealDictionary {
    Map<String, Meal> mealDictionary; // Stores meals with their names as keys for quick lookup.
    private Userdata user; // The user associated with this meal dictionary.

    /**
     * Constructs a new MealDictionary for the given user.
     * Initializes an empty dictionary to store meals.
     *
     * @param user The user for whom this dictionary is managing meals.
     */
    public MealDictionary(Userdata user){
        this.mealDictionary = new HashMap<>();
        this.user = user;
    }

    /**
     * Adds a meal to the dictionary using the meal's name as the key.
     * If a meal with the same name already exists, it will be overwritten.
     *
     * @param meal The meal to be added to the dictionary.
     */
    public void addMealToDict(Meal meal){
        mealDictionary.put(meal.getName(), meal);
    }

    /**
     * Retrieves a meal from the dictionary by its name.
     * Returns null if no meal with the specified name is found.
     *
     * @param name The name of the meal to retrieve.
     * @return The meal associated with the given name, or null if not found.
     */
    public Meal getMealFromDict(String name){
        return mealDictionary.get(name);
    }

    /**
     * Removes a meal from the dictionary by its name.
     * Returns the removed meal, or null if no meal was found with the specified name.
     *
     * @param name The name of the meal to be removed.
     * @return The removed meal, or null if the meal did not exist.
     */
    public Meal removeMeal(String name){
        return mealDictionary.remove(name);
    }

    /**
     * Prints all the meal names currently stored in the dictionary.
     * Outputs meal names prefixed with " - " to the console.
     */
    public void printKeyset(){
        for (String mealName : mealDictionary.keySet()) {
            System.out.print(" - " + mealName);
        }
        System.out.print(" - ");
    }

    /**
     * Checks if the dictionary is empty (i.e., contains no meals).
     *
     * @return true if the dictionary is empty, false otherwise.
     */
    public boolean isEmpty(){
        return mealDictionary.isEmpty();
    }

    /**
     * Checks if a meal with the specified name exists in the dictionary.
     *
     * @param name The name of the meal to check.
     * @return true if the meal exists, false otherwise.
     */
    public boolean exists(String name){
        return mealDictionary.containsKey(name);
    }
}
