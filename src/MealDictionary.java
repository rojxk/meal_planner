import java.util.HashMap;
import java.util.Map;

class MealDictionary {
    Map<String, Meal> mealDictionary;

    public MealDictionary(){
        this.mealDictionary = new HashMap<>();
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
}
