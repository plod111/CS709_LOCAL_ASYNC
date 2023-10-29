import java.util.HashSet;
import java.util.Set;

/**
 * CS709 - Week 7 ASYNC 
 * Exercise: Generate all the permutations of 7 letters recursively
 * Version: 2
 * 
 * @author P.Chu
 * @collaborator B.Cornish
 * @date Oct 27, 2023
 */


public class LetterPermutations{


    /**
     * @param args
     */
    
    public static void main(String[] args) {

    
        if (args.length != 1 || args[0].length() != 7) {     // only accepts 7 letter strings or else program exits.
            System.out.println("Only seven letter strings are accepted.");
            System.exit(0);
        }

        String input = args[0];
        char[] inputArray = input.toCharArray(); // sets input to a char array

        Set<String> uniquePermutations = new HashSet<>();    //creates new hashset for unique permutations.

        permuteString(inputArray, inputArray.length, uniquePermutations);

        for (String permutations : uniquePermutations) {
            System.out.println(permutations);
        }

    }//main end
    
    public static void permuteString(char[] input, int size, Set<String> uniquePermutations) {
        //base case if method is working the last letter in the char array.
        if (size == 1) {
            // converts char array back to a string when base case is met.
            String permutation = new String(input); 

            // adds the new string to permuteString set.
            uniquePermutations.add(permutation);
            
        } else {
            //creates a permutation recursively by swapping position of chars.
            for (int i = 0; i < size; i++) {  
                permuteString(input, size - 1, uniquePermutations);  
                
                if (size % 2 == 1) {
                    swap(input, 0, size - 1);
                } else {
                    swap(input, i, size - 1);
                }
            }
        }
    }
    //helper method to swap chars for the input array
    public static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

   
}