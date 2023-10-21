public class knightsTour_v2 {

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
		// if(attemptedMoves%1000000==0){
		// 	System.out.println("Attempted Moves: " + attemptedMoves);
		// }

		// fell off board.
		if (row < 0 || row >= board.length || col < 0 || col >= board[row].length)
			return;

		// already here. ie. been here before, don't proceed
		if ((board[row][col] != 0) ) {
			return;
		}

		if ((move == MAX_MOVES-1) && ((row != 2 && col != 1) || (row != 1 && col != 2))) {
			// System.out.println("Not landing");
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
		if (!finished)
			board[row][col] = 0;
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

// public class knightsTour_v1 {
// public static void main(String[] args) {
// int[][] board = new int[8][8];
// int[] xMoves = {2, 1, -1, -2, -2, -1, 1, 2};
// int[] yMoves = {1, 2, 2, 1, -1, -2, -2, -1};
// int x = 0;
// int y = 0;
// int move = 0;
// int nextX = 0;
// int nextY = 0;
// int minMoves = 8;
// int minIndex = 0;
// int tempX = 0;
// int tempY = 0;
// int tempMoves = 0;
// boolean stuck = false;
// boolean done = false;
// board[x][y] = 1;
// while (!done) {
// stuck = true;
// minMoves = 8;
// minIndex = 0;
// for (int i = 0; i < 8; i++) {
// nextX = x + xMoves[i];
// nextY = y + yMoves[i];
// if (nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8 && board[nextX][nextY]
// == 0) {
// stuck = false;
// tempMoves = 0;
// for (int j = 0; j < 8; j++) {
// tempX = nextX + xMoves[j];
// tempY = nextY + yMoves[j];
// if (tempX >= 0 && tempX < 8 && tempY >= 0 && tempY < 8 && board[tempX][tempY]
// == 0) {
// tempMoves++;
// }
// }
// if (tempMoves < minMoves) {
// minMoves = tempMoves;
// minIndex = i;
// }
// }
// }
// if (stuck) {
// done = true;
// } else {
// move++;
// x += xMoves[minIndex];
// y += yMoves[minIndex];
// board[x][y] = move + 1;
// }
// }
// for (int i = 0; i < 8; i++) {
// System.out.println();
// for (int j = 0; j < 8; j++) {
// }