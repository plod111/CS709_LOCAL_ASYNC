package Wk7;

public class recursiveSum {
    
public static void main(String[] args){
        // convert args array into int array
        int[] arr = {10,2,3,4,567,46,97,28,9,45,7,56,9,132,4,968,54,1546,98,54,2,658};  //new int[args.length];


        // for (int i = 0; i < args.length; i++) {
        //     arr[i] = Integer.parseInt(args[i]);
        // }


        System.out.println(sum1(arr, 0));
        System.out.println(sum2(arr, arr.length-1));
    } // end main
    

    public static int sum1(int[] arr, int index) {
        if (index == arr.length) {
            return 0;
        } else {
            return arr[index] + sum1(arr, index + 1);
        }
    } // end sum1


    public static int sum2(int[] arr, int n) {
        // if no numbers, reutrn 0
        if (n == 0) {
            return arr[0];
        }
        return arr[n] + sum2(arr, n - 1);
    } // end sum2

}
