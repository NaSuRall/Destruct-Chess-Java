import java.util.Random;

public class Game {

    // ANSI codes for colors
    public static final String RESET = "\u001B[0m";   // Reset to default color
    public static final String GREEN = "\u001B[32m";  // Green color for Player 1
    public static final String BLUE = "\u001B[34m";   // Blue color for Player 2
    public static final String RED = "\u001B[31m";    // Red color for destroyed cells ('D')

    public static void main(String[] playerNames) {
        // Create the game board with 10 rows and 11 columns
        char[][] board = Board.CreateBoard(10, 11);
           short ScorePlayer2 = 0;
           short ScorePlayer1 = 0;
        // Place Player 1 on the board at position (5, 6)
        Player.placePlayer(board, '1', 4, 5);
        // Place Player 2 on the board at position (6, 6)
        Player.placePlayer(board, '2', 5, 5);

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
                System.out.println(GREEN + playerNames[0] + RESET + "'s turn");

            } else {
                System.out.println(BLUE + playerNames[1] + RESET +"'s turn");
            }

            // Determine the current player's symbol and their current position
            char currentPlayerSymbol = isPlayer1Turn ? '1' : '2';
            int currentRow = isPlayer1Turn ? player1Row : player2Row;
            int currentCol = isPlayer1Turn ? player1Col : player2Col;

            if (Cell.checkIfPlayerLost(board, currentRow, currentCol)) {
                if (isPlayer1Turn) {
                    System.out.println(GREEN + playerNames[0] + RESET + " is blocked and has lost!");
                    System.out.println(BLUE + playerNames[1] + RESET + " wins the game!");
                    System.out.println(" ");
                    ScorePlayer2 += 10;
                    ScorePlayer1 -= 0;
                    System.out.println("Score : " + playerNames[1] + ScorePlayer2);
                    System.out.println("Score : " + playerNames[0] + ": " + ScorePlayer1);
                } else {
                    System.out.println(BLUE + playerNames[1] + RESET + " is blocked and has lost!");
                    System.out.println(GREEN + playerNames[0] + RESET + " wins the game!");
                    System.out.println(" ");
                    ScorePlayer1 += 5;
                    ScorePlayer2 -= 2;
                    System.out.println("Score : "+ playerNames[0] + ScorePlayer1);
                    System.out.println("Score : " + playerNames[1] + ": " + ScorePlayer2);
                }
                break;
            }

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
                        System.out.println(GREEN + playerNames[0] + RESET + "'s turn");

                    } else {
                        System.out.println(BLUE + playerNames[1] + RESET +"'s turn");
                    }
                }
            }

            // Switch turns between the players after a valid move
            isPlayer1Turn = !isPlayer1Turn;
        }
    }

}
