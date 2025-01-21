import java.util.Random;

public class Game {

    public static void main (String[] args) {

        // Attribute name for players

        String Player1 = "Player1";
        String Player2 = "Player2";
        String[] players = {Player1, Player2};

        // Randomise player turn at the game start
        Random random = new Random();
        String FirstPlayer = players[random.nextInt(players.length)];
        System.out.println(FirstPlayer + "'s turn");

        // Show the board
        char[][] board = new char[10][11];
        Board.showBoard(board);


    }

}
