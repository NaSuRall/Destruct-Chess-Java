import java.util.Scanner;

public class Cell {

    // Method to move a player on the board
    public static int[] MovePlayer(char[][] board, char player, int currentRow, int currentCol) {
        Scanner scanner = new Scanner(System.in);

        short score = 0;


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
            if (board[newRow][newCol] == '*') { // The cell is available
                board[currentRow][currentCol] = '*'; // Clear the old position
                board[newRow][newCol] = player; // Place the player on the new position

                Board.showBoard(board);

                // Ask the player to destroy a cell
                System.out.println("Where would you like to destroy a case? Enter row and column: ");
                int destroyRow = -1;
                int destroyCol = -1;

                // Validate the row and column input
                boolean validInput = false;
                while (!validInput) {
                    if (scanner.hasNextInt()) {
                        destroyRow = scanner.nextInt();
                        if (scanner.hasNextInt()) {
                            destroyCol = scanner.nextInt();
                            // Check if the coordinates are valid
                            if (destroyRow > 0 && destroyRow < board.length && destroyCol > 0 && destroyCol < board[0].length) {
                                validInput = true; // Valid coordinates
                            } else {
                                System.out.println("Invalid coordinates, please enter row and column within the board size.");
                            }
                        } else {
                            System.out.println("Please enter a valid number for column.");
                            scanner.nextLine(); // Clear the invalid input
                        }
                    } else {
                        System.out.println("Please enter a valid number for row.");
                        scanner.nextLine(); // Clear the invalid input
                    }
                }

                // Destroy the cell
                board[destroyRow][destroyCol] = 'D'; // Destroy the cell
                System.out.println("Case at (" + destroyRow + ", " + destroyCol + ") has been destroyed");


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
        return board[row][col] != '*'; // Only '*' allows movement
    }
}