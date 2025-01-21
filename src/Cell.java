import java.util.Scanner;

public class Cell {

    public static int[] MovePlayer(char[][] board, char player, int currentRow, int currentCol) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Move (Z = up, Q = left, S = down, D = right): ");
        String input = scanner.next();
        if (input.length() != 1) {
            System.out.println("You have to enter a single character");
            return null;
        }

        char move = input.toUpperCase().charAt(0);

        if (move != 'Z' && move != 'Q' && move != 'S' && move != 'D') {
            System.out.println("You have to enter Z,Q,S or D to move");
            return null;
        }

        int newRow = currentRow;
        int newCol = currentCol;

        switch (move) {
            case 'Z':
                newRow = currentRow - 1;
                break;

            case 'Q':
                newCol = currentCol - 1;
                break;

            case 'S':
                newRow = currentRow + 1;
                break;

            case 'D':
                newCol = currentCol + 1;
                break;
            default :
                System.out.println("Invalid move");
                return null;
        }

        if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
            if (board[newRow][newCol] == ' ') {
                board[currentRow][currentCol] = ' ';
                board[newRow][newCol] = player;
                return new int[]{newRow, newCol};
            }
            else {
                System.out.println("Case is occupied");
                return null;
            }

        }
        else {
            System.out.println("Invalid move");
            return null;

        }
    }
}
