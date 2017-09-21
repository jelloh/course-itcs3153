/**
  * @author Aileen Benedict
  * @version 09/19/2017
  *
  * Instructions:
  * Write a program that places 8 queens on an 8x8 board where none
  * of the queens are in conflict with each other. You are to
  * implement the Hill-Climbing algorithm with random restarts...
  *
  * Steps:
  * 1 - Place each queen randomly in its column. ------------------------------------ done
  * 2 - Check for goal state right away (no queens in conflict) --------------------- done
  * 3 - Evaluate all possible neighbor states by moving each queen ------------------ done
  *     up and down it's column. Keep track of each heuristic value.
  * 4 - If a lower heuristic was NOT found, restarts -------------------------------- done
  * 5 - Else, update current state to the newly found lower heuristic state --------- done
  *
  * Heuristic: How far away you are from the goal. In this case, it is
  *            # of conflicts.
  */

import java.util.*;

public class EightQueens{

  public static void main(String[] args){
    // Variables
    int restarts = 0;
    int stateChanges = 0;
    final int SIZE = 8;

    State currentState = new State(SIZE);

    //State currentState = new State();

    // While Loop
    while(!currentState.isGoal()) {
      int h = currentState.getHeuristic();
      // Display info (current state and its heuristic)
      System.out.println("Current h: " + h);
      System.out.println("Current State");
      System.out.println(currentState.toString());

      // Info of state's neighbors
      HashMap neighbors = currentState.getAllNeighbors();
      Set set = neighbors.entrySet();
      Iterator i = set.iterator();

      int lowerNeighbors = 0;               // # of neighbors with lower heuristic
      State lowestNeighbor = currentState;  // The lowest neighbor

      // Loop through to find # of lower neighbors and the lowest one
      while(i.hasNext()){
        Map.Entry me = (Map.Entry)i.next();
        if((int)(me.getValue()) < h) lowerNeighbors++;
        if(((State)me.getKey()).getHeuristic() < lowestNeighbor.getHeuristic())
          lowestNeighbor = (State)me.getKey();
      }

      System.out.println("Neighbors found with lower h: " + lowerNeighbors);

      // If no lower neighbors, then restart and generate a new random state
      if(lowerNeighbors == 0){
        System.out.println("RESTART");
        currentState = new State(SIZE);
        restarts ++;
      }
      // Otherise, set currentState to the lowest neighbor and keep checking states
      else {
        System.out.println("Setting new current state\n");
        currentState = lowestNeighbor;
        stateChanges++;
      }

    } // end while loop


    // Display solution and info
    System.out.println(currentState.toString());
    System.out.println("Solution Found!");
    System.out.println("State changes: " + stateChanges);
    System. out.println("Restarts: " + restarts);

  }
}
