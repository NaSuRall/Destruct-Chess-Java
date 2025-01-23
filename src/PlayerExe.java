import java.util.Scanner;

public class PlayerExe {

    public static String pseudo;
    public static int score;


    public static void placePlayer(char[][] board, char playerSymbol, int row, int col) {

        // Place the player at the specified row and column
        board[row][col] = playerSymbol; // Place the player in the cell
    }


    public static int requestPartyNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of players you would like to play");
        int numberOfPlayer = scanner.nextInt();
        return numberOfPlayer;

    }


    public static void CreatePlayer( int numberPlayers){
        Scanner scanner = new Scanner(System.in);
        PlayerExe[] players = new PlayerExe[numberPlayers];


        for(int i = 0; i < numberPlayers; i++){
            System.out.println("Enter Player " + (i + 1) + ": ");
            pseudo = new Scanner(System.in).nextLine();

            while (pseudo.length() < 2 || pseudo.length() > 10 || pseudo.equals(players[0])) {
                if (pseudo.length() < 2 || pseudo.length() > 10){
                    System.out.println("The name must be between 2 and 10 characters. Please try again:");
                }else {
                    System.out.println("The name is already taken. Please try again:");
                }
                pseudo =  scanner.nextLine();
            }

            players[i].pseudo = pseudo;
            System.out.println("Player " + (i + 1) + "is now" + pseudo);
        }

        System.out.println("--------------------------------------");
        System.out.println("Player List : ");
        System.out.println(players);
        System.out.println("--------------------------------------");

    };



    public static void main(String[] args) {

        int numberPlayer = PlayerExe.requestPartyNumber();
        PlayerExe.CreatePlayer(numberPlayer);

    }

}
