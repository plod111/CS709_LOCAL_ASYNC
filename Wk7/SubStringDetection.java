/**
 * CS709 - Week 7 ASYNC 
 * Exercise: Recursive sub-string detection
 * 
 * 
 * @author B.Cornish
 * @collaborator P.Chu
 * @date Oct 26, 2023
 */

public class SubStringDetection {

    /**
     * Recursive sub-string detection
     * @param word
     * @param substring
     * @param n
     * @return
     */
    public static boolean subStringDetect(String word, String substring, int n) {

        // base case: if at the end of word: return false
        if (n > word.length() - substring.length())
            return false;

        // base case: if detected
        if (word.substring(n, n + substring.length()).equals(substring))
            return true;

        // recurse n+1
        else
            return subStringDetect(word, substring, n + 1);

    }

    public static void main(String[] args) {

        // String to be searched, from command line
        String word = args[0];

        // String to be searched for, from command line
        String substring = args[1];

        System.out.println("Sub-string Detected: " + subStringDetect(word, substring, 0));

    }
}