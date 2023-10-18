public class knightsTour_v1 {

	static final int SIZE = 6; // makes recursion much faster
	static final int MAX_MOVES = SIZE*SIZE;
	static int[][] board = new int[SIZE][SIZE];
	static boolean finished = false;

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
		if (! finished) knightsTour( move + 1, row - 1, col - 2 );
		if (! finished) knightsTour( move + 1, row - 1, col + 2 );
		if (! finished) knightsTour( move + 1, row + 1, col - 2 );
		if (! finished) knightsTour( move + 1, row + 1, col + 2 );

		if (! finished) knightsTour( move + 1, row + 2, col - 1 );
		if (! finished) knightsTour( move + 1, row + 2, col + 1 );
		if (! finished) knightsTour( move + 1, row - 2, col - 1 );
		if (! finished) knightsTour( move + 1, row - 2, col + 1 );

		// back track - we're stuck in a corner
		if (! finished) board[row][col] = 0;
	}

	public static void main ( String args[] ) {
		for (int i = 0 ; i < board.length ; i++ )
			for (int j = 0 ; j < board[i].length ; j++ ) // why not board.length?
				board[i][j] = 0;

		// kick off the recursion.
		knightsTour(1, 0, 0);

		for (int i = 0 ; i < board.length ; i++ ) {
			for (int j = 0 ; j < board[i].length ; j++ ) // why not board.length?
				System.out.print( board[i][j] + "\t");
			System.out.println();
		}
	}

}















































// public class knightsTour_v1 {
//     public static void main(String[] args) {
//         int[][] board = new int[8][8];
//         int[] xMoves = {2, 1, -1, -2, -2, -1, 1, 2};
//         int[] yMoves = {1, 2, 2, 1, -1, -2, -2, -1};
//         int x = 0;
//         int y = 0;
//         int move = 0;
//         int nextX = 0;
//         int nextY = 0;
//         int minMoves = 8;
//         int minIndex = 0;
//         int tempX = 0;
//         int tempY = 0;
//         int tempMoves = 0;
//         boolean stuck = false;
//         boolean done = false;
//         board[x][y] = 1;
//         while (!done) {
//             stuck = true;
//             minMoves = 8;
//             minIndex = 0;
//             for (int i = 0; i < 8; i++) {
//                 nextX = x + xMoves[i];
//                 nextY = y + yMoves[i];
//                 if (nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8 && board[nextX][nextY] == 0) {
//                     stuck = false;
//                     tempMoves = 0;
//                     for (int j = 0; j < 8; j++) {
//                         tempX = nextX + xMoves[j];
//                         tempY = nextY + yMoves[j];
//                         if (tempX >= 0 && tempX < 8 && tempY >= 0 && tempY < 8 && board[tempX][tempY] == 0) {
//                             tempMoves++;
//                         }
//                     }
//                     if (tempMoves < minMoves) {
//                         minMoves = tempMoves;
//                         minIndex = i;
//                     }
//                 }
//             }
//             if (stuck) {
//                 done = true;
//             } else {
//                 move++;
//                 x += xMoves[minIndex];
//                 y += yMoves[minIndex];
//                 board[x][y] = move + 1;
//             }
//         }
//         for (int i = 0; i < 8; i++) {
//             System.out.println();
//             for (int j = 0; j < 8; j++) {
// }