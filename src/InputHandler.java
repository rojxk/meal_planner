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
        if (userName.isEmpty()){
            System.out.println("There are no meals in the planner");
        } else {
            System.out.println("Your meals: ");
            userName.printKeyset();
            System.out.println();
            while (true) {
                System.out.println("Name the meal to be removed: ");
                userInput();
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
    }

    static void showMeal(MealDictionary userName){
        if (userName.isEmpty()){
            System.out.println("There are no meals in the planner");
        } else {
            System.out.println("Your meals: ");
            userName.printKeyset();
            System.out.println();
        }
    }



    public enum Action{
        S,A,R,C,X
    }
    private static Action chooseAction(){
        printQuesitonAction();
        while (true){
            userInput();
            String inputAction = scan.next();
            scan.nextLine();
            for (Action act : Action.values()){
                if (act.name().equalsIgnoreCase(inputAction)){
                    return act;
                }
            }
            System.out.println( "Incorrect action, enter again: ");
        }

    }

    static void handleAction(MealDictionary userName){
        while(true){
            Action action = chooseAction();
            switch (action){
                case S -> {
                    showMeal(userName);
                }
                case A -> {
                    addMeal(userName);
                }
                case R -> {
                    removeMeal(userName);
                }
                case C -> {
                    System.out.println("C");
                }
                case X -> {
                    System.out.println("EXITING MEAL PLANNER...");
                    return;
                }
        }

        }
    }
}
