import java.util.Arrays;
import java.util.Scanner;

public class Player {

    // Method to place a player on the board at a specific position
    public static void placePlayer(char[][] board, char playerSymbol, int row, int col) {

        // Place the player at the specified row and column
        board[row][col] = playerSymbol; // Place the player in the cell
    }



    // Function to request player names
    public static String[] requestPlayerName() {
            Scanner scanner = new Scanner(System.in);

            // Array to store the names of the 2 players
            String[] playerNames = new String[2];

            // Loop to request valid names for both players
            for (short i = 0; i < 2; i++) {
                System.out.println("Enter the name of player " + (i + 1) + ":");
                String userName = scanner.nextLine();

                // While the entered name is invalid, keep asking
                // Valid name: 2-10 characters / unique name
                while (userName.length() < 2 || userName.length() > 10 || (userName.equals(playerNames[0]))) {
                    if (userName.length() < 2 || userName.length() > 10) {
                        System.out.println("The name must be between 2 and 10 characters. Please try again:");
                    } else {
                        System.out.println("The name is already taken. Please try again:");
                    }
                    userName = scanner.nextLine();
                }

                // Add the name to the playerNames array
                playerNames[i] = userName;
                System.out.println("Player " + (i + 1) + " is now: " + playerNames[i]);
            }

            // Return the array of player names
            System.out.println("--------------------------------------");
            System.out.println("Player List : ");
            System.out.println(Arrays.toString(playerNames));
            System.out.println("--------------------------------------");
            return playerNames;
    }



}


