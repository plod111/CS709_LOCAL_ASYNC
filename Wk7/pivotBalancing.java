
/**
 * CS709 - Week 7 ASYNC 
 * Exercise: Recursive balancing of a one row airplane
 * 
 * 
 * @author B.Cornish
 * @collaborator P.Chu
 * @date Oct 29, 2023
 */

import java.util.HashSet;
import java.util.Set;

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

        int[] input = { 1, 2, 3, 4 }; // { 1, 2, 1, 2, 1, 1, 2, 1, 2, 1 }; // { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };

        System.out.println("Input: " + toString(input));
        System.out.println("Initial Load Left: " + sumLeft(input, 0));
        System.out.println("Initial Load Right: " + sumRight(input, 0));

        // calculate the initial load difference
        int loadDifference = difference(sumLeft(input, 0), sumRight(input, 0));

        System.out.println("Initial Load Inbalance: " + loadDifference);

        Set<int[]> uniquePermutations = new HashSet<>(); // creates new hashset for unique permutations.

        balance(input, input.length, uniquePermutations);

        System.out.println("Optimized Load Left: " + sumLeft(??, 0));
        System.out.println("Optimized Load Right: " + sumRight(??, 0));

            // calculate load difference
        int loadDifference = difference(sumLeft(input, 0), sumRight(input, 0));
        System.out.println("Difference: " + difference(sumLeft(??, 0), sumRight(??, 0)));

    } // main end

    ////////////////////////////////////////////////////////////////////////
    // Load balancing method

    /**
     * balance - recursively balances the load of an array
     * 
     * @param input
     * @param size
     * @param uniquePermutations
     * @return int[] balanced
     */
    private static void balance(int[] input, int size, Set<int[]> uniquePermutations) {
        // base case if method is working the last letter in the char array.
        if (size == input.length/2) {
            // adds to permutations set.
            uniquePermutations.add(input);

        } else {


            for (int i = 0; i < input.length / 2; i++){



            }



            // creates a permutation recursively by swapping position of ints.
            for (int i = 0; i < size; i++) {
                balance(input, size - 1, uniquePermutations);

                if (size % 2 == 1) {
                    swap(input, 0, size - 1);
                } else {
                    swap(input, i, size - 1);
                }
            }
        }
    } // end balance

    ////////////////////////////////////////////////////////////////////////
    // Helper Methods

    /**
     * swap - swaps nth and (length-n-1)th elements in an array
     * 
     * @param input
     * @param n
     */
    private static void swap(int[] input, int n) {
        int temp = input[n];
        input[n] = input[input.length - n - 1];
        input[input.length - n - 1] = temp;

    } // end swap

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
    } // end difference

    /**
     * toString - returns a string representation of an array
     * 
     * @param input
     * @return
     */
    private static String toString(int[] input) {
        String output = "";
        for (int i = 0; i < input.length; i++) {
            output += input[i] + " ";
        }
        return output;
    }
}
