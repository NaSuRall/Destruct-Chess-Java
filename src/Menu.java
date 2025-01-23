import java.util.Scanner;

/*
    as long as no input is correct:
        we ask the user to enter a number to perform an action
            we check if the user enters a number
            if yes:
                if their choice=1:
                    we start the game
                if their choice=2
                    we display the rules
                if their choice=3
                    we stop the program
                by default:
                    we inform the user that they must enter a number between 1 and 3
            if not:
                we inform the user that they must enter a number
 */

public class Menu {
    public static void menu() {
        //we create a scanner
        Scanner scanner = new Scanner(System.in);
        // and we initialize a choice variable
        byte choix;

        //as long as a response is not valid we display the options and ask the user to enter a number
        while (true) {
            System.out.println("To choose an option, tap a number ");
            System.out.println("1. Start game");
            System.out.println("2. Read the rules");
            System.out.println("3. Exit game");
            System.out.println("4. Scores");

            //we try to convert the user input into a byte
            try {
                choix = Byte.parseByte(scanner.nextLine());
                //we check the user input and see what it corresponds to
                switch (choix) {
                    case 1:
                        System.out.println("The game will begin.");
                        int numberOfPlayers = Player.requestPartyNumber();
                        Player[] players = Player.createPlayers(numberOfPlayers);
                        Game.main(players);
                        break;

                    case 2:
                        // we simply display the rules
                        System.out.println("Rules:");
                        System.out.println("During his turn a player can move his pawn one space (vertically or horizontally),");
                        System.out.println("then he destroys a square on the board.");
                        System.out.println("The last player who can still move wins.");
                        System.out.println("The winner wins 5 points and the loser loses 2 points.");
                        break;

                    case 3:
                        System.out.println("See you next time");
                        //we close the scanner to free up memory
                        scanner.close();
                        //we return nothing to stop the function and thus stop the program
                        return;

                    case 4 :
                        int[] scoresArray = Player.loadScoresFromFile("Scores-Games.txt");

                        // Afficher le tableau avant le tri
                        System.out.println("Contenu du fichier avant le tri :");
                        for (int score : scoresArray) {
                            System.out.println(score);
                        }
                        break;
                    // default establishes a case to inform the user that their input is incorrect
                    default:
                        System.out.println("Invalid choice: choose a number between 1 and 3");
                        break;
                }

                //if we cannot convert their input, we display an error message
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }
}