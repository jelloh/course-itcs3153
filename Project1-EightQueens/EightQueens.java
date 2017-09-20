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
  * 1 - Place each queen randomly in its column.
  * 2 - Check for goal state right away (no queens in conflict)
  * 3 - Evaluate all possible neighbor states by moving each queen
  *     up and down it's column. Keep track of each heuristic value.
  * 4 - If a lower heuristic was NOT found, restarts
  * 5 - Else, update current state to the newly found lower heuristic state
  *
  * Heuristic: How far away you are from the goal. In this case, it is
  *            # of conflicts.
  */


public class EightQueens{

  public static void main(String[] args){

    // Testing stuff
    State state = new State();
    System.out.println(state.toString());

    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        if(state.getGrid()[i][j] == 1){
          System.out.println(state.checkVertical(i,j) + " ...vertical");

          System.out.println(state.checkHorizontal(i,j) + "...horizontal");

          System.out.println(state.checkDiagonal(i,j) + "...diagonal");
          System.out.println();
        }
      }
    }



  }
}
