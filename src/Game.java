import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * The main class for running the game, including initializing the game board, managing players' turns,
 * handling player movement, checking for lost players, and saving game results to a file.
 */
public class Game {

    // ANSI color codes for displaying colors in the console
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";  // Green color for Player 1
    public static final String BLUE = "\u001B[34m";   // Blue color for Player 2
    public static final String YELLOW = "\u001B[33m";  // Yellow color for Player 3
    public static final String PURPLE = "\u001B[35m";  // Purple color for Player 4

    /**
     * The entry point for the game, where players are initialized, and the main game loop runs.
     * It manages the turns, checks for blocked (lost) players, updates the scores, and writes the results to a file.
     *
     * @param players The array of players participating in the game.
     */
    public static void main(Player... players) {
        // Create a game board with 10 rows and 11 columns
        char[][] board = Board.CreateBoard(10, 11);

        // Manually place players on fixed positions on the board
        int[][] playerPositions = new int[players.length][2];
        if (players.length > 0) {
            Player.placePlayer(board, '1', 4, 4); // Player 1 at E4
            playerPositions[0] = new int[] {4, 4}; // Store position for Player 1
        }
        if (players.length > 1) {
            Player.placePlayer(board, '2', 5, 4); // Player 2 at E5
            playerPositions[1] = new int[] {5, 4}; // Store position for Player 2
        }
        if (players.length > 2) {
            Player.placePlayer(board, '3', 4, 5); // Player 3 at F4
            playerPositions[2] = new int[] {4, 5}; // Store position for Player 3
        }
        if (players.length > 3) {
            Player.placePlayer(board, '4', 5, 5); // Player 4 at F5
            playerPositions[3] = new int[] {5, 5}; // Store position for Player 4
        }

        // Display the board after placing the players
        Board.showBoard(board);

        // Randomly select the first player
        Random random = new Random();
        int currentPlayerIndex = random.nextInt(players.length); // Random selection

        // Create an array to track which players are eliminated
        boolean[] isEliminated = new boolean[players.length];

        // Main game loop (turn by turn)
        while (true) {

            // Count how many players are still in the game (not eliminated)
            int remainingPlayers = 0;
            for (int i = 0; i < isEliminated.length; i++) {
                if (!isEliminated[i]) {
                    remainingPlayers++;
                }
            }

            // If only one player remains, they win
            if (remainingPlayers == 1) {
                for (int i = 0; i < players.length; i++) {
                    if (!isEliminated[i]) {
                        System.out.println(players[i].getPseudo() + RESET + " is the winner!");
                        Menu.menu();
                    }
                }
            }

            // If the current player is eliminated, skip their turn
            if (isEliminated[currentPlayerIndex]) {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
                continue;
            }

            Player currentPlayer = players[currentPlayerIndex];

            // Determine the color associated with the current player
            String currentColor = "";
            switch (currentPlayerIndex) {
                case 0: currentColor = GREEN; break;
                case 1: currentColor = BLUE; break;
                case 2: currentColor = YELLOW; break;
                case 3: currentColor = PURPLE; break;
            }

            // Display the player's turn with their associated color
            System.out.println(currentColor + currentPlayer.getPseudo() + RESET + "'s turn");

            // Get the current position from the playerPositions array
            int currentRow = playerPositions[currentPlayerIndex][0];
            int currentCol = playerPositions[currentPlayerIndex][1];

            // Check if the player is blocked (lost the game)
            if (Cell.checkIfPlayerLost(board, currentRow, currentCol)) {
                System.out.println(currentColor + currentPlayer.getPseudo() + RESET + " is blocked and has lost!");
                currentPlayer.updateScore(-2); // Deduct points for losing
                System.out.println(currentPlayer.getPseudo() + " new score: " + currentPlayer.getScore());

                // Update the scores for the remaining players
                for (int i = 0; i < players.length; i++) {
                    if (i != currentPlayerIndex && !Cell.checkIfPlayerLost(board, currentRow, currentCol)) {
                        players[i].updateScore(5); // Award points for other players
                        System.out.println(players[i].getPseudo() + " new score: " + players[i].getScore());
                    }
                }

                // Write the game results to a file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Scores-Games.txt", true))) {
                    // Write the lost player's score
                    writer.write(currentPlayer.getPseudo() + " has lost! New score: " + currentPlayer.getScore());
                    writer.newLine();

                    // Write the scores of the remaining players
                    for (int i = 0; i < players.length; i++) {
                        if (i != currentPlayerIndex) {
                            writer.write(players[i].getPseudo() + " score: " + players[i].getScore());
                            writer.newLine();
                        }
                    }

                    // Add a separator for the end of the game event
                    writer.write("---- End of Event ----");
                    writer.newLine();
                } catch (IOException e) {
                    // Handle any exceptions during file writing
                    System.err.println("Error writing to the file: " + e.getMessage());
                }

                break; // End the game
            }

            // Perform the player's move
            boolean validMove = false;
            while (!validMove) {
                int[] newPosition = Cell.MovePlayer(board, (char) ('1' + currentPlayerIndex), currentRow, currentCol);

                // If the move is valid, update the player's position on the board and in the playerPositions array
                if (newPosition != null) {
                    currentRow = newPosition[0];
                    currentCol = newPosition[1];
                    validMove = true;

                    // Update the player's position
                    playerPositions[currentPlayerIndex] = newPosition;

                    // Display the updated board
                    Board.showBoard(board);
                } else {
                    // If the move is invalid, prompt the player to try again
                    Board.showBoard(board);
                    System.out.println(currentColor + currentPlayer.getPseudo() + RESET + "'s turn");
                }
            }

            // Move to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        }
    }
}
