import java.util.Scanner;

/**
 * Class representing the game logic for moving players and checking if players are blocked.
 * This class includes methods for moving a player on the board, checking if a player has lost, and
 * managing the destruction of cells.
 */
public class Cell {

    /**
     * Moves the player on the board based on input (Z, Q, S, D for up, left, down, right).
     * After the move, it allows the player to destroy a cell on the board and validates the move and destruction.
     *
     * @param board The game board represented as a 2D array of characters.
     * @param player The character representing the player on the board.
     * @param currentRow The current row position of the player.
     * @param currentCol The current column position of the player.
     * @return An array containing the new row and column of the player after the move. Returns null if the move is invalid.
     */
    public static int[] MovePlayer(char[][] board, char player, int currentRow, int currentCol) {
        Scanner scanner = new Scanner(System.in);

        // Ask the player to move
        System.out.println("Move (Z = up, Q = left, S = down, D = right): ");
        String input = scanner.next();

        // Validate the input
        if (input.length() != 1) {
            System.out.println("You have to enter a single character");
            return null;
        }

        char move = input.toUpperCase().charAt(0);
        if (move != 'Z' && move != 'Q' && move != 'S' && move != 'D') {
            System.out.println("You have to enter Z,Q,S or D to move");
            return null;
        }

        // Calculate the new position based on the player's move
        int newRow = currentRow;
        int newCol = currentCol;

        switch (move) {
            case 'Z':
                newRow--;
                break; // Up
            case 'Q':
                newCol--;
                break; // Left
            case 'S':
                newRow++;
                break; // Down
            case 'D':
                newCol++;
                break; // Right
        }

        // Validate the move to ensure it stays within the board boundaries
        if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
            if (board[newRow][newCol] == '.') { // The cell is available for movement
                board[currentRow][currentCol] = '.'; // Clear the old position
                board[newRow][newCol] = player; // Place the player on the new position

                Board.showBoard(board); // Display the updated board

                // Loop until the player chooses a valid cell to destroy
                boolean validDestruction = false;
                while (!validDestruction) {
                    // Ask the player to destroy a cell on the board
                    System.out.println("Where would you like to destroy a case? Enter row and column: ");
                    String destroyInput = scanner.next().toUpperCase();
                    int destroyRow = -1;
                    int destroyCol = -1;

                    // Validate the row and column input
                    boolean validInput = false;
                    while (!validInput) {
                        if (destroyInput.length() >= 2 && destroyInput.length() <= 3) {
                            char rowChar = destroyInput.charAt(0);
                            String colStr = destroyInput.substring(1);  // Get the column part as a string (01, 02, ..., 11)

                            if (rowChar >= 'A' && rowChar < 'A' + board.length) {  // If the row is valid (A, B, C, ...)
                                destroyRow = rowChar - 'A';  // Convert the row letter into a number (A -> 0, B -> 1, etc.)
                                try {
                                    destroyCol = Integer.parseInt(colStr);  // Convert the column into a number
                                } catch (NumberFormatException e) {
                                    // Handle invalid column input but continue asking for valid input
                                    System.out.println("Invalid column format. Please enter a valid column number between 1 and 10.");
                                    destroyInput = scanner.next().toUpperCase();  // Prompt again for valid input
                                    continue;
                                }

                                if (destroyRow >= 0 && destroyRow < board.length && destroyCol >= 0 && destroyCol < board[0].length) {
                                    validInput = true;
                                } else {
                                    System.out.println("Invalid coordinates, please enter a valid row (A-J) and column (1-10).");
                                }
                            } else {
                                System.out.println("Invalid input, please enter a valid row (A-J) and column (1-10).");
                            }
                        } else {
                            System.out.println("Invalid input, please enter the coordinates in the correct format (e.g., A1, B3).");
                        }

                        if (!validInput) {
                            destroyInput = scanner.next().toUpperCase();  // Prompt again if input is invalid
                        }
                    }

                    // **Check**: if the cell is already destroyed (D), prevent further destruction
                    if (board[destroyRow][destroyCol] == 'D') {
                        System.out.println("Cannot destroy a cell that is already destroyed.");
                    } else if (board[destroyRow][destroyCol] != '.' && board[destroyRow][destroyCol] != 'D') {
                        System.out.println("Cannot destroy a cell occupied by a player.");
                    } else {
                        // Destroy the selected cell
                        board[destroyRow][destroyCol] = 'D'; // Mark the cell as destroyed
                        System.out.println("Case at (" + destroyRow + ", " + destroyCol + ") has been destroyed");
                        validDestruction = true; // Exit the loop once a valid cell is destroyed
                    }
                }

                return new int[]{newRow, newCol}; // Return the new position after the move
            } else {
                System.out.println("Case is occupied");
                return null; // If the destination cell is occupied, return null
            }
        } else {
            System.out.println("Invalid move");
            return null; // If the move is out of bounds, return null
        }
    }

    /**
     * Checks if the player has lost by evaluating if all adjacent cells are blocked.
     * A player is considered lost if there are no valid moves available in any direction.
     *
     * @param board The game board represented as a 2D array of characters.
     * @param currentRow The current row position of the player.
     * @param currentCol The current column position of the player.
     * @return True if the player has lost (all adjacent cells are blocked), false otherwise.
     */
    public static boolean checkIfPlayerLost(char[][] board, int currentRow, int currentCol) {
        // Check all adjacent cells (up, down, left, right) to see if the player is blocked
        return isMoveBlocked(board, currentRow - 1, currentCol) &&  // Up
                isMoveBlocked(board, currentRow + 1, currentCol) &&  // Down
                isMoveBlocked(board, currentRow, currentCol - 1) &&  // Left
                isMoveBlocked(board, currentRow, currentCol + 1);    // Right
    }

    /**
     * Checks if a move to a specific cell is blocked.
     * A move is blocked if the target cell is out of bounds or contains something other than an empty space ('.').
     *
     * @param board The game board represented as a 2D array of characters.
     * @param row The row position of the cell to check.
     * @param col The column position of the cell to check.
     * @return True if the move is blocked, false otherwise.
     */
    private static boolean isMoveBlocked(char[][] board, int row, int col) {
        // Check if the cell is out of bounds
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return true; // Out of bounds means the move is blocked
        }

        // Check if the cell is occupied by 'D' or any other invalid character
        return board[row][col] != '.'; // A cell occupied by anything other than '.' is considered blocked
    }
}
