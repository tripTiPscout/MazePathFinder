package maze.pathfinder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static String COORDINATES;

    public static void main(String[] args) {

        String filePath = "D:\\IntelliJ_IDEA\\Projects\\MazePathFinder\\files\\maze.csv";

        FileReader reader = new FileReader();

        try {
            COORDINATES = reader.getRowCoordinates(filePath);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        String fileData = reader.readFile(filePath);
        Maze maze = new Maze(fileData);

        List<Cell> path = maze.findPath(maze);

        if (Maze.IS_FOUND) {
            maze.initializePath(path);

            String visualizeMazePath = maze.visualizeMazePath(Maze.FINAL_MAZE_PATH);
            System.out.print(visualizeMazePath);

            FileWriter writer = new FileWriter();
            try {
                writer.writeToFile(path, visualizeMazePath);
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        } else {
            System.out.println("There is no path!");
        }

    }

}
