
// If the final position of such a path is a knight's move away from the initial position of the knight, 
// the path is called re-entrant or closed and corresponds to a Hamiltonian cycle on the underlying knight graph. 
// Conrad et al. (1994) shows that a knight's tour exists on an n×n board iff n>=6 and n is even. 

public class knightsTour_v2 {

	static final int SIZE = 6; // makes recursion much faster
	static final int MAX_MOVES = SIZE * SIZE;
	static int[][] board = new int[SIZE][SIZE];
	static boolean finished = false;
	static int[] rowMoves = { +2, +2, -2, -2, +1, +1, -1, -1 };
	static int[] colMoves = { +1, -1, +1, -1, +2, -2, +2, -2 };

	static int startRow = 1;
	static int startCol = 0;

	static int attemptedMoves = 0;

	public static void knightsTour(int move, int row, int col) {

		attemptedMoves++;
		// if (attemptedMoves % 1000000 == 0) {
		// System.out.println("Attempted Moves: " + attemptedMoves);
		// }

		// System.out.println("move: " + move + " row: " + row + " col: " + col);
		// fell off board.
		if (row < 0 || row >= board.length || col < 0 || col >= board[row].length)
			return;

		// already here. ie. been here before, don't proceed
		if ((board[row][col] != 0)) {
			return;
		}

		// if the last move, and it's NOT landing on one move away from start, go back
		if ((move == MAX_MOVES) &&
				!((row == startRow + rowMoves[0] && col == startCol + colMoves[0]) ||
						(row == startRow + rowMoves[1] && col == startCol + colMoves[1]) ||
						(row == startRow + rowMoves[2] && col == startCol + colMoves[2]) ||
						(row == startRow + rowMoves[3] && col == startCol + colMoves[3]) ||
						(row == startRow + rowMoves[4] && col == startCol + colMoves[4]) ||
						(row == startRow + rowMoves[5] && col == startCol + colMoves[5]) ||
						(row == startRow + rowMoves[6] && col == startCol + colMoves[6]) ||
						(row == startRow + rowMoves[7] && col == startCol + colMoves[7]))) {
			return;
		}

		// mark my spot
		board[row][col] = move;

		// stop the recursion -- we're done.
		if ((move == MAX_MOVES)) {
			System.out.println("Finished successfully! Move: " + move);
			System.out.println("Attempted Moves: " + attemptedMoves);
			finished = true;
			return;
		}

		// recurse in every direction
		for (int i = 0; i < 8; i++) {
			if (!finished) {
				knightsTour(move + 1, row - rowMoves[i], col - colMoves[i]);
			}
		}

		// back track - we're stuck in a corner
		if (!finished) {
			board[row][col] = 0;
		}
	}

	public static void main(String args[]) {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++) // why not board.length?
				board[i][j] = 0;

		// kick off the recursion.
		knightsTour(1, startRow, startCol);

		printBoard();

	}

	/**
	 * Prints the board.
	 */
	public static void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) // why not board.length?
				System.out.print(board[i][j] + "\t");
			System.out.println();
		}

	}

}