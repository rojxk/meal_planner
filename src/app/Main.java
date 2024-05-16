package app;

import database.DatabaseConnector;
import database.Database;
import database.DatabaseHandler;
import model.Userdata;

import static mess.DisplayMessg.*;


public class Main {

    public static void main(String[] args) {
        printTitle();
        DatabaseHandler.handleActionDB(new Userdata(1,"Magda"));

    }
}
