package model;

import java.util.HashMap;
import java.util.Map;

public class MealDictionary {
    Map<String, Meal> mealDictionary;
    private Userdata user;

    public MealDictionary(Userdata user){
        this.mealDictionary = new HashMap<>();
        this.user = user;
    }

    public void addMealToDict(Meal meal){
        mealDictionary.put(meal.getName(), meal);
    }

    public Meal getMealFromDict(String name){
        return mealDictionary.get(name);
    }

    public Meal removeMeal(String name){
        return mealDictionary.remove(name);
    }

    public void printKeyset(){
        for (String mealName : mealDictionary.keySet()) {
            System.out.print(" - " + mealName);
        }
        System.out.print(" - ");
    }

    public boolean isEmpty(){
        return mealDictionary.isEmpty();
    }
    public boolean exists(String name){
        return mealDictionary.containsKey(name);
    }
}
