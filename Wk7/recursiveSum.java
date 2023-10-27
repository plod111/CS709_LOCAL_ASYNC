/**
 * CS709 - Week 7 ASYNC 
 * Exercise: Recursive summation from runtime list of ints
 * 
 * Implemented both going forward through array and also backwards through array
 * 
 * 
 * @author B.Cornish
 * @collaborator P.Chu
 * @date Oct 26, 2023
 */


public class recursiveSum {

    public static void main(String[] args) {

        // convert args array into int array
        int[] arr = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }

        System.out.println("Recursive sum, forward: " + sum1(arr, 0));
        System.out.println("Recursive sum, reverse: " + sum2(arr, arr.length - 1));
    } // end main

    /**
     * Recursive sum, forward
     * @param arr
     * @param index
     * @return sum
     */
    public static int sum1(int[] arr, int index) {
        if (index == arr.length) {
            return 0;
        } else {
            return arr[index] + sum1(arr, index + 1);
        }
    } // end sum1

    /**
     * Recursive sum, reverse
     * @param arr
     * @param n
     * @return sum
     */
    public static int sum2(int[] arr, int n) {
        
        // gotten to the end of the array
        if (n == 0) {
            return arr[0];
        }

        // if an empty input is given
        if (n == -1)
            return 0;

        return arr[n] + sum2(arr, n - 1);
    } // end sum2

}
