public class Player {

    // Place a player on the board at a specific position
    public static void placePlayer(char[][] board, char playerSymbol, int row, int col) {

        board[row][col] = playerSymbol; // Place le joueur dans la case
    }
}
