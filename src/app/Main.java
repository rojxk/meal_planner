package app;

import model.InputHandler;
import model.Userdata;

import static mess.DisplayMessg.*;


public class Main {
    public static void main(String[] args) {
        printTitle();
        model.MealDictionary MagdaMeals = new model.MealDictionary(new Userdata(1,"Madzia"));
        InputHandler.handleAction(MagdaMeals);

    }

}
