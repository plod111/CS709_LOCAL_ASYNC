public class knightsTour_v1 {

	static final int SIZE = 6; // makes recursion much faster
	static final int MAX_MOVES = SIZE * SIZE;
	static int[][] board = new int[SIZE][SIZE];
	static boolean finished = false;
	static int[] rowMoves = { +2, +2, -2, -2, +1, +1, -1, -1 };
	static int[] colMoves = { +1, -1, +1, -1, +2, -2, +2, -2 };

	public static void knightsTour ( int move, int row, int col ) {
		// fell off board.
		if ( row < 0 || row >= board.length || col < 0 || col >= board[row].length ) return;

		// already here.
		if ( board[row][col] != 0 ) return;

		// mark my spot
		board[row][col] = move;

		// stop the recursion -- we're done.
		if ( move == MAX_MOVES ) {
			System.out.println( "Finished successfully!" );
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
		if (! finished) board[row][col] = 0;
	}

	public static void main(String args[]) {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++) // why not board.length?
				board[i][j] = 0;

		// kick off the recursion.
		knightsTour(1, 0, 0);

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) // why not board.length?
				System.out.print(board[i][j] + "\t");
			System.out.println();
		}
	}

}