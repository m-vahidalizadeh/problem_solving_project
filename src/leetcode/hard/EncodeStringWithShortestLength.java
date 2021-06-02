package leetcode.hard;

/**
 * 471. Encode String with Shortest Length
 * Given a string s, encode the string such that its encoded length is the shortest.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. k should be a positive integer.
 *
 * If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them.
 *
 * Example 1:
 *
 * Input: s = "aaa"
 * Output: "aaa"
 * Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
 * Example 2:
 *
 * Input: s = "aaaaa"
 * Output: "5[a]"
 * Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
 * Example 3:
 *
 * Input: s = "aaaaaaaaaa"
 * Output: "10[a]"
 * Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
 * Example 4:
 *
 * Input: s = "aabcaabcd"
 * Output: "2[aabc]d"
 * Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
 * Example 5:
 *
 * Input: s = "abbbabbbcabbbabbbc"
 * Output: "2[2[abbb]c]"
 * Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".
 *
 * Constraints:
 *
 * 1 <= s.length <= 150
 * s consists of only lowercase English letters.
 */
public class EncodeStringWithShortestLength {

    public String encode(String s) {
        int n = s.length();
        var dp = new String[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                var currSub = s.substring(i, j + 1);
                dp[i][j] = currSub;
                if (currSub.length() > 4) {
                    for (int k = i; k < j; k++) {
                        if (dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length())
                            dp[i][j] = dp[i][k] + dp[k + 1][j];
                    }
                    for (int k = i; k <= j; k++) {
                        var pattern = s.substring(i, k + 1);
                        if (pattern.length() > 0 && currSub.length() % pattern.length() == 0 && currSub.replace(pattern, "").equals("")) {
                            var candidate = currSub.length() / pattern.length() + "[" + dp[i][k] + "]";
                            if (candidate.length() < dp[i][j].length()) dp[i][j] = candidate;
                        }
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

}
