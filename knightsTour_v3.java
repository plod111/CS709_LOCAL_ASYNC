

// #3: Implement Warnsdoff’s algorithm. Essentially instead of always
// following the array in order, you will calculate how many moves are available from any one spot
// and move first to the spot that has the lowest amount, then second lowest, then third lowest
// and so on until you have found your tour. The goal is to attempt to hit spots that might close
// soon.

public class knightsTour_v3 {

	static final int SIZE = 6; // makes recursion much faster
	static final int MAX_MOVES = SIZE * SIZE;
	static int[][] board = new int[SIZE][SIZE];
	static boolean finished = false;
	static int[] rowMoves = { +2, +2, -2, -2, +1, +1, -1, -1 };
	static int[] colMoves = { +1, -1, +1, -1, +2, -2, +2, -2 };

	static int startRow = 0;
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
		if ((move == MAX_MOVES) && (!(row == 2 && col == 1) || !(row == 1 && col == 2))) {
			// System.out.println("Not landing on 2,1 or 1,2. row: " + row + " col: " + col);
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