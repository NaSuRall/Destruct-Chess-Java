import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void main (String[] args) {

        // Create the board
        char[][] board = Board.CreateBoard(10,11);

        // Place the players on the board

        //Player 1
        Player.placePlayer(board,'1',4,5);
        //Player 2
        Player.placePlayer(board,'2',5,5);

        Board.showBoard(board);

        // Randomize player's turn
        Random random = new Random();
        boolean isPlayer1Turn = random.nextBoolean(); // true is Player1's turn, else false

        // initial player position
        int player1Row = 4, player1Col = 5;
        int player2Row = 5, player2Col = 5;

        // initiate scanner
        Scanner scanner = new Scanner(System.in);


        while (true) {

            if (isPlayer1Turn) {
                System.out.println("Player1's turn");
            }
            else {
                System.out.println("Player2's turn");
            }

            // All current position
            char currentPlayerSymbol = isPlayer1Turn ? '1' : '2';
            int currentRow = isPlayer1Turn ? player1Row : player2Row;
            int currentCol = isPlayer1Turn ? player1Col : player2Col;

            // initiate validMove
            boolean validMove = false;

            while (!validMove) {
                int[] newPosition = Cell.MovePlayer(board, currentPlayerSymbol, currentRow, currentCol);

                // if Move valid, update position
                if (newPosition != null) {
                    if (isPlayer1Turn) {
                        player1Row = newPosition[0];
                        player1Col = newPosition[1];
                    } else {
                        player2Row = newPosition[0];
                        player2Col = newPosition[1];
                    }

                    validMove = true; // Move is valid, change to next player
                    Board.showBoard(board);
                } else {
                    Board.showBoard(board);
                    if (isPlayer1Turn) {
                        System.out.println("Player1's turn");
                    }
                    else {
                        System.out.println("Player2's turn");
                    }

                }
            }
            isPlayer1Turn = !isPlayer1Turn;
        }
    }

}
