package leetcode.companies.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 727. Minimum Window Subsequence
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.
 *
 * Example 1:
 *
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 * Note:
 *
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 */
public class MinWindowSubsequence {

    public String minWindow(String S, String T) {
        int n = S.length();
        int[] last = new int[26];
        int[][] next = new int[n][26];
        Arrays.fill(last, -1);
        for (int i = n - 1; i >= 0; i--) {
            last[S.charAt(i) - 'a'] = i;
            for (int c = 0; c < 26; c++) {
                next[i][c] = last[c];
            }
        }
        List<int[]> windows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) == T.charAt(0)) windows.add(new int[]{i, i});
        }
        for (int j = 1; j < T.length(); j++) {
            int letterIndex = T.charAt(j) - 'a';
            for (int[] window : windows) {
                int e = window[1];
                if (e < n - 1 && next[e + 1][letterIndex] >= 0) {
                    window[1] = next[e + 1][letterIndex];
                } else {
                    window[0] = -1;
                    window[1] = -1;
                    break;
                }
            }
        }
        int[] res = new int[]{-1, n};
        for (int[] window : windows) {
            if (window[0] == -1) break;
            if (window[1] - window[0] < res[1] - res[0]) res = window;
        }
        return res[0] == -1 ? "" : S.substring(res[0], res[1] + 1);
    }

    public static void main(String[] args) {
        MinWindowSubsequence m = new MinWindowSubsequence();
        System.out.println(m.minWindow("abcdebdde", "bde"));
        System.out.println(m.minWindow("ffynmlzesdshlvugsigobutgaetsnjlizvqjdpccdylclqcbghhixpjihximvhapymfkjxyyxfwvsfyctmhwmfjyjidnfryiyajmtakisaxwglwpqaxaicuprrvxybzdxunypzofhpclqiybgniqzsdeqwrdsfjyfkgmejxfqjkmukvgygafwokeoeglanevavyrpduigitmrimtaslzboauwbluvlfqquocxrzrbvvplsivujojscytmeyjolvvyzwizpuhejsdzkfwgqdbwinkxqypaphktonqwwanapouqyjdbptqfowhemsnsl",
                "ntimcimzah"));
    }

}
