import java.util.Scanner;

public class Cell {

    // Method to move a player on the board
    public static int[] MovePlayer(char[][] board, char player, int currentRow, int currentCol) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a move (Z = up, Q = left, S = down, D = right)
        System.out.println("Move (Z = up, Q = left, S = down, D = right): ");
        String input = scanner.next();

        // If the input is more than one character, return an error message and do nothing
        if (input.length() != 1) {
            System.out.println("You have to enter a single character");
            return null;
        }

        // Convert the input character to uppercase and take the first character
        char move = input.toUpperCase().charAt(0);

        // Check if the move is valid, only Z, Q, S, or D are accepted
        if (move != 'Z' && move != 'Q' && move != 'S' && move != 'D') {
            System.out.println("You have to enter Z,Q,S or D to move");
            return null;
        }

        // Initialize the new row and column to the current position
        int newRow = currentRow;
        int newCol = currentCol;

        // Determine the new position based on the move
        switch (move) {
            case 'Z':  // Move up
                newRow = currentRow - 1;
                break;

            case 'Q':  // Move left
                newCol = currentCol - 1;
                break;

            case 'S':  // Move down
                newRow = currentRow + 1;
                break;

            case 'D':  // Move right
                newCol = currentCol + 1;
                break;
            default :
                System.out.println("Invalid move");
                return null;
        }

        // Check if the new position is within the board's boundaries
        if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
            // If the new position is empty, move the player and ask the player to destroy a case
            if (board[newRow][newCol] == ' ') {
                board[currentRow][currentCol] = ' ';  // Clear the current position
                board[newRow][newCol] = player;  // Place the player in the new position

                int destroyRow = -1, destroyCol = -1;

                // Repeatedly ask the user for valid coordinates to destroy a case
                while (true) {
                     Board.showBoard(board);
                    System.out.println("Where would you like to destroy a case? Enter row and column : ");

                    // Check if both inputs are integers
                    if (scanner.hasNextInt()) {
                        destroyRow = scanner.nextInt();

                        if (scanner.hasNextInt()) {
                            destroyCol = scanner.nextInt();

                            // Check if the destroy coordinates are within the board's boundaries
                            if (destroyRow >= 0 && destroyRow < board.length && destroyCol >= 0 && destroyCol < board[0].length) {
                                board[destroyRow][destroyCol] = 'D';  // Mark the destroyed case with 'D'
                                System.out.println("Case at (" + destroyRow + ", " + destroyCol + ") has been destroyed");
                                break;  // Exit the loop once valid destruction is done
                            } else {
                                System.out.println("Invalid coordinates. Coordinates must be within the board boundaries.");
                            }
                        } else {
                            System.out.println("Invalid input for column. Please enter an integer.");
                            scanner.next(); // Consume the invalid input
                        }
                    } else {
                        System.out.println("Invalid input for row. Please enter an integer.");
                        scanner.next(); // Consume the invalid input
                    }
                }

                return new int[]{newRow, newCol};  // Return the new position
            }
            else {
                System.out.println("Case is occupied");  // If the new position is already occupied
                return null;
            }

        }
        else {
            System.out.println("Invalid move");  // If the new position is out of bounds
            return null;
        }
    }
}
