package model;

import java.util.HashSet;
import java.util.Set;



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
            case B -> new Breakfast(name);
            case L -> new Lunch(name);
            case D -> new Dinner(name);
            case S -> new Snack(name);
        };
    }

    public static void printMeal(Meal meal) {
        System.out.println("model.Meal: " + meal.getName()+ "\n" + "(model.Category: " + meal.getCategory() + ")");
        System.out.println("Ingredients:");
        for (String ingredient : meal.getIngredients()) {
            System.out.println("- " + ingredient);
        }
    }



}

class Breakfast extends Meal {
    public Breakfast(String name) {
        super(name, Category.B);
    }
}

class Lunch extends Meal {
    public Lunch(String name) {
        super(name, Category.L);
    }
}

class Dinner extends Meal {
    public Dinner(String name) {
        super(name, Category.D);
    }
}

class Snack extends Meal {
    public Snack(String name){
        super(name, Category.S);
    }

}