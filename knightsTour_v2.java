/**
 * CS709 - Week 6 ASYNC 
 * Exercise: MODIFIED Knight's Tour v2 - CLOSED
 * 
 * If the final position of such a path is a knight's move away from the initial
 * position of the knight,
 * the path is called re-entrant or closed and corresponds to a Hamiltonian
 * cycle on the underlying knight graph.
 * Conrad et al. (1994) shows that a knight's tour exists on an n×n board iff
 * n>=6 and n is even.
 * 
 * @editor B.Cornish
 * @collaborator P.Chu
 * @date Oct 21, 2023
 */

public class knightsTour_v2 {

	static final int SIZE = 6; // board size
	static final int MAX_MOVES = SIZE * SIZE; // max number of moves
	static int[][] board = new int[SIZE][SIZE]; // the board
	static boolean finished = false; // flag to stop the recursion

	// possible moves
	static int[] rowMoves = { +2, +2, -2, -2, +1, +1, -1, -1 };
	static int[] colMoves = { +1, -1, +1, -1, +2, -2, +2, -2 };

	// starting position
	static int startRow = 3;
	static int startCol = 3;

	// keep track of how many moves we've tried
	static int attemptedMoves = 0;

	/**
	 * Recursive method to find a knight's tour.
	 * 
	 * @param move
	 * @param row
	 * @param col
	 */
	public static void knightsTour(int move, int row, int col) {

		// keep track of how many moves we've tried
		attemptedMoves++;
		// Print out every 10,000,000 moves
		if (attemptedMoves % 10000000 == 0) {
		System.out.println("Attempted Moves: " + attemptedMoves);
		}

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

	///////////////////////////////////////////////////////////////////////////////
	////////// RUN PROGRAM ////////////////////////////////////////////////////////
	public static void main(String args[]) {

		// initialize the board
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				board[i][j] = 0;

		// kick off the recursion.
		knightsTour(1, startRow, startCol);

		// print the board
		printBoard();

	}

	/**
	 * Prints the board.
	 */
	public static void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++)
				System.out.print(board[i][j] + "\t");
			System.out.println();
		}

	}

}