public class Player {

    // Method to place a player on the board at a specific position
    public static void placePlayer(char[][] board, char playerSymbol, int row, int col) {

        // Place the player at the specified row and column
        board[row][col] = playerSymbol; // Place the player in the cell
    }
}
