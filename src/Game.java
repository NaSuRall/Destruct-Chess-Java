import java.util.Random;

public class Game {

    public static void main (String[] args) {

        String Player1 = "Player1";
        String Player2 = "Player2";
        String[] players = {Player1, Player2};

        Random random = new Random();
        String FirstPlayer = players[random.nextInt(players.length)];
        System.out.println(FirstPlayer + "'s turn");

        char[][] board = new char[10][11];
        Board.showBoard(board);


    }

}
