public class Board {

    // ANSI codes for colors
    public static final String RESET = "\u001B[0m";   // Reset to default color
    public static final String GREEN = "\u001B[32m";  // Green color for Player 1
    public static final String BLUE = "\u001B[34m";   // Blue color for Player 2
    public static final String RED = "\u001B[31m";    // Red color for destroyed cells ('D')

    // Create the rows and columns for the board
    public static char[][] CreateBoard(int rows, int cols) {
        char[][] board = new char[rows][cols]; // Initialize the board with the specified number of rows and columns
        // Loop through each row and column
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '.'; // Initialize each cell with a '.'
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

        // Print each row of the board with row letters
        for (int i = 0; i < board.length; i++) {
            System.out.print((char) ('A' + i) + " ");  // Print the row letter at the start of the row
            for (int j = 1; j < board[i].length; j++) {
                // Apply colors based on the content of the cell
                if (board[i][j] == '1') {
                    System.out.print(GREEN + "[" + board[i][j] + "]" + RESET); // Player 1 in green
                } else if (board[i][j] == '2') {
                    System.out.print(BLUE + "[" + board[i][j] + "]" + RESET);  // Player 2 in blue
                } else if (board[i][j] == 'D') {
                    System.out.print(RED + "[" + board[i][j] + "]" + RESET);   // Destroyed cell in red
                } else {
                    System.out.print("[" + board[i][j] + "]"); // Normal cell
                }
            }
            System.out.println();  // Move to the next line after each row
        }
    }
}
