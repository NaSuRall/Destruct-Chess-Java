import java.util.Scanner;

/**
 * The {@code Menu} class provides the main menu interface for the game.
 * It allows users to choose between different actions such as starting the game,
 * reading the rules, viewing the scores, or exiting the game.
 * <p>
 * The menu operates in a loop, prompting the user for input and ensuring the input is valid.
 * Based on the user’s choice, it either displays a sub-menu or performs an action like starting the game.
 */
public class Menu {

    /**
     * Displays the main menu and processes user input.
     * <p>
     * This method repeatedly presents the user with a list of options and validates their input.
     * Depending on the user's choice, it either starts a new game, displays the rules, shows the scores,
     * or exits the program.
     * The input is validated to ensure the user selects a valid option (1-4).
     */
    public static void menu() {
        // Create the scanner for user input
        Scanner scanner = new Scanner(System.in);
        // Initialize the choice variable to store user's menu choice
        byte choice;

        // Main loop of the menu
        while (true) {
            System.out.println("To choose an option, type a number");
            System.out.println("1. Choose a game mode");
            System.out.println("2. Read the rules");
            System.out.println("3. Scores");
            System.out.println("4. Exit game");

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

                    case 4:
                        // Exit the game
                        System.out.println("See you next time!");
                        scanner.close();
                        return;

                    case 3:
                        // Display options for viewing scores
                        System.out.println("1. Show scores");
                        System.out.println("2. Show ascending scores");
                        System.out.println("3. Show descending scores");
                        System.out.println("4. Main Menu");
                        int choix2;
                        try {
                            // Attempt to convert the user input for scores menu
                            choix2 = Byte.parseByte(scanner.nextLine());
                            switch (choix2) {
                                case 1:
                                    // Show scores without sorting
                                    int[] scoresArray = Player.loadScoresFromFile("Scores-Games.txt");
                                    System.out.println("The top 10 scores from the file:");
                                    for (int i = 0; i < 10 && i < scoresArray.length; i++) {
                                        System.out.println(scoresArray[i]);
                                    }
                                    break;

                                case 2:
                                    // Show scores sorted in ascending order
                                    int[] scoresArray2 = Player.loadScoresFromFile("Scores-Games.txt");
                                    sort.mergeSort(scoresArray2);
                                    System.out.println("The top 10 scores from the file:");
                                    for (int i = 0; i < 10 && i < scoresArray2.length; i++) {
                                        System.out.println(scoresArray2[i]);
                                    }
                                    break;

                                case 3:
                                    // Show scores sorted in descending order
                                    int[] scoresArray3 = Player.loadScoresFromFile("Scores-Games.txt");
                                    sort.descending(scoresArray3);
                                    System.out.println("The top 10 scores from the file:");
                                    for (int i = 0; i < 10 && i < scoresArray3.length; i++) {
                                        System.out.println(scoresArray3[i]);
                                    }
                                    break;

                                case 4:
                                    // Return to the main menu
                                    Menu.menu();
                                    break;

                                default:
                                    // Inform the user of an invalid choice in the scores menu
                                    System.out.println("Invalid choice: choose a number between 1 and 3");
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            // Error if the input is not a valid number
                            System.out.println("Error: Please enter a valid number.");
                        }
                        break;

                    default:
                        // Inform the user of an invalid choice in the main menu
                        System.out.println("Invalid choice: choose a number between 1 and 4");
                        break;
                }

            } catch (NumberFormatException e) {
                // Error if the input is not a valid number
                System.out.println("Error: please enter a valid number.");
            }
        }
    }

    /**
     * Displays a sub-menu when the user chooses to start the game.
     * <p>
     * This method offers the user a choice between a normal game mode, an accelerated game mode,
     * or the option to return to the main menu. It validates the input and starts the selected game mode.
     *
     * @param scanner The {@link Scanner} object used to capture user input.
     */
    public static void displaySubMenu(Scanner scanner) {
        byte subChoice;

        while (true) {
            System.out.println("Game mode");
            System.out.println("1. Normal game");
            System.out.println("2. Accelerated game");
            System.out.println("3. Main Menu");

            try {
                // Get the sub-menu choice
                subChoice = Byte.parseByte(scanner.nextLine());

                switch (subChoice) {
                    case 1:
                        // Start a normal game
                        System.out.println("The game is about to start.");
                        // Initialize players variable
                        int numberOfPlayers = Player.requestPartyNumber();
                        Player[] players = Player.createPlayers(numberOfPlayers);
                        Game.main(players);
                        return; // Return to the main menu after the game

                    case 2:
                        // Start an accelerated game
                        System.out.println("The game is about to start.");
                        // Initialize players variable
                        int numberOfPlayers2 = Player.requestPartyNumber();
                        Player[] players2 = Player.createPlayers(numberOfPlayers2);
                        AcceleratedGame.main(players2);
                        return; // Return to the main menu after the game

                    case 3:
                        // Return to the main menu
                        Menu.menu();
                        break;

                    default:
                        // Inform the user of an invalid choice
                        System.out.println("Invalid choice: please choose a number between 1 and 2.");
                        break;
                }

            } catch (NumberFormatException e) {
                // Error if the input is not a valid number
                System.out.println("Error: please enter a valid number.");
            }
        }
    }
}
