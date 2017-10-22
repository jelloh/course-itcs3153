/**
 * @author Aileen Benedict
 *
 */

public class Map {

    private String[][] map;
    private int mapSize = 15; // Default size

    /** Empty constructor */
    public Map(){
        map = new String[mapSize][mapSize];
    }

    /** Choose map size */
    public Map(int size){
        mapSize = size;
        map = new String[mapSize][mapSize];
    }

    public void generateMap(){

    }

}
