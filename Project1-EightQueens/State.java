

public class State{

  // --------------------------------------------------------------------------

  private int N = 8; // Size of grid. Default to 8
  private int grid[][] = new int[N][N];
  private int heuristic;

  // --------------------------------------------------------------------------
  // CONSTRUCTORS -------------------------------------------------------------

  /* Default Constructor. Randomly generates a new state of size 8. */
  public State(){
    // New grid generated
    generateRandomGrid();
    // Immediately check and save the heuristic value
    this.heuristic = checkHeuristic();
  }

  /*  Constructor. Randomly generates a new state of size N.
   * @param int n to determine the size of the grid
   */
  public State(int n){
    this.N = n;
    // New grid generated
    generateRandomGrid();
    // Immediately check and save the heuristic value
    this.heuristic = checkHeuristic();
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

  private void generateRandomGrid(){
        // Start with all 0's
        for(int i = 0; i < N; i++){
          for(int j = 0; j < N; j++){
            grid[i][j] = 0;
          }
        }

        // Place 8 queens (value of 1) in random row per column
        for(int i = 0; i < N; i++){
          int rand = (int)(Math.random()*N);
          grid[rand][i] = 1;
        }
  }

  public boolean isGoal(){
    if(heuristic == 0) return true;
    else return false;
  }

  private int checkHeuristic(){
    int conflicts = 0;
    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++){
        if(grid[i][j] == 1){
          conflicts += checkHorizontal(i,j);
          conflicts += checkVertical(i,j);
          conflicts += checkDiagonal(i,j);
        }
      }
    }

    return conflicts/2;
  }

  /* @return - The number of conflicts that arise veritcally
   * @param - row and column of the queen to checkVertical
   */
  private int checkVertical(int row, int column){
    //System.out.print("Queen at " + row + ", " + column + "...");
    int conflicts = 0;

    for(int i = 0; i < N; i++){
      if(i == row) continue;
      if(grid[i][column] == 1) conflicts++;
    }

    return conflicts;
  }

  /* @return - The number of conflicts that arise horizontally
   * @param - row and column of the queen to checkVertical
   */
  private int checkHorizontal(int row, int column){
    //System.out.print("Queen at " + row + ", " + column + "...");
    int conflicts = 0;

    for(int i = 0; i < N; i++){
      if(i == column) continue;
      if(grid[row][i] == 1) conflicts++;
    }

    return conflicts;
  }

  /* @return - The number of conflicts that arise diagonally
   * @param - row and column of the queen to checkVertical
   */
  private int checkDiagonal(int row, int column){
    //System.out.print("Queen at " + row + ", " + column + "...");
    int conflicts = 0;

    for(int i = row-1, j = column-1; i >= 0 && j >= 0; i--, j--){
      if(grid[i][j] == 1) conflicts++;
    }

    for(int i = row+1, j = column+1; i < N && j < N; i++, j++){
      if(grid[i][j] == 1) conflicts++;
    }

    for(int i = row+1, j = column-1; i < N && j >= 0; i++, j--){
      if(grid[i][j] == 1) conflicts++;
    }

    for(int i = row-1, j = column+1; i >= 0 && j < N; i--, j++){
      if(grid[i][j] == 1) conflicts++;
    }

    return conflicts;
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
