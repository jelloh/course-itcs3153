

public class State{

  // --------------------------------------------------------------------------

  private int N = 8; // Size of grid. Default to 8
  private int grid[][] = new int[N][N];
  private int heuristic;

  // --------------------------------------------------------------------------
  // CONSTRUCTORS -------------------------------------------------------------

  /* Default constructor. Random generates a new state. */
  public State(){
    // Start with all 0's
    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++){
        grid[i][j] = 0;
      }
    }

    // Place 8 queens (value of 1) in random row per column
    for(int i = 0; i < N; i++){
      int rand = (int)(Math.random()*8);
      grid[rand][i] = 1;
    }
  }

  // --------------------------------------------------------------------------
  // GETTERS ------------------------------------------------------------------

  public int[][] getGrid(){
    return grid;
  }

  public int getHeuristic(){
    return heuristic;
  }

  // --------------------------------------------------------------------------
  // SUPPORTING STUFF :^) -----------------------------------------------------

  private int checkHeuristic(){
    return 0;
  }

  /* @return - False if there is a confict vertically, true otherwise
   * @param - row and column of the queen to checkVertical
   */
  public boolean checkVertical(int row, int column){
    System.out.print("Queen at " + row + ", " + column + "...");

    for(int i = 0; i < N; i++){
      if(i == row) continue;
      if(grid[i][column] == 1) return false;
    }

    return true;
  }

  /* @return - False if there is a confict horizontally, true otherwise
   * @param - row and column of the queen to checkVertical
   */
  public boolean checkHorizontal(int row, int column){
    System.out.print("Queen at " + row + ", " + column + "...");

    for(int i = 0; i < N; i++){
      if(i == column) continue;
      if(grid[row][i] == 1) return false;
    }

    return true;
  }

  /* @return - False if there is a confict diagonally, true otherwise
   * @param - row and column of the queen to checkVertical
   */
  public boolean checkDiagonal(int row, int column){
    System.out.print("Queen at " + row + ", " + column + "...");

    for(int i = row-1, j = column-1; i >= 0 && j >= 0; i--, j--){
      if(grid[i][j] == 1) return false;
    }

    for(int i = row+1, j = column+1; i < N && j < N; i++, j++){
      if(grid[i][j] == 1) return false;
    }

    for(int i = row+1, j = column-1; i < N && j >= 0; i++, j--){
      if(grid[i][j] == 1) return false;
    }

    for(int i = row-1, j = column+1; i >= 0 && j < N; i--, j++){
      if(grid[i][j] == 1) return false;
    }

    return true;
  }


  // --------------------------------------------------------------------------
  // TO STRING METHOD ---------------------------------------------------------

  public String toString(){
    String strGrid = "";

    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++){
        strGrid += grid[i][j] + " ";
      }
      strGrid += "\n";
    }

    return strGrid;
  }

  // --------------------------------------------------------------------------

} // end class
