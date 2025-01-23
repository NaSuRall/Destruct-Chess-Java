import java.util.Random;

public class AcceleratedGame {

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String RED = "\u001B[31m";

    public static void main(Player... players) {
        char[][] board = Board.CreateBoard(10, 11); // Create the game board with 10 rows and 11 columns
        int[][] playerPositions = new int[players.length][2]; // Array to hold player positions

        if (players.length > 0) {
            Player.placePlayer(board, '1', 4, 4); // Place player 1 at (4, 4)
            playerPositions[0] = new int[]{4, 4};
        }
        if (players.length > 1) {
            Player.placePlayer(board, '2', 5, 4); // Place player 2 at (5, 4)
            playerPositions[1] = new int[]{5, 4};
        }
        if (players.length > 2) {
            Player.placePlayer(board, '3', 4, 5); // Place player 3 at (4, 5)
            playerPositions[2] = new int[]{4, 5};
        }
        if (players.length > 3) {
            Player.placePlayer(board, '4', 5, 5); // Place player 4 at (5, 5)
            playerPositions[3] = new int[]{5, 5};
        }

        Board.showBoard(board); // Show the initial game board
        Random random = new Random();
        int currentPlayerIndex = random.nextInt(players.length); // Randomly select the first player
        boolean[] isEliminated = new boolean[players.length]; // Track eliminated players
        int turnCount = 0; // Track the number of turns

        while (true) {
            int remainingPlayers = 0;
            for (boolean eliminated : isEliminated) {
                if (!eliminated) remainingPlayers++; // Count the remaining players
            }

            if (remainingPlayers == 1) {
                // If only one player remains, declare the winner
                for (int i = 0; i < players.length; i++) {
                    if (!isEliminated[i]) {
                        System.out.println(players[i].getPseudo() + RESET + " is the winner!");
                        Menu.menu(); // Go back to the main menu
                    }
                }
            }

            if (isEliminated[currentPlayerIndex]) {
                // Skip the turn if the current player is eliminated
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
                continue;
            }

            // Randomly destroy a row or column every 4 turns
            if (turnCount > 0 && turnCount % 4 == 0) {
                boolean destroyed = false;
                while (!destroyed) {
                    boolean destroyRow = random.nextBoolean(); // True: row, False: column
                    int index = destroyRow ? random.nextInt(board.length) : random.nextInt(board[0].length);

                    // Check if the row or column contains a player
                    boolean containsPlayer = false;
                    for (int[] position : playerPositions) {
                        if (destroyRow && position[0] == index) {
                            containsPlayer = true;
                            break;
                        }
                        if (!destroyRow && position[1] == index) {
                            containsPlayer = true;
                            break;
                        }
                    }

                    // If no player is in the row/column, destroy it
                    if (!containsPlayer) {
                        if (destroyRow) {
                            // Destroy a row
                            for (int col = 0; col < board[0].length; col++) {
                                board[index][col] = 'D';
                            }
                            System.out.println(RED + "Row " + index + " has been destroyed!" + RESET);
                        } else {
                            // Destroy a column
                            for (int row = 0; row < board.length; row++) {
                                board[row][index] = 'D';
                            }
                            System.out.println(RED + "Column " + index + " has been destroyed!" + RESET);
                        }
                        destroyed = true;
                        Board.showBoard(board); // Show the updated board after destruction
                    }
                }
            }

            Player currentPlayer = players[currentPlayerIndex];
            String currentColor = switch (currentPlayerIndex) {
                case 0 -> GREEN;
                case 1 -> BLUE;
                case 2 -> YELLOW;
                case 3 -> PURPLE;
                default -> RESET;
            };

            // Print current player's turn message
            System.out.println(currentColor + currentPlayer.getPseudo() + RESET + "'s turn");
            int currentRow = playerPositions[currentPlayerIndex][0];
            int currentCol = playerPositions[currentPlayerIndex][1];

            if (Cell.checkIfPlayerLost(board, currentRow, currentCol)) {
                // Check if the current player is blocked and lost
                System.out.println(currentColor + currentPlayer.getPseudo() + RESET + " is blocked and has lost!");
                isEliminated[currentPlayerIndex] = true; // Eliminate the player
                continue;
            }

            boolean validMove = false;
            while (!validMove) {
                // Attempt to move the current player
                int[] newPosition = Cell.MovePlayer(board, (char) ('1' + currentPlayerIndex), currentRow, currentCol);

                if (newPosition != null) {
                    currentRow = newPosition[0];
                    currentCol = newPosition[1];
                    validMove = true;
                    playerPositions[currentPlayerIndex] = newPosition; // Update player position
                    Board.showBoard(board); // Show the updated board
                } else {
                    Board.showBoard(board); // Show the board again if the move was invalid
                    System.out.println(currentColor + currentPlayer.getPseudo() + RESET + "'s turn");
                }
            }

            // Increment the turn counter and move to the next player
            turnCount++;
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        }
    }
}
