import java.util.Scanner;

public class Cell {

    // Method to move a player on the board
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

        // Calculate the new position
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

        // Validate the move
        if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
            if (board[newRow][newCol] == '.') { // The cell is available
                board[currentRow][currentCol] = '.'; // Clear the old position
                board[newRow][newCol] = player; // Place the player on the new position

                Board.showBoard(board);

                // Loop until the player chooses a valid cell to destroy
                boolean validDestruction = false;
                while (!validDestruction) {
                    // Ask the player to destroy a cell
                    System.out.println("Where would you like to destroy a case? Enter row and column: ");
                    String destroyInput = scanner.next().toUpperCase();
                    int destroyRow = -1;
                    int destroyCol = -1;

                    // Validate the row and column input
                    boolean validInput = false;
                    while (!validInput) {
                        if (destroyInput.length() >= 2 && destroyInput.length() <= 3) {
                            char rowChar = destroyInput.charAt(0);
                            String colStr = destroyInput.substring(1);  // Get column part as string (01, 02, ..., 11)

                            if (rowChar >= 'A' && rowChar < 'A' + board.length) {  // If the row is valid (A, B, C, ...)
                                destroyRow = rowChar - 'A';  // Convert the letter into a number (A -> 0, B -> 1, etc.)
                                try {
                                    destroyCol = Integer.parseInt(colStr);  // Convert the column into a number
                                } catch (NumberFormatException e) {
                                    // Handle the invalid number input but continue asking the user
                                    System.out.println("Invalid column format. Please enter a valid column number between 1 and 10.");
                                    destroyInput = scanner.next().toUpperCase();  // Prompt again for valid input
                                    continue;
                                }

                                if (destroyRow >= 0 && destroyRow < board.length && destroyCol >= 0 && destroyCol < board[0].length) {
                                    if (destroyRow == 0 && destroyCol == 0) {
                                        System.out.println("Invalid coordinate");
                                        validInput = false;
                                    } else {
                                        validInput = true;
                                    }
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
                            destroyInput = scanner.next().toUpperCase();  // Prompt again if invalid input
                        }
                    }

                    // Check if the cell is occupied by a player before destruction
                    if (board[destroyRow][destroyCol] != '.' && board[destroyRow][destroyCol] != 'D') {
                        Board.showBoard(board);
                        System.out.println("Cannot destroy a cell occupied by a player.");
                    } else {
                        // Destroy the cell
                        board[destroyRow][destroyCol] = 'D'; // Destroy the cell
                        System.out.println("Case at (" + destroyRow + ", " + destroyCol + ") has been destroyed");
                        validDestruction = true; // Exit the loop once a valid cell is destroyed
                    }
                }

                return new int[]{newRow, newCol}; // Return the new position
            } else {
                System.out.println("Case is occupied");
                return null;
            }
        } else {
            System.out.println("Invalid move");
            return null;
        }
    }

    // Method to check if the player has lost
    public static boolean checkIfPlayerLost(char[][] board, int currentRow, int currentCol) {
        // Check all adjacent cells (up, down, left, right)
        return isMoveBlocked(board, currentRow - 1, currentCol) &&  // Up
                isMoveBlocked(board, currentRow + 1, currentCol) &&  // Down
                isMoveBlocked(board, currentRow, currentCol - 1) &&  // Left
                isMoveBlocked(board, currentRow, currentCol + 1);    // Right
    }

    // Method to check if a move to a cell is blocked
    private static boolean isMoveBlocked(char[][] board, int row, int col) {
        // Check if the cell is out of bounds
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return true; // Out of bounds means blocked
        }

        // Check if the cell is occupied by 'D' or another invalid character
        return board[row][col] != '.'; // Only '*' allows movement
    }
}
