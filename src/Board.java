public class Board {
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
