import java.util.Scanner;

public class Menu {
    public static void menu() {
        // Create the scanner
        Scanner scanner = new Scanner(System.in);
        // Initialize the choice variable
        byte choice;

        // Main loop of the menu
        while (true) {
            System.out.println("To choose an option, type a number");
            System.out.println("1. Choose a game mode");
            System.out.println("2. Read the rules");
            System.out.println("3. Exit the game");

            try {
                // Attempt to convert user input into a byte
                choice = Byte.parseByte(scanner.nextLine());

                switch (choice) {
                    case 1:
                        // Display the sub-menu to start the game
                        displaySubMenu(scanner);
                        break;

                    case 2:
                        // Display the rules of the game
                        System.out.println("Rules:");
                        System.out.println("During their turn, a player can move their piece by one cell (vertically or horizontally),");
                        System.out.println("then they destroy a cell on the board.");
                        System.out.println("The last player who can still move wins.");
                        System.out.println("The winner gains 5 points, and the loser loses 2 points.");
                        break;

                    case 3:
                        // Exit the game
                        System.out.println("See you next time!");
                        scanner.close();
                        return;

                    default:
                        // Message for invalid choice
                        System.out.println("Invalid choice: please choose a number between 1 and 3.");
                        break;
                }

            } catch (NumberFormatException e) {
                // Error if the input is not a valid number
                System.out.println("Error: please enter a valid number.");
            }
        }
    }

    // Method to display the sub-menu when option 1 is chosen
    public static void displaySubMenu(Scanner scanner) {
        byte subChoice;

        while (true) {
            System.out.println("Game mode");
            System.out.println("1. Normal game");
            System.out.println("2. Accelerated game");

            try {
                subChoice = Byte.parseByte(scanner.nextLine());

                // INITIATE PLAYERS VARIABLE
                int numberOfPlayers = Player.requestPartyNumber();
                Player[] players = Player.createPlayers(numberOfPlayers);

                switch (subChoice) {
                    case 1:
                        // Start a new game
                        System.out.println("The game is about to start.");
                        Game.main(players);
                        return; // Return to the main menu after the game
                    case 2:
                        // Special mode
                        System.out.println("The game is about to start.");
                        AcceleratedGame.main(players);
                        return; // Return to the main menu

                    default:
                        // Message for invalid choice
                        System.out.println("Invalid choice: please choose a number between 1 and 3.");
                        break;
                }

            } catch (NumberFormatException e) {
                // Error if the input is not a valid number
                System.out.println("Error: please enter a valid number.");
            }
        }
    }
}
