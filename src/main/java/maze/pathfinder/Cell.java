package maze.pathfinder;

/**
 * Represent a cell with coordinates in 2D matrix
 */
public class Cell {
    int x;
    int y;
    Cell parent;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
    }

    public Cell(int x, int y, Cell parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", Main.COORDINATES.charAt(getY()), getX());
    }
}
