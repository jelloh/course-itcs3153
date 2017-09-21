import java.util.*;

public class State{

  // --------------------------------------------------------------------------

  private int N; // Size of grid. Default to 8
  private int grid[][];
  private int heuristic;

  // --------------------------------------------------------------------------
  // CONSTRUCTORS -------------------------------------------------------------

  /* Default Constructor. Randomly generates a new state of size 8. */
  public State(){
    this.N = 8;
    grid = new int[N][N];

    // New grid generated
    generateRandomGrid();
    // Immediately check and save the heuristic value
    this.heuristic = checkHeuristic();
  }

  /*  Constructor. Randomly generates a new state of size N.
   * @param - int n to determine the size of the grid
   */
  public State(int n){
    this.N = n;
    grid = new int[N][N];

    // New grid generated
    generateRandomGrid();
    // Immediately check and save the heuristic value
    this.heuristic = checkHeuristic();
  }

  /* Constructor. Allows a specific grid to be initialized based on the parameter
  * @param - int[][] to initialize this state's grid
   */
  public State(int[][] g){
    if(g.length == g[0].length){
      // Set grid to the parameter
      this.grid = g;
      // Set size
      N = g.length;
      // Immediately check and save the heuristic value
      this.heuristic = checkHeuristic();
    }
    else{
      System.out.println("Error. N*N grid was not entered.");
      // New grid generated
      generateRandomGrid();
      // Immediately check and save the heuristic value
      this.heuristic = checkHeuristic();
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
  // OTHER SUPPORTING STUFF :^) -----------------------------------------------

  /* Generates a random grid set up of one queen per column */
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

  /* @return - true if this state is a goal state, else false */
  public boolean isGoal(){
    if(heuristic == 0) return true;
    else return false;
  }

  /* Move a Queen from a start position to an end position
   * @param - startRow, startCol is the starting position
   * @param - endRow, endCol is the ending/goal position
   */
  public void moveQueen(int startRow, int startCol, int endRow, int endCol){
    // Only move if the entered queen is actually a queen (value of 1 and not 0)
    if(grid[startRow][startCol] == 1){
      grid[startRow][startCol] = 0; // Set to empty
      grid[endRow][endCol] = 1; // Set to queen
    }

    // Check Heuristic Again
    heuristic = checkHeuristic();
  }

  // --------------------------------------------------------------------------
  // FINDING HEURISTIC -------- -----------------------------------------------

  /* @return - the heuristic of this state */
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

    return strGrid.substring(0, strGrid.length() - 2);
  }

  // --------------------------------------------------------------------------
  // hello ---------------------------------------------------------

  public HashMap getAllNeighbors (){
    HashMap neighbors = new HashMap();

    // Iterate through entire grid
    for(int row = 0; row < N; row++){
      for(int col = 0; col < N; col++){

        // Find each queen
        if(grid[row][col] == 1){
          // Move the current queen to each row in the current column
          for(int i = 0; i < N; i++){
            if(i == row) continue; // Ignore if it is the original queen's row

            State s = new State(copyArray(grid, N));
            s.moveQueen(row, col, i, col); // Column remains the same
            neighbors.put(s, s.getHeuristic()); // Add to HashMap

          }
        }

      }
    }

    return neighbors;
  }

  // Pain in the butt :'(
  private int[][] copyArray(int[][] g, int size){

    int[][] a = new int[size][];
    for (int i = 0; i < size; i++) {
      a[i] = Arrays.copyOf(g[i], g[i].length);
    }

    return a;

  }

} // end class
