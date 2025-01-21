public class Player {

    // Place a player on the board at a specific position
    public static void placePlayer(char[][] board, char symbol, int row, int col) {
        board[row][col] = ' '; // Vide la case (efface son contenu)
        board[row][col] = symbol; // Place le joueur dans la case
    }
}
