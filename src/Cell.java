import java.util.Scanner;

public class Cell {

    // Method to move a player on the board
    public static int[] MovePlayer(char[][] board, char player, int currentRow, int currentCol) {
        Scanner scanner = new Scanner(System.in);

        // Demander au joueur de se déplacer
        System.out.println("Move (Z = up, Q = left, S = down, D = right): ");
        String input = scanner.next();

        // Vérification de l'entrée
        if (input.length() != 1) {
            System.out.println("You have to enter a single character");
            return null;
        }

        char move = input.toUpperCase().charAt(0);
        if (move != 'Z' && move != 'Q' && move != 'S' && move != 'D') {
            System.out.println("You have to enter Z,Q,S or D to move");
            return null;
        }

        // Calcul de la nouvelle position
        int newRow = currentRow;
        int newCol = currentCol;

        switch (move) {
            case 'Z': newRow--; break; // Haut
            case 'Q': newCol--; break; // Gauche
            case 'S': newRow++; break; // Bas
            case 'D': newCol++; break; // Droite
        }

        // Validation du déplacement
        if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
            if (board[newRow][newCol] == '*') { // La case est disponible
                board[currentRow][currentCol] = '*'; // Vide l'ancienne position
                board[newRow][newCol] = player; // Place le joueur sur la nouvelle position

                Board.showBoard(board);

                // Demander au joueur de détruire une case
                System.out.println("Where would you like to destroy a case? Enter row and column : ");
                int destroyRow = scanner.nextInt();
                int destroyCol = scanner.nextInt();

                // Valider les coordonnées de destruction
                if (destroyRow >= 0 && destroyRow < board.length && destroyCol >= 0 && destroyCol < board[0].length) {
                    board[destroyRow][destroyCol] = 'D'; // Détruit la case
                    System.out.println("Case at (" + destroyRow + ", " + destroyCol + ") has been destroyed");

                    // Vérifiez si l'adversaire est bloqué CONDITION DE DEFAITE
                    if (checkIfPlayerLost(board, currentRow, currentCol)) {
                        Board.showBoard(board);
                        System.out.println("Player at position (" + currentRow + ", " + currentCol + ") is blocked and has lost!");
                        Main.main(null);
                    }
                } else {
                    System.out.println("Invalid coordinates"); // Coordonnées de destruction invalides
                }

                return new int[]{newRow, newCol}; // Retournez la nouvelle position
            } else {
                System.out.println("Case is occupied");
                return null;
            }
        } else {
            System.out.println("Invalid move");
            return null;
        }
    }


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
