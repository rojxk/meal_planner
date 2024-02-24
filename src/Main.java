import static mess.DisplayMessg.*;


public class Main {

    public static void main(String[] args) {
        printTitle();
        MealDictionary MagdaMeals = new MealDictionary();
        InputHandler.handleAction(MagdaMeals);

    }

}
