public class task4 {

    private static final int GRID_SIZE = 9;
    /*
      Main method to demonstrate the Sudoku solver.
      Initializes an unsolved Sudoku puzzle and attempts to solve it.
     */
    public static void main(String[] args) {
        // Example unsolved Sudoku puzzle (0 represents empty cells)
        int[][] board = {
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

        System.out.println("Unsolved Sudoku Puzzle:");
        printBoard(board);

        // Attempt to solve the puzzle
        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku Puzzle:");
            printBoard(board);
        } else {
            System.out.println("\nNo solution exists for the given Sudoku puzzle.");
        }
    }

    //Solves the Sudoku puzzle using a backtracking algorithm.

    public static boolean solveSudoku(int[][] board) {
        // Iterate through each cell of the board
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                // Find an empty cell (represented by 0)
                if (board[row][col] == 0) {
                    // Try placing numbers from 1 to 9
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        // Check if the number can be legally placed in the current cell
                        if (isValidPlacement(board, numberToTry, row, col)) {
                            // If valid, place the number
                            board[row][col] = numberToTry;

                            // Recursively try to solve the rest of the puzzle
                            if (solveSudoku(board)) {
                                return true; // If successful, propagate true
                            } else {
                                // If placing the current number doesn't lead to a solution,
                                // backtrack: reset the cell to 0 and try the next number
                                board[row][col] = 0;
                            }
                        }
                    }
                    // If no number from 1-9 can be placed in the current empty cell,
                    // it means the previous placement was wrong, so return false to backtrack.
                    return false;
                }
            }
        }
        // If no empty cells are found, the puzzle is solved
        return true;
    }

    //Checks if a given number is already present in the specified row
    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    //Checks if a given number is already present in the specified column.
    private static boolean isNumberInColumn(int[][] board, int number, int col) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    //Checks if a given number is already present in the 3x3 subgrid
    private static boolean isNumberInBox(int[][] board, int number, int row, int col) {
        // Calculate the starting row and column of the 3x3 subgrid
        int localBoxRowStart = row - row % 3;
        int localBoxColStart = col - col % 3;

        for (int i = localBoxRowStart; i < localBoxRowStart + 3; i++) {
            for (int j = localBoxColStart; j < localBoxColStart + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    //Checks if placing a number at a specific position is valid according to Sudoku rules.
    //A placement is valid if the number is not present in its row, column, or 3x3 subgrid.
    private static boolean isValidPlacement(int[][] board, int number, int row, int col) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, col) &&
                !isNumberInBox(board, number, row, col);
    }

    //Prints the Sudoku board to the console in a formatted way.
    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("---------------------"); // Separator for 3x3 blocks
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| "); // Separator for 3x3 blocks
                }
                System.out.print(board[row][col] == 0 ? ". " : board[row][col] + " "); // Use '.' for empty cells
            }
            System.out.println();
        }
    }
}
