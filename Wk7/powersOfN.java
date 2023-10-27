/**
 * CS709 - Week 7 ASYNC 
 * Exercise: Recursive n^x calculation
 * 
 * 
 * @author B.Cornish
 * @collaborator P.Chu
 * @date Oct 26, 2023
 */

public class powersOfN {
    
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("\nUsage: java powersOfN <base> <exponent>\n");
            System.exit(0);
        }
        
        // base, from command line
        int n = Integer.valueOf(args[0]);

        // exponent, from command line
        int x = Integer.valueOf(args[1]);

        // only positive exponents
        if (x >= 0) {
            System.out.printf("\n%d^%d =  %d\n", n, x, n_to_x(n, x));
        }
        else
        System.out.println("\nExponent must be positive\n");
    } // end main


    /**
     * Recursive n^x calculation
     * @param n
     * @param x
     * @return n^x
     */
    public static int n_to_x(int n, int x) {
        
        // base case: x = 0
        if (x==0)
            return 1;

        // base case: x = 1
        if (x==1)
            return n;

        // recurse x-1
        return n * n_to_x(n, x - 1);
    }
}
