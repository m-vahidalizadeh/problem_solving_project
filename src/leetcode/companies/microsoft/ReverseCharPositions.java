package leetcode.companies.microsoft;

/**
 * Reverse Only Letters
 * Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.
 * <p>
 * Example 1:
 * <p>
 * Input: "ab-cd"
 * Output: "dc-ba"
 * Example 2:
 * <p>
 * Input: "a-bC-dEf-ghIj"
 * Output: "j-Ih-gfE-dCba"
 * Example 3:
 * <p>
 * Input: "Test1ng-Leet=code-Q!"
 * Output: "Qedo1ct-eeLg=ntse-T!"
 * <p>
 * Note:
 * <p>
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122
 * S doesn't contain \ or "
 */
public class ReverseCharPositions {

    public String reverseOnlyLetters(String S) {
        int n = S.length();
        char[] orig = S.toCharArray();
        char[] reversed = new char[n];
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            while (left <= right && !Character.isLetter(orig[right])) {
                reversed[right] = orig[right];
                right--;
            }
            while (left <= right && !Character.isLetter(orig[left])) {
                reversed[left] = orig[left];
                left++;
            }
            if (left <= right) {
                reversed[left] = orig[right];
                reversed[right] = orig[left];
                left++;
                right--;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append(reversed[i]);
        return sb.toString();
    }

    public static void main(String[] args) {
        ReverseCharPositions r = new ReverseCharPositions();
        System.out.println(r.reverseOnlyLetters("ab-cd"));
        System.out.println(r.reverseOnlyLetters("a-bC-dEf-ghIj"));
        System.out.println(r.reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }

}
