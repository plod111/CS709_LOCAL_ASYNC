/**
 * CS709 - Week 7 ASYNC 
 * Exercise: Recursive balancing of a one row airplane
 * 
 * 
 * @author B.Cornish
 * @collaborator P.Chu
 * @date Oct 29, 2023
 */

/**
 * pivotBalancing
 * 
 * Program that attempts to recursively load balance a very tiny one row
 * airplane, simulated by an 1D array of numbers entered on the
 * command line. Here are the rules:
 * a. implement recursively.
 * b. may only swap element n in the array with element length-n-1
 * (i.e. the element on the other side of the center).
 * c. Load is defined as the sum of the elements to the left of the center vs.
 * the sum of the elements to the right of the center.
 * d. The load on each side does not have to be exactly the same, it should,
 * however be as close as possible.
 * e. You do not need to recurse within the left or right side.
 */

public class pivotBalancing {

    public static void main(String[] args) {
        // if (args.length != 1 ) {
        // System.out.println("\nUsage: java pivotBalancing <int array>\n");
        // System.exit(0);
        // }

        int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };

        loadBalance(input);

        System.out.println("Load Left: " + sumLeft(input, 0));
        System.out.println("Load Right: " + sumRight(input, 0));
        System.out.println("Difference: " + difference(sumLeft(input, 0), sumRight(input, 0)));

    } // main end


    ////////////////////////////////////////////////////////////////////////
    // Load balancing

    /**
     * loadBalance - recursively load balances a one row airplane
     * 
     * @param input
     * @return int[] balanced
     */
    private static int[] loadBalance(int[] input) {
        return null;
    }


    ////////////////////////////////////////////////////////////////////////
    // Helper Methods

    /**
     * sumLeft - recursively adds up the left side of the array
     * 
     * @param input
     * @param index
     * @return int sum
     */
    private static int sumLeft(int[] input, int index) {
        int[] leftSide = new int[input.length / 2];

        for (int i = 0; i < input.length / 2; i++) {
            leftSide[i] = input[i];
        }

        if (index == leftSide.length) {
            return 0;
        } else {
            return leftSide[index] + sumLeft(input, index + 1);
        }
    } // end sumLeft


    /**
     * sumRight - recursively adds up the right side of the array
     * 
     * @param input
     * @param index
     * @return int sum
     */
    private static int sumRight(int[] input, int index) {
        int[] rightSide = new int[input.length - input.length / 2];

        int j = 0;
        for (int i = input.length / 2; i < input.length; i++) {
            rightSide[j] = input[i];
            j++;
        }

        // for (int i = 0; i < rightSide.length; i++) {
        //     System.out.print(rightSide[i]);
        // }
        // System.out.println();

        if (index == rightSide.length) {
            return 0;
        } else {
            return rightSide[index] + sumRight(input, index + 1);
        }
    } // end sumRight


    /**
     * difference - returns the difference between left and right sides
     * 
     * @param left
     * @param right
     * @return int diff
     */
    private static int difference(int left, int right) {
        return left - right;
    }
}
