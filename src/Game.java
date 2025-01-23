import java.util.Random;

public class Game {

    // ANSI codes for colors
    public static final String RESET = "\u001B[0m";   // Reset to default color
    public static final String GREEN = "\u001B[32m";  // Green color for Player 1
    public static final String[] COLORS = { "\u001B[32m", "\u001B[34m", "\u001B[33m", "\u001B[35m" }; // Colors for up to 4 players
    public static final String BLUE = "\u001B[34m";   // Blue color for Player 2
    public static final String RED = "\u001B[31m";    // Red color for destroyed cells ('D')

    public static void main(Player... players) {
        // Create the game board with 10 rows and 11 columns
        char[][] board = Board.CreateBoard(10, 11);
        short ScorePlayer2 = 0;
        short ScorePlayer1 = 0;

        // Place all players on the board at random initial positions
        int[][] playerPositions = new int[players.length][2];
        Random random = new Random();
        for (int i = 0; i < players.length; i++) {
            int row = random.nextInt(board.length); // Utilise la hauteur du tableau
            int col = random.nextInt(board[0].length); // Utilise la largeur du tableau

            // Réessayez jusqu'à trouver une cellule vide
            while (board[row][col] != '.') {
                row = random.nextInt(board.length);
                col = random.nextInt(board[0].length);
            }

            playerPositions[i][0] = row;
            playerPositions[i][1] = col;

            // Place le joueur sur le plateau
            Player.placePlayer(board, (char) ('1' + i), row, col);
        }



        // Show the board after placing the players
        Board.showBoard(board);

        // Randomly determine which player will go first
        int currentPlayerIndex = random.nextInt(players.length);

        // Start a loop for the game (keep running until a win condition or defeat condition is met)
        while (true) {
            Player currentPlayer = players[currentPlayerIndex];
            int currentRow = playerPositions[currentPlayerIndex][0];
            int currentCol = playerPositions[currentPlayerIndex][1];

            // Display whose turn it is
            System.out.println(COLORS[currentPlayerIndex % COLORS.length] + currentPlayer.getPseudo() + RESET + "'s turn");

            // Check if the current player is blocked
            if (Cell.checkIfPlayerLost(board, currentRow, currentCol)) {
                System.out.println(COLORS[currentPlayerIndex % COLORS.length] + currentPlayer.getPseudo() + RESET + " is blocked and has lost!");
                System.out.println("Remaining players:");

                // Announce remaining players
                for (int i = 0; i < players.length; i++) {
                    if (i != currentPlayerIndex && !Cell.checkIfPlayerLost(board, playerPositions[i][0], playerPositions[i][1])) {
                        System.out.println(COLORS[i % COLORS.length] + players[i].getPseudo() + RESET);
                    }
                }
                currentPlayer.score=-2;
                //System.out.println(players[0].getPseudo() + players[0]);
                System.out.println(currentPlayer.getScore());
                break; // End the game
            }

            // Flag to track if the move is valid or not
            boolean validMove = false;

            // Continue asking for a valid move until it's made
            while (!validMove) {
                // Ask the player to make a move and get the new position
                int[] newPosition = Cell.MovePlayer(board, (char) ('1' + currentPlayerIndex), currentRow, currentCol);

                // If the move is valid, update the player's position on the board
                if (newPosition != null) {
                    playerPositions[currentPlayerIndex][0] = newPosition[0];
                    playerPositions[currentPlayerIndex][1] = newPosition[1];
                    validMove = true;

                    // Display the updated board
                    Board.showBoard(board);
                } else {
                    // If the move is invalid, show the board and ask the same player to try again
                    Board.showBoard(board);
                    System.out.println(COLORS[currentPlayerIndex % COLORS.length] + currentPlayer.getPseudo() + RESET + "'s turn");
                }
            }

            // Switch to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;

        }
    }
}
