import java.util.Scanner;

/**
 * CS709 - Week 7 ASYNC 
 * Exercise: Generate all the permutations of 7 letters recursively
 * Version: 1
 * 
 * @author P.Chu
 * @collaborator B.Cornish
 * @date Oct 27, 2023
 */


public class letterPermutations{


    
    public static void main(String[] args) {
        if (args.length != 1 || args[0].length() != 7) {    
            System.out.println("Only seven letter strings are accepted.");
            System.exit(0);
        }

        String input = args[0];
        char[] inputArray = input.toCharArray(); // sets input to a char array
        permuteString(inputArray, inputArray.length); 

    }//main end
    
    public static void permuteString(char[] input, int size) {
        if (size == 1) {
            System.out.println(new String(input));
        } else {
            for (int i = 0; i < size; i++) {
                permuteString(input, size - 1);

                if (size % 2 == 1) {
                    swap(input, 0, size - 1);
                } else {
                    swap(input, i, size - 1);
                }
            }
        }
    }

    public static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

   
}