import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.Arrays;

/**
 * The {@code SaveScores} class is responsible for saving the scores of players to a text file.
 * This class demonstrates basic file writing functionality using the {@code FileWriter}.
 * It is intended to write player scores into a file, although the implementation currently
 * handles only a basic example with a fixed array of player names.
 * <p>
 * The current implementation writes the first player name from the {@code ScoresPlayer} array to a file.
 * <p>
 * This class is subject to modification to handle more complex game logic, including saving scores
 * in a more structured format and appending scores to the file instead of overwriting.
 */
public class SaveScores {

    /**
     * The main method that executes the file writing functionality.
     * It writes the name of the first player from the {@code ScoresPlayer} array to a file.
     * <p>
     * Note: Currently, this method writes a static array of player names to a file, and only the first
     * player name is written.
     *
     * @param args The command-line arguments, if any.
     * @throws IOException If an error occurs during file writing.
     */
    public static void main(String[] args) throws IOException {

        // Create a FileWriter to write to a text file
        FileWriter writer = new FileWriter("caca.txt");

        // Example array of player names
        String[] ScoresPlayer = {
                "Player1",
                "Plater2"
        };

        // Write the name of the first player to the file
        writer.write(ScoresPlayer[0]);

        // Close the writer to ensure the data is saved and resources are released
        writer.close();
    }
}
