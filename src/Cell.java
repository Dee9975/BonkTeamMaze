
public class Cell {

    private boolean left;
    private boolean top;
    private boolean right;
    private boolean bottom;
    private boolean visited;

    private int x;
    private int y;

    public Cell(int x, int y) {
        left = true;
        top = true;
        right = true;
        bottom = true;
        visited = false;
        this.x = x;
        this.y = y;
    }

    //X index
    public int getX() {
        return x;
    }

    //Y index
    public int getY() {
        return y;
    }

    //Getters and Setters for the wall booleans
    public void setTop(boolean b) {
        top = b;
    }

    public void setBottom(boolean b) {
        bottom = b;
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public boolean getTop() {
        return top;
    }

    public boolean getBottom() {
        return bottom;
    }

    public boolean getRight() {
        return right;
    }

    public boolean getLeft() {
        return left;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean b) {
        visited = b;
    }

    public String toString() {
        return ("Cell (" + x + "," + y + ")");
    }

    public boolean equals(Cell cell) {
        return (this.x == cell.getX() && this.y == cell.getY());
    }
}