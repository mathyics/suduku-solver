public class Sudoku {
    private static final int BOARD_SIZE = 9;
    private static final int BOX_SIZE = 3;

    public static boolean isValid(int[][] board, int row, int col, int num) {
        // Check row
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check box
        int boxRow = row - row % BOX_SIZE;
        int boxCol = col - col % BOX_SIZE;
        for (int i = 0; i < BOX_SIZE; i++) {
            for (int j = 0; j < BOX_SIZE; j++) {
                if (board[boxRow + i][boxCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean solve(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    for (int num = 1; num <= BOARD_SIZE; num++) {
                        if (isValid(board, i, j, num)) {
                            board[i][j] = num;
                            if (solve(board)) {
                                return true;
                            }
                            board[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
                if ((j + 1) % BOX_SIZE == 0 && j < BOARD_SIZE - 1) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((i + 1) % BOX_SIZE == 0 && i < BOARD_SIZE - 1) {
                System.out.println("------|-------|--------");
            }
        }
    }

    public static void main(String[] args) {
        int[][] board ={
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

        if (solve(board)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists");
        }
    }
}