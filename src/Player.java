import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * The {@code Player} class represents a player in the game.
 * It contains information about the player's pseudo and score,
 * and provides methods to manage and interact with game mechanics such as
 * updating the score, displaying scores, and creating player objects.
 * <p>
 * The class includes methods to load scores from a file, update a player's score,
 * and prompt the user to input the number of players for the game.
 * It also handles special cases like the "Easter egg" for a player named "Clement".
 */
public class Player {

    public static final String GREEN = "\u001B[32m";  // Green color for Player 1

    private String pseudo;
    private int score;

    /**
     * Constructor to initialize a Player with a pseudo.
     *
     * @param pseudo The pseudo of the player.
     */
    public Player(String pseudo) {
        this.pseudo = pseudo;
        this.score = 0;
    }

    /**
     * Gets the pseudo of the player.
     *
     * @return The player's pseudo.
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Gets the current score of the player.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Displays all the scores stored in a given file.
     *
     * @param filePath The path to the file containing the scores.
     * @throws IOException If an error occurs while reading the file.
     */
    public static void displayAllScores(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("Content of Scores-Games.txt:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    /**
     * Loads scores from a file and stores them in an integer array.
     * The array can hold up to 100 scores.
     *
     * @param filePath The path to the file containing the scores.
     * @return An array of integers containing the scores.
     * @throws IOException If an error occurs while reading the file.
     */
    public static int[] loadScoresFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int[] scores = new int[100];
            String line;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                if (line.contains("score:")) {
                    String[] parts = line.split("score: ");
                    if (parts.length == 2) {
                        scores[index] = Integer.parseInt(parts[1].trim());
                        index++;
                    }
                }
            }

            return scores;
        } catch (IOException e) {
            System.out.println("Error while reading the file: " + e.getMessage());
            return new int[0];
        }
    }

    /**
     * Updates the player's score by adding the specified points.
     *
     * @param points The number of points to add to the player's score.
     */
    public void updateScore(int points) {
        this.score += points;
    }

    /**
     * Places the player's symbol on the specified position in the game board.
     *
     * @param board        The game board represented as a 2D char array.
     * @param playerSymbol The player's symbol to place on the board.
     * @param row          The row index where the symbol will be placed.
     * @param col          The column index where the symbol will be placed.
     */
    public static void placePlayer(char[][] board, char playerSymbol, int row, int col) {
        board[row][col] = playerSymbol;
    }

    /**
     * Prompts the user to enter the number of players and validates the input.
     * The number must be between 2 and 4.
     *
     * @return The number of players.
     */
    public static int requestPartyNumber() {
        Scanner scanner = new Scanner(System.in);
        byte numberOfPlayers;

        System.out.println("Please enter the number of players you would like to play (2 - 4):");

        while (true) {
            try {
                numberOfPlayers = Byte.parseByte(scanner.nextLine());
                if (numberOfPlayers >= 2 && numberOfPlayers <= 4) {
                    return numberOfPlayers;
                } else {
                    System.out.println("Please enter a valid number between 2 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    /**
     * Creates player objects based on the number of players.
     * Prompts the user for a valid pseudo for each player.
     * Includes an Easter egg if a player enters the name "Clement".
     *
     * @param numberOfPlayers The number of players.
     * @return An array of {@link Player} objects.
     */
    public static Player[] createPlayers(int numberOfPlayers) {
        Scanner scanner = new Scanner(System.in);
        Player[] players = new Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            String pseudo;
            boolean validPseudo;

            do {
                validPseudo = true;
                System.out.println("Enter Player " + (i + 1) + " name (2-10 characters):");
                pseudo = scanner.nextLine();

                // EASTER EGG
                if (pseudo.equalsIgnoreCase("Clement")) {
                    while (true) {
                        // Add "Error" in green
                        String glitchMessage = GREEN + "ERROR ";

                        // Add random characters to the glitch message
                        for (int j = 0; j < 10; j++) {
                            char randomChar = (char) (33 + (Math.random() * 94)); // Generate a random character
                            glitchMessage += randomChar; // Add to the message
                        }
                        System.out.println(glitchMessage); // Display the message
                    }
                }

                // Validate pseudo
                if (pseudo.length() < 2 || pseudo.length() > 10 || (i > 0 && pseudo.equals(players[0].getPseudo()))) {
                    System.out.println("The name must be between 2 and 10 characters and different from Player 1's name. Please try again.");
                    validPseudo = false;
                }

            } while (!validPseudo);

            players[i] = new Player(pseudo);
            System.out.println("Player " + (i + 1) + " is now " + pseudo + ".");
        }

        return players;
    }
}
