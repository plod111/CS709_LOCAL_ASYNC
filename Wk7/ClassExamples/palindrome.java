public class palindrome {

    // palindrome - qwert y trewq
    // 01234 5 678910

    // Base case:

    // ODD # chars
    // counter from start == counter from end --> return

    // Even # chars
    // counter start = counter end -1 --> return
    // OR counter start +1 = counter end --> return

    // /////////////////////////////////

    public static void main(String[] args) {
        String s = "qwertrewq";
        int i = 0;
        int j = s.length() - 1;
        System.out.println(palindromeTest(s, i, j));
    }

    public static boolean palindromeTest(String s, int i, int j) {

        if (s.length() % 2 == 1) {
            // ODD
            if (i == j)
                return true;

            // EVEN
            else if (i + 1 == j)
                return true;
        }
        if (s.charAt(i) == (s.charAt(j)))

            palindromeTest(s, i + 1, j - 1);
        else
            return false;

    }
}