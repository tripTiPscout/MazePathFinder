package maze.pathfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represent a maze like a 2D matrix with fields that describe every type of possible cell in it
 */
public class Maze {
    private int[][] maze;
    private Cell start;
    private Cell end;
    private boolean[][] visited;
    private static final int ROAD = 0;
    private static final int WALL = 1;
    private static final int START = 2;
    private static final int END = 3;
    private static final int PATH = 4;

    /**
     * Four directions of possible moves in labyrinth
     */
    private static final int[][] DIRECTIONS_STRAIGHT = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public static boolean IS_FOUND = false;
    public static int[][] FINAL_MAZE_PATH;

    public Maze(String mazeData) {
        initializeMaze(mazeData);
    }

    /**
     * The method initializes each cell in a 2D matrix with a value obtained from the extracted text information
     */
    private void initializeMaze(String textData) {
        if (textData == null || textData.length() == 0) {
            throw new IllegalArgumentException("Empty lines data.");
        }

        String[] lines = textData.split(",?\n");
        maze = new int[lines.length][lines[0].length()];
        visited = new boolean[lines.length][lines[0].length()];

        for (int row = 1; row < getHeight(); row++) {
            if (lines[row].length() != getWidth()) {
                throw new IllegalArgumentException
                        ("Line " + (row) + " wrong length (was " + lines[row].length()
                                + " but should be " + getWidth() + ").");
            }

            for (int col = 1; col < getWidth(); col++) {
                if (lines[row].charAt(col) == 'N')
                    maze[row][col] = WALL;
                else if (lines[row].charAt(col) == 'M') {
                    maze[row][col] = START;
                    start = new Cell(row, col);
                } else if (lines[row].charAt(col) == 'X') {
                    maze[row][col] = END;
                    end = new Cell(row, col);
                } else if (lines[row].charAt(col) == ' ') {
                    maze[row][col] = ROAD;
                }
            }
        }
    }

    public int getHeight() {
        return maze.length;
    }

    public int getWidth() {
        return maze[0].length;
    }

    public Cell getEntry() {
        return start;
    }

    /**
     * Using Bread First Search (BFS) Algorithm to find the shortest path in labyrinth
     */
    public List<Cell> findPath(Maze maze) {
        LinkedList<Cell> queue = new LinkedList<>();
        Cell start = maze.getEntry();
        queue.addFirst(start);

        while (!queue.isEmpty()) {
            Cell currentPosition = queue.removeFirst();

            if (maze.invalidLocation(currentPosition.getX(), currentPosition.getY())
                    || maze.isExplored(currentPosition.getX(), currentPosition.getY())) {
                continue;
            }

            if (maze.isWall(currentPosition.getX(), currentPosition.getY())) {
                maze.setVisited(currentPosition.getX(), currentPosition.getY(), true);
                continue;
            }

            if (maze.isFound(currentPosition.getX(), currentPosition.getY())) {
                IS_FOUND = true;
                return backtrackPath(currentPosition);
            }

            for (int[] direction : DIRECTIONS_STRAIGHT) {
                Cell nextPosition = new Cell(currentPosition.getX() + direction[0],
                        currentPosition.getY() + direction[1], currentPosition);
                queue.add(nextPosition);
                maze.setVisited(currentPosition.getX(), currentPosition.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    public boolean isStart(int x, int y) {
        return x == start.getX() && y == start.getY();
    }

    public boolean isFound(int x, int y) {
        return x == end.getX() && y == end.getY();
    }

    public boolean isWall(int row, int col) {
        return maze[row][col] == WALL;
    }

    public boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    public void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }

    public boolean invalidLocation(int row, int col) {
        return row < 1 || row >= getHeight() || col < 1 || col >= getWidth();
    }

    private static List<Cell> backtrackPath(Cell current) {
        List<Cell> path = new ArrayList<>();
        Cell next = current;

        while (next != null) {
            path.add(next);
            next = next.parent;
        }

        consolePrint(path);
        return path;
    }

    /**
     * Method initializes founded path in labyrinth into 2D matrix
     */
    public void initializePath(List<Cell> path) {
        int[][] mazePath = Arrays.stream(maze)
                .map(int[]::clone)
                .toArray(int[][]::new);
        for (Cell cell : path) {
            if (isStart(cell.getX(), cell.getY()) || isFound(cell.getX(), cell.getY())) {
                continue;
            }
            mazePath[cell.getX()][cell.getY()] = PATH;
        }
        FINAL_MAZE_PATH = mazePath;
    }

    /**
     * Method returns text representation of labyrinth with tracked path
     */
    public String visualizeMazePath(int[][] maze) {
        StringBuilder result = new StringBuilder(maze[0].length * (maze.length + 1));
        result.append("Visualization: ");
        result.append(System.lineSeparator());
        result.append(System.lineSeparator());
        result.append(Main.COORDINATES);
        result.append('|');
        result.append(System.lineSeparator());
        for (int row = 1; row < maze.length; row++) {
            result.append(row);
            for (int col = 1; col < maze[0].length; col++) {
                if (maze[row][col] == PATH) {
                    result.append('.');
                } else if (maze[row][col] == WALL) {
                    result.append('N');
                } else if (maze[row][col] == START) {
                    result.append('M');
                } else if (maze[row][col] == END) {
                    result.append('X');
                } else {
                    result.append(' ');
                }
            }
            result.append('|');
            result.append(System.lineSeparator());
        }
        return result.toString();
    }

    public static void consolePrint(List<Cell> path) {
        Collections.reverse(path);
        System.out.printf("%nFind path in %d steps.%n", path.size() - 1);
        path.forEach(System.out::print);
        System.out.println();
        System.out.println();
    }

}
