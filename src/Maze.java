import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Maze {

    //Basic maze components
    private final int WIDTH;
    private final int LENGTH;
    private Cell[][] maze;
    private Cell start;

    //PRIMS SET UP
    private Map<Cell, List<Integer>> wallMap = new HashMap<>();
    private Random rand = new Random();
    private List<Cell> keyList = new ArrayList<>();

    //Maze constructor
    public Maze(int width, int length) {
        this.WIDTH = width;
        this.LENGTH = length;
        maze = new Cell[WIDTH][LENGTH];
        generateMaze();
    }

    public Cell[][] getMaze() {
        return maze;
    }

    //Make the maze
    public void generateMaze() {
        for (int i = 0; i < WIDTH; ++i) {
            for (int j = 0; j < LENGTH; ++j) {
                maze[i][j] = new Cell(i, j);
            }
        }

        start = maze[1][1];

        setBorder();
        prims(start);
    }

    private void setBorder() {
        //Draws left and right side border
        for (int i = 1; i < WIDTH; i++) {
            maze[0][i].setLeft(false);
            maze[0][i].setRight(false);
            maze[0][i].setTop(false);
            maze[0][i].setBottom(false);
            maze[1][i].setLeft(true);
            maze[LENGTH - 1][i].setRight(true);
        }
        //Draws bottom and top border
        for (int i = 1; i < LENGTH; i++) {
            maze[i][0].setLeft(false);
            maze[i][0].setTop(false);
            maze[i][0].setRight(false);
            maze[i][0].setBottom(false);
            maze[i][1].setTop(true);
            maze[i][WIDTH - 1].setBottom(true);
        }

        maze[0][0].setRight(false);
        maze[0][0].setBottom(false);
        maze[1][1].setLeft(false);
        maze[LENGTH - 1][WIDTH - 1].setRight(false);
    }

    private void prims(Cell root) {
        //Initial set up
        List<Integer> foundWalls = findWalls(root);
        //Finding initial walls
        if (foundWalls.size() > 0) {
            wallMap.put(root, foundWalls); //Creating wall list
        }
        // Getting cells (keys) in wallMap
        keyList.addAll(wallMap.keySet());
        int index = rand.nextInt(keyList.size());
        Cell current = keyList.get(index); //Random key (cell) picked


        //Main section of code
        //Goes through wallMap until it is empty
        while (!wallMap.isEmpty()) {
            //After picking random wall from wall list
            current.setVisited(true);

            List<Integer> walls = wallMap.get(current); //Get random list of walls from the chosen cell
            int location = walls.get(rand.nextInt(walls.size())); //Random wall (location) picked out of list

            //Checks if opposite/neighbor cell is in the maze or not
            //Neighbor not added to wallMap if it has been visited
            if (!neighborVisited(current, location)) {
                Cell nextCell = primsWallBuster(current, location); //Breaks into a new cell and breaks wall
                nextCell.setVisited(true);
                List<Integer> w = findWalls(nextCell);
                if (w.size() > 0 && !keyList.contains(nextCell)) { //If neighbots exist and the nextCell has not been visited
                    keyList.add(nextCell);
                    wallMap.put(nextCell, w);
                }

            }
            //Wall is removed from walls List
            //Location must be cast to Integer bc removing that from the list
            walls.remove((Integer) location); //Removing wall
            //Taking care of key that is mapped to walls
            //Book keeping
            if (walls.size() == 0) { //If all the walls have been checked removed the cell
                wallMap.remove(current);
                keyList.remove(current);

                //Gets out of the while loop when the wallMap is empty
                //MST is done
                if (keyList.size() == 0) {
                    System.out.println("Labirinta izmēri:\nPlatums: " + WIDTH + "\nAugstums: " + LENGTH);
                    break;
                }
            }
            index = rand.nextInt(keyList.size());
            current = keyList.get(index); //Finds a new cell and starts over
        }
    }

    private List<Integer> findWalls(Cell curr) {
        List<Integer> walls = new ArrayList<>();
        if (curr != null) {
            Cell[] n = findPrimsNeighbors(curr.getX(), curr.getY()); //Current cell X and Y value
            //Finding neighbors --> then walls that correspond
            //Finding walls that have not been torn down
            if (n[0] != null) {
                walls.add(0); //Left wall
            }
            if (n[1] != null) {
                walls.add(1); //Bottom wall
            }
            if (n[2] != null) {
                walls.add(2); //Right wall
            }
            if (n[3] != null) {
                walls.add(3); //Top wall
            }
        }
        return walls;
    }

    // Checks to see if the neighbor has been visited
    // Returns a boolean --> used in prims() method above
    private boolean neighborVisited(Cell curr, int location) {
        if (location == 0) {
            return maze[curr.getX() - 1][curr.getY()].getVisited();
        } else if (location == 1) {
            return maze[curr.getX()][curr.getY() - 1].getVisited();
        } else if (location == 2) {
            return maze[curr.getX() + 1][curr.getY()].getVisited();
        } else if (location == 3) {
            return maze[curr.getX()][curr.getY() + 1].getVisited();
        }
        return true;
    }

    private Cell[] findPrimsNeighbors(int x, int y) {
        Cell[] neighbors = new Cell[4];
        if (!((x-1) < 1) && !maze[x-1][y].getVisited()) {
            neighbors[0] = maze[x-1][y];
        }
        if (!((y-1) < 1) && !maze[x][y-1].getVisited()) {
            neighbors[1] = maze[x][y-1];
        }
        if (!((x+1) > (WIDTH-1)) && !maze[x+1][y].getVisited()) {
            neighbors[2] = maze[x+1][y];
        }
        if (!((y+1) > (LENGTH-1)) && !maze[x][y+1].getVisited()) {
            neighbors[3] = maze[x][y+1];
        }
        return neighbors;
    }

    private Cell primsWallBuster(Cell curr, int location) {
        if (curr != null) {
            if (location == 0) { //Bust Left
                curr.setLeft(false);
                maze[curr.getX() - 1][curr.getY()].setRight(false);
                return maze[curr.getX() - 1][curr.getY()];
            } else if (location == 1) { //Bust Bottom
                curr.setTop(false);
                maze[curr.getX()][curr.getY() - 1].setBottom(false);
                return maze[curr.getX()][curr.getY() - 1];
            } else if (location == 2) { //Bust Right
                curr.setRight(false);
                maze[curr.getX() + 1][curr.getY()].setLeft(false);
                return maze[curr.getX() + 1][curr.getY()];
            } else if (location == 3) { //Bust Top
                curr.setBottom(false);
                maze[curr.getX()][curr.getY() + 1].setTop(false);
                return maze[curr.getX()][curr.getY() + 1];
            }
            curr.setVisited(true);
        }
        return null;
    }
}