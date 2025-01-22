public class Board {

    // Create the rows and columns for the board
    public static char[][] CreateBoard(int rows, int cols) {
        char[][] board = new char[rows][cols]; // Initialize the board with the specified number of rows and columns
        // Loop through each row and column
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '*'; // Initialize each cell with a *
            }
        }
        return board; // Return the created board
    }
    // Display the rows and columns of the board
    public static void showBoard(char[][] board) {
        // Display the column numbers at the top
        System.out.print("   ");  // Add padding for alignment
        for (int col = 1; col < board[0].length; col++) {
            System.out.print(col + "  ");  // Print each column number
        }
        System.out.println();  // Go to the next line

        // Print a separator
        System.out.println("  -----------------------------");

        // Display each row of the board with row numbers at the beginning
        for (int i = 1; i < board.length; i++) {
            System.out.print(i + " ");  // Print the row number at the beginning of the row
            for (int j = 1; j < board[i].length; j++) {
                System.out.print("[" + board[i][j] + "]");  // Print the cell contents
            }
            System.out.println();  // Move to the next line after printing the row
        }
    }
}

