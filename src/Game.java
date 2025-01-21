import java.util.Random;

public class Game {

    public static void main () {

        // Create the game board with 10 rows and 11 columns
        char[][] board = Board.CreateBoard(10,11);

        // Place Player 1 on the board at position (4, 5)
        Player.placePlayer(board,'1',4,5);
        // Place Player 2 on the board at position (5, 5)
        Player.placePlayer(board,'2',5,5);

        // Show the board after placing the players
        Board.showBoard(board);

        // Randomly determine which player will go first
        Random random = new Random();
        boolean isPlayer1Turn = random.nextBoolean(); // true means it's Player1's turn, false means Player2's turn

        // Initial positions of the players
        int player1Row = 4, player1Col = 5;
        int player2Row = 5, player2Col = 5;

        // Start a loop for the game (keep running until a win condition or defeat condition is met)
        while (true) {

            // Display whose turn it is
            if (isPlayer1Turn) {
                System.out.println("Player1's turn");
            }
            else {
                System.out.println("Player2's turn");
            }

            // Determine the current player's symbol and their current position
            char currentPlayerSymbol = isPlayer1Turn ? '1' : '2';
            int currentRow = isPlayer1Turn ? player1Row : player2Row;
            int currentCol = isPlayer1Turn ? player1Col : player2Col;

            // Flag to track if the move is valid or not
            boolean validMove = false;

            // Continue asking for a valid move until it's made
            while (!validMove) {

                // Ask the player to make a move and get the new position of the player
                int[] newPosition = Cell.MovePlayer(board, currentPlayerSymbol, currentRow, currentCol);

                // If the move is valid, update the player's position on the board
                if (newPosition != null) {
                    if (isPlayer1Turn) {
                        player1Row = newPosition[0];
                        player1Col = newPosition[1];
                    } else {
                        player2Row = newPosition[0];
                        player2Col = newPosition[1];
                    }

                    validMove = true; // Move is valid, so change to the next player's turn
                    // Display the updated board with the player's new position
                    Board.showBoard(board);
                } else {
                    // If the move is invalid, show the board and ask the same player to try again
                    Board.showBoard(board);
                    if (isPlayer1Turn) {
                        System.out.println("Player1's turn");
                    }
                    else {
                        System.out.println("Player2's turn");
                    }
                }
            }
            // Switch turns between the players after a valid move
            isPlayer1Turn = !isPlayer1Turn;
        }
    }

}
