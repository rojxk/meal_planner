import java.util.HashSet;
import java.util.Set;

import static mess.DisplayMessg.userInput;

enum Category{
    BREAKFAST, LUNCH, DINNER, SNACK
}

class Meal {
    private String name;
    private Set<String> ingredients = new HashSet<>();
    protected final Category category;

    public Meal(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName(){
        return name;
    }

    public Category getCategory(){
        return category;
    }

    public Set<String> getIngredients() {
        return new HashSet<>(ingredients);
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public boolean removeIngredient(String ingredient) {
        return ingredients.remove(ingredient);
    }


    static Meal setInputMeal(String name, Category category) {
        return switch (category) {
            case BREAKFAST -> new Breakfast(name);
            case LUNCH -> new Lunch(name);
            case DINNER -> new Dinner(name);
            case SNACK -> new Snack(name);
        };
    }

    public static void printMeal(Meal meal) {
        System.out.println("Meal: " + meal.getName()+ "\n" + "(Category: " + meal.getCategory() + ")");
        System.out.println("Ingredients:");
        for (String ingredient : meal.getIngredients()) {
            System.out.println("- " + ingredient);
        }
    }



}

class Breakfast extends Meal {
    public Breakfast(String name) {
        super(name, Category.BREAKFAST);
    }
}

class Lunch extends Meal {
    public Lunch(String name) {
        super(name, Category.LUNCH);
    }
}

class Dinner extends Meal {
    public Dinner(String name) {
        super(name, Category.DINNER);
    }
}

class Snack extends Meal {
    public Snack(String name){
        super(name, Category.SNACK);
    }

}