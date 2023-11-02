import java.util.HashSet;
import java.util.Set;

public class isValidTest {

    public static void main(String[] args) {

        int[] input = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] perm1 = { 8, 3, 5, 2, 6, 7, 4, 1 };
        int[] perm2 = { 1, 7, 3, 4, 5, 6, 2, 8 };
        int[] perm3 = { 1, 2, 6, 4, 5, 3, 7, 8 };
        int[] perm4 = { 1, 2, 3, 5, 4, 6, 7, 8 };
        int[] perm5 = { 1, 2, 3, 4, 8, 6, 7, 5 };

        int[][] permutationsArray = { perm1 }; //, perm2, perm3, perm4, perm5 };

        Set<int[]> rainbowPermutations = new HashSet<>();
        for (int[] permutation : permutationsArray) {
            System.out.println("Permutation: " + toString(permutation));
            System.out.println(isValidPermutation(input, permutation, 0));
            if (isValidPermutation(input, permutation, 0)) {
                System.out.println("Valid permutation: " + toString(permutation));
                rainbowPermutations.add(permutation);
            }
        }

        System.out.println("Rainbow permutations: " + rainbowPermutations.size());

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

        boolean isValid = false;

        if (i == input.length / 2) {
            System.out.println("Reached end");
            return true;
        }

        // go thru input and permutation arrays and check if rainbow swap
        // is valid.

        // if ODD array length, validate only those elements that 'middle element does
        // not move
        if (input.length % 2 == 1) {
            if (permutation[permutation.length / 2] == input[permutation.length / 2]) {
                System.out.println("middle element does not move");
                isValid = true;
            } else {
                return false;
            }
        }

        System.out.println("i: " + i);
        System.out.println("p[i]: " + permutation[i]);
        System.out.println("i[i]: " + input[i]);
        System.out.println("p[l-i-1]: " + permutation[input.length - i - 1]);
        System.out.println("i[l-i-1]: " + input[input.length - i - 1]);
        
        System.out.println("p[l-i-1]==i[i]: " + (permutation[input.length - i - 1] == input[i]));

        if (((permutation[i] == input[i]) && (permutation[input.length - i - 1] == input[input.length - i - 1]) && isValid)
                || ((permutation[i] == input[input.length - i - 1])
                        && (permutation[input.length - i - 1] == input[i]) && isValid)) {

            isValid = true;
            System.out.println("***isValid so far" );

        } else {
            System.out.println("not valid");
            return false;
            // return isValid;
        }

        if (isValid) {
             System.out.println("recursive call");
             return isValidPermutation(input, permutation, i + 1);
        }

        // System.out.println(isValid);
        return isValid;
    }

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
