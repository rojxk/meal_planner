package app;

import database.DatabaseConnector;
import database.Database;
import model.Userdata;

import static mess.DisplayMessg.*;


public class Main {

    public static void main(String[] args) {
        printTitle();
        //model.MealDictionary MagdaMeals = new model.MealDictionary();
        //model.InputHandler.handleAction(MagdaMeals);
        //System.out.println(Database.getUserId("Madzia"));
        //DatabaseConnector.doDatabaseOperation();
        //DatabaseConnector.doDatabaseOperation2();
        //DatabaseConnector.selectAllFromCategory();
        model.MealDictionary MagdaMeals = new model.MealDictionary(new Userdata(1,"Madzia"));




    }

}
