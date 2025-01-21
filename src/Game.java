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
        showBoard(board);


    }


    public static char[][] CreateBoard (int rows, int cols) {
        char[][] board = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }

    public static void showBoard(char[][] board) {
        System.out.println(" ");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
                if(j < board[i].length - 1){
                    System.out.print(" ");
                }
            }
        System.out.println();
        }
    }
}
