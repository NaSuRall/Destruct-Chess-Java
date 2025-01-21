import java.util.Random;

public class Game {

    public static void main (String[] args) {

        // Attribute name for players and symbols

        String Player1 = "Player1";
        String Player2 = "Player2";
        char player1Symbol = '1';
        char player2Symbol = '2';
        String[] players = {Player1, Player2};

        // Create the board
        char[][] board = new char[10][11];

        // Place the players
        Player.placePlayer(board, player1Symbol, 4, 5);
        Player.placePlayer(board, player2Symbol, 5, 5);

        // Randomise player turn at the game start
        Random random = new Random();
        String FirstPlayer = players[random.nextInt(players.length)];
        System.out.println(FirstPlayer + "'s turn");

        // Show the board
        Board.showBoard(board);


    }

}
