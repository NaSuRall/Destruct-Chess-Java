import java.util.Scanner;
public class Player{

    private String pseudo;
    private int score;

    // Constructeur pour initialiser un joueur
    public Player(String pseudo) {
        this.pseudo = pseudo;
        this.score = 0;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getScore() {
        return score;
    }


    public static void placePlayer(char[][] board, char playerSymbol, int row, int col) {

        // Place the player at the specified row and column
        board[row][col] = playerSymbol; // Place the player in the cell
    }


    public static int requestPartyNumber() {
        Scanner scanner = new Scanner(System.in);
        byte numberOfPlayers;

        System.out.println("Please enter the number of players you would like to play (2 - 4):");

        while (true) {
            try {
                numberOfPlayers = Byte.parseByte(scanner.nextLine());
                if (numberOfPlayers >= 2 && numberOfPlayers <= 4) {
                    return numberOfPlayers;
                } else {
                    System.out.println("Please enter a valid number between 2 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static Player[] createPlayers(int numberOfPlayers) {
        Scanner scanner = new Scanner(System.in);
        Player[] players = new Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            String pseudo;
            boolean validPseudo;

            do {
                validPseudo = true;
                System.out.println("Enter Player " + (i + 1) + " name (2-10 characters):");
                pseudo = scanner.nextLine();

                if (pseudo.length() < 2 || pseudo.length() > 10) {
                    System.out.println("The name must be between 2 and 10 characters. Please try again.");
                    validPseudo = false;
                }

            } while (!validPseudo);

            players[i] = new Player(pseudo);
            System.out.println("Player " + (i + 1) + " is now " + pseudo + ".");
        }

        return players;
    }


}
