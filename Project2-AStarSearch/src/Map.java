import java.util.ArrayList;

/**
 * @author Aileen Benedict
 * @version 10/22/2017
 *
 * Note:
 * x -> unpathable block
 * - -> pathable block
 * Each block has a 10% chance of being unpathable.
 *
 */

public class Map {

    private String[][] map; // Use mostly for displaying map
    private int mapSize = 15; // Default size
    private String[][] mapPath;
    
    private Node[][] nodes; // Use for A* and generating values

    private ArrayList<Node> path; // Use to hold the path

    public static String UNPATHABLE = "x";
    public static String PATHABLE = "-";

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    
    /** Empty constructor */
    public Map(){
        map = new String[mapSize][mapSize];
        generateMap();
        nodes = new Node[mapSize][mapSize];
        generateNodes();

        mapPath = new String[mapSize][mapSize];
    }

    /** Choose map size */
    public Map(int size){
        mapSize = size;
        map = new String[mapSize][mapSize];
        generateMap();
        nodes = new Node[mapSize][mapSize];
        generateNodes();

        mapPath = new String[mapSize][mapSize];
    }

    /** Choose map itself */
    public Map(String[][] m){
        this.map = m;
        this.mapSize = m.length;

        nodes = new Node[mapSize][mapSize];
        generateNodes();

        mapPath = new String[mapSize][mapSize];
    }

    /** Choose map itself and symbols */
    public Map(String[][] m, String path, String noPath){
        UNPATHABLE = noPath;
        PATHABLE = path;

        this.map = m;
        this.mapSize = m.length;

        nodes = new Node[mapSize][mapSize];
        generateNodes();

        mapPath = new String[mapSize][mapSize];
    }

    ///////////////////////////////////////////////////////////////////////////
    // Generate Stuff
    ///////////////////////////////////////////////////////////////////////////
    
    public void generateMap(){
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){

                double prob = Math.random();
                if(prob < 0.10){
                    map[i][j] = UNPATHABLE;
                }
                else{
                    map[i][j] = PATHABLE;
                }

            }
        }
    }
    
    private void generateNodes(){
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                int type = map[i][j].equals(PATHABLE)? Node.PATHABLE : Node.UNPATHABLE;
                nodes[i][j] = new Node(i, j, type);
            }
        }
    }

    public void generatePath(int startRow, int startCol, int goalRow, int goalCol){
        AStar a = new AStar(nodes, startRow, startCol, goalRow, goalCol, mapSize);

        if(a.isPathFound()) {
            path = a.getPath();
        }
        else path = null;
    }

    public String displayPath(){
        if(path == null){
            return "No path was found";
        }
        else{
            String result = "";
            for(int i = path.size() - 1; i >= 0; i--){
                result += path.get(i).toString() + " ";
            }
            return result;
        }
    }

    public void updateMap(){

        resetPathedMap();

        if(path != null){
            int counter = 1;


            for(int i = path.size() - 1; i >= 0; i--){
               Node next = path.get(i);
               int r = next.getRow();
               int c = next.getCol();
               mapPath[r][c] = ""+ counter;
               counter++;

            }

        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // To Reset
    ///////////////////////////////////////////////////////////////////////////

    private void resetPathedMap(){
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                mapPath[i][j] = map[i][j];
            }
        }
    }

    public void resetNodes(){
        nodes = new Node[mapSize][mapSize];
        generateNodes();
    }

    public void resetPath(){
        path.clear();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Getters and Setters
    ///////////////////////////////////////////////////////////////////////////
    
    public String[][] getMap() {
        return map;
    }

    public String[][] getPathedMap() {
        return mapPath;
    }

    public int getMapSize() {
        return mapSize;
    }

    public String getType(int row, int col){
        return map[row][col];
    }

    public void setElement(int row, int column, String symbol){
        map[row][column] = symbol;
    }

    ///////////////////////////////////////////////////////////////////////////
    // To String
    ///////////////////////////////////////////////////////////////////////////
    
    public String toString() {
        String result = "";

        result += "\t";
        for (int i = 0; i < mapSize; i++){
            result += i + "\t";
        }
        result += "\n";

        for (int i = 0; i < mapSize; i++) {
            result += i + "\t";
            for (int j = 0; j < mapSize; j++) {
                result += map[i][j] + "\t";
            }
            result += "\n";
        }

        return result;
    }

    public String pathToString() {
        String result = "";

        result += "\t";
        for (int i = 0; i < mapSize; i++){
            result += i + "\t";
        }
        result += "\n";

        for (int i = 0; i < mapSize; i++) {
            result += i + "\t";
            for (int j = 0; j < mapSize; j++) {
                result += mapPath[i][j] + "\t";
            }
            result += "\n";
        }

        return result;
    }



}
