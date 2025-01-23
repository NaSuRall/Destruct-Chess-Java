/**
 * Class representing the game board, providing methods to create and display the board.
 * The board is initialized with specific cells for players and destroyed cells.
 * Colors are applied to represent different players and destroyed cells.
 */
public class Board {

    /**
     * ANSI code to reset text color to default.
     */
    public static final String RESET = "\u001B[0m";

    /**
     * ANSI code for green color, used to represent Player 1.
     */
    public static final String GREEN = "\u001B[32m";

    /**
     * ANSI code for blue color, used to represent Player 2.
     */
    public static final String BLUE = "\u001B[34m";

    /**
     * ANSI code for yellow color, used to represent Player 3.
     */
    public static final String YELLOW = "\u001B[33m";

    /**
     * ANSI code for purple color, used to represent Player 4.
     */
    public static final String PURPLE = "\u001B[35m";

    /**
     * ANSI code for red color, used to represent destroyed cells.
     */
    public static final String RED = "\u001B[31m";

    /**
     * Creates a game board with the specified number of rows and columns.
     * Each cell is initially set to a '.' character.
     *
     * @param rows The number of rows for the board.
     * @param cols The number of columns for the board.
     * @return A 2D array representing the board.
     */
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

    /**
     * Displays the game board on the console, with color codes applied based on the content of each cell.
     * Player cells are color-coded (green, blue, yellow, purple), and destroyed cells are colored red.
     * Column numbers are displayed at the top, and row letters are shown on the left.
     *
     * @param board The 2D array representing the game board to be displayed.
     */
    public static void showBoard(char[][] board) {
        // Display the column numbers at the top
        System.out.print("   ");  // Add padding for alignment
        for (int col = 0; col < board[0].length; col++) {
            System.out.print(col + "  ");  // Print each column number
        }
        System.out.println();  // Go to the next line

        // Print each row of the board with row letters
        for (int i = 0; i < board.length; i++) {
            System.out.print((char) ('A' + i) + " ");  // Print the row letter at the start of the row
            for (int j = 0; j < board[i].length; j++) {
                // Apply colors based on the content of the cell
                if (board[i][j] == '1') {
                    System.out.print(GREEN + "[" + board[i][j] + "]" + RESET); // Player 1 in green
                } else if (board[i][j] == '2') {
                    System.out.print(BLUE + "[" + board[i][j] + "]" + RESET);  // Player 2 in blue
                } else if (board[i][j] == '3') {
                    System.out.print(YELLOW + "[" + board[i][j] + "]" + RESET); // Player 3 in yellow
                } else if (board[i][j] == '4') {
                    System.out.print(PURPLE + "[" + board[i][j] + "]" + RESET); // Player 4 in purple
                }
                else if (board[i][j] == 'D') {
                    System.out.print(RED + "[" + board[i][j] + "]" + RESET);   // Destroyed cell in red
                }
                else {
                    System.out.print("[" + board[i][j] + "]"); // Normal cell
                }
            }
            System.out.println();  // Move to the next line after each row
        }
    }
}
