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

    private String[][] map;
    private int mapSize = 15; // Default size

    private final String UNPATHABLE = "x";
    private final String PATHABLE = "-";

    /** Empty constructor */
    public Map(){
        map = new String[mapSize][mapSize];
        generateMap();
    }

    /** Choose map size */
    public Map(int size){
        mapSize = size;
        map = new String[mapSize][mapSize];
        generateMap();
    }

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

    public String[][] getMap() {
        return map;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setElement(int row, int column, String symbol){
        map[row][column] = symbol;
    }

    public String toString() {
        String result = "";
        /*
        for (int i = 0; i < mapSize; i++){
            result += i + "\t";
        }
        result += "\n";
        */
        for (int i = 0; i < mapSize; i++) {
            //result += i + "\t";
            for (int j = 0; j < mapSize; j++) {
                result += map[i][j] + " ";
            }
            result += "\n";
        }

        return result;
    }
}
