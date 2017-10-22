import java.util.Scanner;

/**
 * @author Aileen Benedict
 * @version 10/22/2017
 *
 * Driver class.
 *
 * Note:
 * array[row][column]
 * Aways forget :')...
 */

public class Driver {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        // Generate Map
        Map map = new Map();

        /* Another Map ror testing :') ~~
        String[][] m = {{"-", "x", "x", "x", "x", "x"},
                        {"-", "x", "x", "-", "-", "x"},
                        {"-", "x", "-", "x", "-", "x"},
                        {"-", "-", "x", "x", "-", "x"},
                        {"x", "x", "x", "x", "-", "x"},
                        {"x", "x", "-", "-", "-", "x"}};
        Map map = new Map(m);
        */


        // Display Map
        System.out.println("----------------------------------------");
        System.out.println("        This is the environment.        ");
        System.out.println("----------------------------------------\n");
        System.out.println(map.toString() + "\n\n");

        // ----------------------------------------------------------------------------------------------
        // Select Start
        System.out.println("----------------------------------------");
        System.out.println("     Select your Starting Position.     ");
        System.out.println("----------------------------------------");
        System.out.print("Row: ");
        int startRow = scan.nextInt();
        System.out.print("Column: ");
        int startColumn = scan.nextInt();

        while((map.getType(startRow,startColumn)).equals(Map.UNPATHABLE)){
            System.out.println("\nCannot choose a blocked area as a starting position.\n" +
                    "Choose again...");
            System.out.print("Row: ");
            startRow = scan.nextInt();
            System.out.print("Column: ");
            startColumn = scan.nextInt();
            map.setElement(startRow,startColumn,"s");
        }

        map.setElement(startRow,startColumn,"s");

        // ----------------------------------------------------------------------------------------------
        // Select Goal
        System.out.println("----------------------------------------");
        System.out.println("       Select your Goal Position.       ");
        System.out.println("----------------------------------------");
        System.out.print("Row: ");
        int goalRow = scan.nextInt();
        System.out.print("Column: ");
        int goalColumn = scan.nextInt();

        while((map.getType(goalRow, goalColumn)).equals(Map.UNPATHABLE)){
            System.out.println("\nCannot choose a blocked area as a goal position.\n" +
                    "Choose again...");
            System.out.print("Row: ");
            goalRow = scan.nextInt();
            System.out.print("Column: ");
            goalColumn = scan.nextInt();
            map.setElement(goalRow,goalColumn,"g");
        }

        map.setElement(goalRow,goalColumn,"g");

        // ----------------------------------------------------------------------------------------------
        // Display map again with Start and Goal labeled
        System.out.println("\n\n" + map.toString());

        // ----------------------------------------------------------------------------------------------
        map.generatePath(startRow,startColumn,goalRow,goalColumn);
        System.out.println("Path to goal is...\n" + map.displayPath());

        // ----------------------------------------------------------------------------------------------
        // Display map again
        System.out.println("\n----------------------------------------");
        System.out.println("           Map with Path Shown.         ");
        System.out.println("----------------------------------------");
        map.updateMap();
        System.out.println("\n\n" + map.toString());


    }
}
