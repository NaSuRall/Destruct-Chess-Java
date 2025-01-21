import java.util.Scanner;

public class Player {

    // Method to place a player on the board at a specific position
    public static void placePlayer(char[][] board, char playerSymbol, int row, int col) {

        // Place the player at the specified row and column
        board[row][col] = playerSymbol; // Place the player in the cell
    }
    public static void requestPlayerName(){
        Scanner scanner = new Scanner(System.in);
        String[] playerNames = new String[2];

        for (short i = 0; i < 2; i++) {
            System.out.println("Entrez le nom du joueur " + (i + 1) + " :");
            String userName = scanner.nextLine();

            while (userName.length() < 2 || userName.length() > 10 || (userName.equals(playerNames[0]))) {
                if (userName.length() < 2 || userName.length() > 10) {
                    System.out.println("Le pseudo doit contenir entre 2 et 10 caractères. Réessayez :");
                } else {
                    System.out.println("Le pseudo est déjà pris. Réessayez :");
                }
                userName = scanner.nextLine();
            }
            playerNames[i] = userName;
            System.out.println("Joueur " + (i + 1) + " est maintenant : " + playerNames[i]);
        }
    }
}
