
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
        if (args.length != 1) {
            System.out.println("\nUsage: java pivotBalancing <int array>\n");
            System.exit(0);
        }

        // convert args array into int array
        int[] inputOriginal = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            inputOriginal[i] = Integer.parseInt(args[i]);
        }

        //int[] inputOriginal = {12,34,37,54,23,67}; //{ 1, 2, 3, 4, 5 };

        int[] input = new int[inputOriginal.length];
        for (int i = 0; i < inputOriginal.length; i++) {
            input[i] = inputOriginal[i];
        }

        System.out.println("Input: " + toString(input));
        System.out.println("Initial Load Left: " + sumLeft(input, 0));
        System.out.println("Initial Load Right: " + sumRight(input, 0));

        // calculate the initial load difference
        int loadDifference = Math.abs(difference(sumLeft(input, 0), sumRight(input, 0)));

        System.out.println("Initial Load Inbalance: " + loadDifference);

        Set<int[]> uniquePermutations = new HashSet<>(); // creates new hashset for unique permutations.

        System.out.println("Beginning permutations...");
        permutations(input, input.length, uniquePermutations);

        Set<int[]> validPermutations = rainbowValidation(inputOriginal, uniquePermutations);

        // printPermutations(validPermutations);

        // find optimal balance
        findBalance(inputOriginal, validPermutations, loadDifference);

        // System.out.println();

    } // main end

    ////////////////////////////////////////////////////////////////////////
    // Load balancing methods

    /**
     * findBalance - finds the optimal balance
     * 
     * @param input
     * @param validPermutations
     * @param loadDifference
     */
    private static void findBalance(int[] input, Set<int[]> validPermutations, int loadDifference) {

        // Set to array for iterating
        int[][] optimalPermutation = new int[validPermutations.size()][input.length];
        validPermutations.toArray(optimalPermutation);

        // initialize optimal load difference
        int optimalLoadDifference = loadDifference;
        int tempLoadDifference = 0;

        // optimal load array
        int[] optimalLoadArray = new int[input.length];

        for (int[] array : optimalPermutation) {

            tempLoadDifference = Math.abs(difference(sumLeft(array, 0), sumRight(array, 0)));
            // System.out.println("tempLoadDifference: " + tempLoadDifference);
            if (tempLoadDifference < optimalLoadDifference) {
                optimalLoadDifference = tempLoadDifference;

                for (int i = 0; i < array.length; i++) {
                    optimalLoadArray[i] = array[i];
                }
            }
        }

        System.out.println("Optimized Load Left: " + sumLeft(optimalLoadArray, 0));
        System.out.println("Optimized Load Right: " + sumRight(optimalLoadArray, 0));

        System.out.println("Optimal Load Difference: " + optimalLoadDifference);
        System.out.println("Optimal Load Array: " + toString(optimalLoadArray));

    }

    /**
     * rainbowValidation - validates permutations using rainbow swap
     * 
     * @param input
     * @param uniquePermutations
     * @return Set<int[]> rainbowPermutations
     */
    private static Set<int[]> rainbowValidation(int[] input, Set<int[]> uniquePermutations) {
        int[][] permutationsArray = new int[uniquePermutations.size()][input.length];
        uniquePermutations.toArray(permutationsArray);

        // iterate thru permutationsArray and validate rainbow swaps and store
        // valid permutations in a new set.

        Set<int[]> rainbowPermutations = new HashSet<>();
        for (int[] permutation : permutationsArray) {
            if (isValidPermutation(input, permutation, 0)) {
                // System.out.println("Valid permutation: " + toString(permutation));
                rainbowPermutations.add(permutation);
            }
        }

        return rainbowPermutations;
    }

    /**
     * isValidPermutation - checks if a permutation is valid
     * 
     * @param input
     * @param permutation
     * @param i
     * @return boolean isValid
     */
    private static boolean isValidPermutation(int[] input, int[] permutation, int i) {

        // System.out.println("i: " + i);
        boolean isValid = false;

        if (i == input.length / 2) {
            // System.out.println("end");
            return true;
        }

        // go thru input and permutation arrays and check if rainbow swap
        // is valid.

        // printArray(permutation);

        // if ODD array length, validate only those elements that 'middle element does
        // not move
        if (input.length % 2 == 1) {
            if (permutation[permutation.length / 2] == input[permutation.length / 2]) {
                isValid = true;
            } else {
                return false;
            }
        }

        if (((permutation[i] == input[i]) && (permutation[input.length - i - 1] == input[input.length - i - 1]))
                || ((permutation[i] == input[input.length - i - 1])
                        && (permutation[input.length - i - 1] == input[i]))) {
            // System.out.println("p[i]: " + permutation[i]);
            // System.out.println("i[i]: " + input[i]);
            // System.out.println("i[l-i-1]: " + input[input.length - i - 1]);
            isValid = true;
            // System.out.println("***isValid: " + isValid);

        } else {
            return false;
            // return isValid;
        }

        if (isValid) {
            // System.out.println("recursive call");
            isValidPermutation(input, permutation, i + 1);
        }

        // System.out.println(isValid);
        return isValid;
    }

    /**
     * permutations - recursively finds all permutations of an array
     * 
     * @param input
     * @param size
     * @param uniquePermutations
     */
    private static void permutations(int[] input, int size, Set<int[]> uniquePermutations) {

        // base case if method is working the last letter in the char array.
        if (size == 1) {
            // create new array object
            int[] permutation = new int[input.length];
            for (int i = 0; i < input.length; i++) {
                permutation[i] = input[i];
            }

            // adds the new array to permuteString set.
            uniquePermutations.add(permutation);

        } else {
            // creates a permutation recursively by swapping position of chars.
            for (int i = 0; i < size; i++) {
                permutations(input, size - 1, uniquePermutations);

                if (size % 2 == 1) {
                    swap(input, 0, size - 1);
                } else {
                    swap(input, i, size - 1);
                }
            }
        }
    } // end permutations

    ////////////////////////////////////////////////////////////////////////
    // Helper Methods

    // helper method to swap chars for the input array
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * rainbowSwap - swaps nth and (length-n-1)th elements in an array
     * 
     * @param input
     * @param n
     */
    private static void rainbowSwap(int[] input, int n) {
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

    /**
     * printPermutations - prints out all permutations in a set
     * 
     * @param uniquePermutations
     */
    private static void printPermutations(Set<int[]> uniquePermutations) {
        System.out.println();
        for (int[] permutations : uniquePermutations) {
            for (int element : permutations) {
                System.out.print(element + " ");

            }
            System.out.println();
        }

    }

    private static void printArray(int[] input) {
        for (int element : input) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
