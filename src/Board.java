public class Board {

    // Create the rows and columns for the board
    public static char[][] CreateBoard(int rows, int cols) {
        char[][] board = new char[rows][cols]; // Initialize the board with the specified number of rows and columns
        // Loop through each row and column
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' '; // Initialize each cell with a blank space
            }
        }
        return board; // Return the created board
    }

    // Display the rows and columns of the board
    public static void showBoard(char[][] board) {
        System.out.println(" "); // Print an empty line before showing the board
        // Loop through each row in the board
        for (int i = 0; i < board.length; i++) {
            // Loop through each column in the current row
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("[" + board[i][j] + "]"); // Display each cell in a bracketed format: [ ]
            }
            System.out.println(); // Move to the next line after each row is displayed
        }
    }
}
