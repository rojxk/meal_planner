import java.util.Arrays;
import java.util.Set;
import java.util.Scanner;
import static mess.DisplayMessg.*;


public class Main {

    public static void main(String[] args) {
        printTitle();
        MealDictionary MagdaMeals = new MealDictionary();
        InputHandler.handleAction(MagdaMeals);
        //InputHandler.addMeal(MagdaMeals);

    }

}
