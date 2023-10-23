import java.util.concurrent.ThreadLocalRandom;

/**
 * CS709 - Week 6 ASYNC
 * Exercise: MODIFIED Knight's Tour v2 - CLOSED
 * 
 * Warnsdorff's rule
 * (https://en.wikipedia.org/wiki/Knight%27s_tour#Warnsdorff's_rule)
 * 
 * Warnsdorff's rule is a heuristic for finding a single knight's tour.
 * The knight is moved so that it always proceeds to the square from which the
 * knight will have the fewest onward moves. When calculating the number of
 * onward moves for each candidate square, we do not count moves that revisit
 * any square already visited.
 * It is possible to have two or more choices for which the number of onward
 * moves is equal; there are various methods for breaking such ties.
 * 
 * 
 * @author B.Cornish
 * @collaborator P.Chu
 * @date Oct 21, 2023
 * 
 * 
 * 
 */

public class knightsTour_v3 {

	static final int SIZE = 6; // board size
	static final int MAX_MOVES = SIZE * SIZE; // max number of moves
	static int[][] board = new int[SIZE][SIZE]; // the board
	static boolean finished = false; // flag to stop the recursion

	static int[][] movesArray = new int[SIZE][SIZE]; // the board of valid moves
	static int MAX_POSSIBLE_MOVES = 8; // max number of moves

	// possible moves
	static int[] rowMoves = { +2, +2, -2, -2, +1, +1, -1, -1 };
	static int[] colMoves = { +1, -1, +1, -1, +2, -2, +2, -2 };

	// starting position
	static int startRow = 0;
	static int startCol = 0;

	// keep track of how many moves we've tried
	static int attemptedMoves = 0;

	// if we want a closed tour, set this to true
	static boolean closed = false;

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
		// Print out every 1,000,000 moves
		// if (attemptedMoves % 1000000 == 0) {
		System.out.println("Attempted Moves: " + attemptedMoves);
		// }

		// fell off board.
		if (!isValidMove(row, col)) {
			System.out.println("fell off board.");
			return;
		}

		// already here. ie. been here before, don't proceed
		if ((board[row][col] != 0)) {
			System.out.println("been here before.");
			return;
		}

		// if we want a closed tour, check if we're on the last move
		if (closed) {
			System.out.println("Closed Tour");
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
		}

		// mark my spot
		board[row][col] = move;
		printBoard();

		// stop the recursion -- we're done.
		if ((move == MAX_MOVES)) {
			System.out.println("Finished successfully! Move: " + move);
			System.out.println("Attempted Moves: " + attemptedMoves);
			finished = true;
			return;
		}

		// recurse using Warnsdoff's rule
		if (!finished) {
			int[] nextMove = findNextMove(row, col);
			if (nextMove[0] == 0 && nextMove[1] == 0) {
				System.out.println("No more moves.");
				return;
			}
			knightsTour(move + 1, row + nextMove[0], col + nextMove[1]);
		}

		// back track
		if (!finished) {
			board[row][col] = 0;
		}
	} // end knightsTour

	/**
	 * Finds the next move using Warnsdoff's rule.
	 * 
	 * @param row
	 * @param col
	 * @return int[] nextMove
	 */
	public static int[] findNextMove(int row, int col) {

		// System.out.println("Current Position: " + row + ", " + col);

		// initialize the least number of moves to the max possible
		int leastMoves = MAX_POSSIBLE_MOVES;

		int[] nextMove = { 0, 0 };

		// pick a random starting point for the cycle through all possible moves
		int start = ThreadLocalRandom.current().nextInt(1000) % MAX_POSSIBLE_MOVES;

		// cycle through all possible moves
		for (int count = 0; count < MAX_POSSIBLE_MOVES; ++count) {

			int i = (start + count) % MAX_POSSIBLE_MOVES;
			// System.out.println("i: " + i);

			// if the move is valid, return it
			if (isValidMove(row + rowMoves[i], col + colMoves[i])) {

				// if the move has less valid moves than the current least AND
				// it's NOT been visited, set it as the next move
				if ((movesArray[row + rowMoves[i]][col + colMoves[i]] <= leastMoves)
						&& (board[row + rowMoves[i]][col + colMoves[i]] == 0)) {
					leastMoves = movesArray[row + rowMoves[i]][col + colMoves[i]];
					nextMove[0] = rowMoves[i];
					nextMove[1] = colMoves[i];
				}
			}
		}
		// System.out.println("Next Move: " + nextMove[0] + ", " + nextMove[1] + " Least
		// Moves: " + leastMoves);
		return nextMove;
	} // end findNextMove

	/**
	 * Checks if a move is valid.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public static boolean isValidMove(int row, int col) {

		// fell off board.
		if (row < 0 || row >= board.length || col < 0 || col >= board[row].length)
			return false;
		else
			return true;
	} // end isValidMove

	///////////////////////////////////////////////////////////////////////////////
	////////// RUN PROGRAM ////////////////////////////////////////////////////////
	public static void main(String args[]) {

		// initialize the board
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				board[i][j] = 0;

		// initialize the valid moves board
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++) {
				int numValidMoves = 0;
				for (int k = 0; k < 8; k++) {
					if ((i + rowMoves[k] >= 0 && i + rowMoves[k] < board.length) &&
							(j + colMoves[k] >= 0 && j + colMoves[k] < board[i].length)) {
						numValidMoves++;
					}
				}
				movesArray[i][j] = numValidMoves;
			}

		// kick off the recursion.
		knightsTour(1, startRow, startCol);

		// print the board
		System.out.println("\nStarting Position: " + startRow + ", " + startCol);
		// System.out.println("\nSolution:");
		// printBoard();

		// print the valid moves board
		// System.out.println("\nValid Moves Board:");
		// printValidMovesBoard();

	} // end main

	/**
	 * Prints the board.
	 */
	public static void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++)
				System.out.print(board[i][j] + "\t");
			System.out.println();
		}

	} // end printBoard

	/**
	 * Prints the valid moves board.
	 */
	public static void printValidMovesBoard() {
		for (int i = 0; i < movesArray.length; i++) {
			for (int j = 0; j < movesArray[i].length; j++)
				System.out.print(movesArray[i][j] + "\t");
			System.out.println();
		}
	} // end printValidMovesBoard

}