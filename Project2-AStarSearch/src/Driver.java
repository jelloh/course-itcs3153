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
        // Display Map
        System.out.println("----------------------------------------");
        System.out.println("        This is the environment.        ");
        System.out.println("----------------------------------------\n");
        System.out.println(map.toString() + "\n\n");

        // Select Start
        System.out.println("----------------------------------------");
        System.out.println("     Select your Starting Position.     ");
        System.out.println("----------------------------------------");
        System.out.print("Row: ");
        int startRow = scan.nextInt();
        System.out.print("Column: ");
        int startColumn = scan.nextInt();
        map.setElement(startRow,startColumn,"s");

        // Select Goal
        System.out.println("----------------------------------------");
        System.out.println("       Select your Goal Position.       ");
        System.out.println("----------------------------------------");
        System.out.print("Row: ");
        int goalRow = scan.nextInt();
        System.out.print("Column: ");
        int goalColumn = scan.nextInt();
        map.setElement(goalRow,goalColumn,"g");

        System.out.println("\n\n" + map.toString());

    }
}
