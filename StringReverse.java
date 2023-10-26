public class stringReverse {

	public static char[] stringRevRec ( char[] toRev, int ind ) {

        // base case
        

		// the work -- recursion
		// remember the temporary swap method!

	}

	public static void main ( String[] args ) {
		char[] toReverse = args[0].toCharArray();

		System.out.println( "Original string: " + args[0] );
		stringRevRec( toReverse, 0 );
		System.out.println( "Recursively reversed string: " + String.valueOf( toReverse ));
	}


}