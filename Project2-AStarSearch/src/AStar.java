import java.util.ArrayList;

public class AStar {

    private Node[][] nodes;

    // Do these have to use min heap ?!? -------------------------------------------- check later
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;

    private Node start;
    private Node goal;

    private int boundary;

    /**
     *
     * @param n - array list of nodes
     * @param sr - startRow
     * @param sc - startColumn
     * @param gr - goalRow
     * @param gc - goalColumn
     */
    public AStar(Node[][] n, int sr, int sc, int gr, int gc, int b){
        boundary = b;

        // Initialize Things
        nodes = n;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();

        // Set Goal Node
        goal = nodes[gr][gc];

        // First add Starting Node to Open List
        start = nodes[sr][sc];
        start.setG(0);
        start.setH(calculateHeuristic(start));
        start.setF();
        start.setParent(null);
        openList.add(start);

        // Call Main Part of A* Search
        PotatoesAreYummy();
    }

    /**
     * Main part of A*
     */
    private void PotatoesAreYummy(){

        Node current; // = start;
        //openList.remove(start);
        boolean pathFound = false;

        while(openList.size() != 0){ // TEEHEE

            // Step 1: Pop off node with lowest F and set as current node
            current = findLowest();
            openList.remove(current);

            // Step 2: Check if current node is the goal
            if(current.equals(goal)){
                // If so, generate the path
                generatePath();
                break;
            }

            // Generate Neighbors
            generateNeighbors(current);

        }

    }

    // Not sure how efficient this is :')..........
    // Generates neighbors and adds to open list if valid
    private void generateNeighbors(Node n){
        int r = n.getRow();
        int c = n.getCol();

        for(int i = 0; i < 8; i++){
            Node check;
            switch(i){
                case 0:
                    check = nodes[r + 1][c]; // Below
                    break;
                case 1:
                    check = nodes[r-1][c]; // Above
                    break;
                case 2:
                    check = nodes[r][c+1]; // Right
                    break;
                case 3:
                    check = nodes[r][c-1]; // Left
                    break;
                case 4:
                    check = nodes[r-1][c+1]; // Top Right
                    break;
                case 5:
                    check = nodes[r-1][c-1]; // Top Left
                    break;
                case 6:
                    check = nodes[r+1][c+1]; // Bottom Right
                    break;
                default:
                    check = nodes[r+1][c-1]; // Bottom Left
                    break;
            }

            if(isValid(check)){
                // Set G
                if(i < 4){
                    check.setG(10); // 10 for up, down, left, right
                }
                else{
                    check.setG(14); // 14 for diagonals
                }

                // Set H
                check.setH(calculateHeuristic(check));

                // Set F
                check.setF();

                // Set Parent
                check.setParent(n);

                // Add to Open List
                openList.add(check);

            }

        } // end for

    }

    private void generatePath(){

    }

    ///////////////////////////////////////////////////////////////////////////
    // Supporting Methods
    ///////////////////////////////////////////////////////////////////////////

    private boolean isValid(Node n){
        return withinBounds(n) && isPathable(n) &&
                !closedList.contains(n)? true : false;
    }

    private boolean withinBounds(Node n){
        int r = n.getRow();
        int c = n.getCol();

        if(r >= 0 && r < boundary && c >= 0 && c < boundary){
            return true;
        }
        return false;
    }

    private boolean isPathable(Node n){
        if(n.getType() == Node.PATHABLE) return true;
        else return false;
    }

    private Node findLowest(){
        if(openList.size() != 0) {
            Node lowest = openList.get(0);

            for (int i = 1; i < openList.size(); i++) {
                if(openList.get(i).getF() < lowest.getF()){
                    lowest = openList.get(i);
                }
            }

            return lowest;
        }

        return null;
    }

    private int calculateHeuristic(Node n){
        int curRow = n.getRow(), curCol = n.getCol();
        int heuristic = 0;
        while(curRow < goal.getRow()){
            curRow++;
            heuristic += 10;
        }
        while(curRow > goal.getRow()){
            curRow--;
            heuristic += 10;
        }
        while(curCol < goal.getCol()){
            curCol++;
            heuristic += 10;
        }
        while(curCol > goal.getCol()){
            curCol--;
            heuristic += 10;
        }

        //System.out.println(heuristic);

        return heuristic;
    }


}
