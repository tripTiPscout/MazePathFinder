package maze.pathfinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Reads information from local CSV file and return text
 */
public class FileReader {

    public String readFile(String path) {
        StringBuilder fileText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(path))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                fileText.append(currentLine).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileText.toString();
    }

    public String getRowCoordinates(String path) throws FileNotFoundException {
        File file = new File(path);
        String coordinates;
        try (Scanner input = new Scanner(file)) {
            coordinates = input.nextLine();
        }
        return coordinates;
    }

}
