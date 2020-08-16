package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Letter Case Permutation
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.
 * <p>
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 * <p>
 * Input: S = "12345"
 * Output: ["12345"]
 * <p>
 * Constraints:
 * <p>
 * S will be a string with length between 1 and 12.
 * S will consist only of letters or digits.
 */
public class LetterCasePermutation {

    List<String> permutations;

    public List<String> letterCasePermutation(String S) {
        permutations = new ArrayList<>();
        getPermutations(S, 0, S.length(), new StringBuilder());
        return permutations;
    }

    private void getPermutations(String S, int i, int n, StringBuilder sb) {
        if (i == n) permutations.add(sb.toString());
        else {
            char c = S.charAt(i);
            if (Character.isAlphabetic(c)) {
                getPermutations(S, i + 1, n, new StringBuilder(sb).append(Character.toLowerCase(c)));
                getPermutations(S, i + 1, n, new StringBuilder(sb).append(Character.toUpperCase(c)));
            } else {
                getPermutations(S, i + 1, n, new StringBuilder(sb).append(c));
            }
        }
    }

    public static void main(String[] args) {
        LetterCasePermutation l = new LetterCasePermutation();
        System.out.println(l.letterCasePermutation("a1b2"));
    }

}
