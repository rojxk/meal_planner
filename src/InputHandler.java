import java.util.Scanner;
import static mess.DisplayMessg.*;
public class InputHandler {
    private static final Scanner scan = new Scanner(System.in);

    private static void makeIngredientList(Meal meal){
        System.out.println("List your ingredience: (press [x] to escape)");
        while(true){
            userInput();
            String ingredient = scan.nextLine();
            if("x".equalsIgnoreCase(ingredient)){
                break;
            } else {
                meal.addIngredient(ingredient);
            }
        }
    }

    private static Category getInputCategory() {
        printQuestionCategory();
        while (true) {
            userInput();
            String inputCategory = scan.next();
            scan.nextLine();
            for(Category category : Category.values()){
                if (category.name().equalsIgnoreCase(inputCategory)) {
                    return category;
                }
            }
            System.out.println("Incorrect category, enter again:");
        }
    }

    private static String getInputMealName(){
        printQuestionName();
        userInput();
        return scan.nextLine();
    }

    static void addMeal(MealDictionary userName){
        Category cat = getInputCategory();
        String name = getInputMealName();
        userName.addMealToDict(Meal.setInputMeal(name, cat));
        Meal meal = userName.getMealFromDict(name);
        makeIngredientList(meal);
        System.out.println("The meal has been added!");
    }


    static void removeMeal(MealDictionary userName){
        System.out.println("Name the meal to be removed: ");
        userInput();
        while (true){
            String name = scan.nextLine();
            Meal removedMeal = userName.removeMeal(name);
            if (removedMeal != null) {
                System.out.println("Meal removed successfully.");
                break;
            } else {
                System.out.println("Meal not found.");
            }
        }
    }

    public enum Action{
        A,R,C,X
    }
    public static void chooseAction(Action action){
        switch (action){
            case A -> {
                System.out.println("A");
            }
            case R -> {
                System.out.println("R");
            }
            case C -> {
                System.out.println("C");
            }
            case X -> {
                System.out.println("X");
            }
        }

    }




}
