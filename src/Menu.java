import java.util.Scanner;

public class Menu {
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        byte choix;

        while (true) {
            System.out.println("To choose an option, tap a number ");
            System.out.println("1. Start game");
            System.out.println("2. Read the rules");
            System.out.println("3. Exit game");

            try {
                choix = Byte.parseByte(scanner.nextLine());
                switch (choix) {
                    case 1:
                        System.out.println("The game will begin");
                        String[] playersNames=Player.requestPlayerName();
                        Game.main(playersNames);
                        break;

                    case 2:
                        System.out.println("Rules:");
                        System.out.println("During his turn a player can move his pawn one space (vertically or horizontally),");
                        System.out.println("then he destroys a square on the board.");
                        System.out.println("The last player who can still move wins.");
                        System.out.println("The winner wins 5 points and the loser loses 2 points.");
                        break;

                    case 3:
                        System.out.println("See you next time");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice: choose a number between 1 and 3");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }
}

