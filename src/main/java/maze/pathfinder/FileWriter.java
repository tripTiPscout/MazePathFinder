package maze.pathfinder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Writes result of the program to new local text file
 */
public class FileWriter {

    public void writeToFile(List<Cell> path, String result) throws FileNotFoundException {
        String outputPath = "D:\\IntelliJ_IDEA\\Projects\\MazePathFinder\\files\\output.txt";
        FileOutputStream outputStream = new FileOutputStream(outputPath);
        PrintWriter writer = new PrintWriter(outputStream);
        writer.print("Find path in " + (path.size() - 1) + " steps.");
        writer.print(System.lineSeparator());
        path.forEach(writer::print);
        writer.print(System.lineSeparator());
        writer.print(System.lineSeparator());
        writer.print(result);
        writer.close();
    }

}
