

public class State{
  int grid[][] = new int[8][8];
  int heuristic;

  /*
   * Default constructor. Random generates a new state.
   */
  public State(){
    // Start with all 0's
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid[i].length; j++){
        grid[i][j] = 0;
      }
    }

    // Place 8 queens (value of 1)
    for(int i = 0; i < grid.length; i++){
      int rand = (int)(Math.random()*9);
      grid[i][rand] = 1;
    }
  }



  public String toString(){
    String strGrid = "";
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid[i].length; j++){
        strGrid += grid[i][j];
      } // end nested for
      strGrid += "\n";
    } // end for

    return strGrid;
  } // end toString


}
