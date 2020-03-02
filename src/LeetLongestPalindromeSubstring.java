public class LeetLongestPalindromeSubstring {

    public static void main(String[] args) {
        LeetLongestPalindromeSubstring leetLongestPalindromeSubstring = new LeetLongestPalindromeSubstring();
        String s = "cbbd";
        System.out.format("The longest palindrome substring of %s is %s.", s, leetLongestPalindromeSubstring.longestPalindrome(s));
    }

    public String longestPalindrome(String s) {
        String answer = "";
        if (s == null)
            return answer;
        int n = s.length();
        if (n == 0)
            return answer;
        if (n == 1)
            return s;
        int maxLength = 0;
        char[] sChars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            // i in the middle
            int a = i - 1;
            int b = i + 1;
            while (a >= 0 && b <= n - 1 && sChars[a] == sChars[b]) {
                a--;
                b++;
            }
            a++;
            int length = b - a;
            if (length > maxLength) {
                maxLength = length;
                answer = s.substring(a, b);
            }
            // i in the right
            a = i - 1;
            b = i;
            while (a >= 0 && b <= n - 1 && sChars[a] == sChars[b]) {
                a--;
                b++;
            }
            a++;
            length = b - a;
            if (length > maxLength) {
                maxLength = length;
                answer = s.substring(a, b);
            }
        }
        return answer;
    }

}
