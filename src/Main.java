import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //system input
        Scanner scan = new Scanner(System.in);
        String temp;



        System.out.println(Messages.title);
        System.out.println(Messages.qCategory);

        boolean isBreakfast;
        boolean isLunch;
        boolean isDinner;
        boolean isSnack;

        do {
            Messages.userInput();
            temp = scan.next();
        } while (!temp.equals("breakfast") && !temp.equals("lunch") && !temp.equals("dinner") && !temp.equals("snack"));

        System.out.println(Messages.mPicked + temp);

        // jak chcesz nazwac swoje danie
        System.out.println("Podaj nazwe dania:");
        Messages.userInput();
        String name = scan.next();
        // stworzenie tablicy skladnikow do tego dania
        String[] ingr = new String[5];
        int fill = 0;
        System.out.println("Podaj składnik dania: (wpisz q aby zakończyć)");
        Messages.userInput();
        while (!(temp = scan.next()).equals("q")){
            if (fill >= ingr.length){
                ingr = biggerArr(ingr);
            }
            Messages.userInput();
            ingr[fill++] = temp;



        }

        System.out.println(name);
        for (int i = 0 ; i < fill ; i++){
            System.out.println("- " + ingr[i]);
        }


    }



    static String[] biggerArr(String[] arr){
        String[] biggerArr = new String[arr.length*2];
        for(int i = 0; i < arr.length; i++){
            biggerArr[i] = arr[i];
        }
        return biggerArr;
    }

    static boolean notInArr(String temp, String[] arr){
        boolean notInArray = false;
        for (int i = 0; i < arr.length; i ++){
            if (arr[i].equals(temp)) return notInArray;
        }
        return notInArray = true;
    }


}