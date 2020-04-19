package medium;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A happy string is a string that:
 * <p>
 * consists only of letters of the set ['a', 'b', 'c'].
 * s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
 * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.
 * <p>
 * Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
 * <p>
 * Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1, k = 3
 * Output: "c"
 * Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
 * Example 2:
 * <p>
 * Input: n = 1, k = 4
 * Output: ""
 * Explanation: There are only 3 happy strings of length 1.
 * Example 3:
 * <p>
 * Input: n = 3, k = 9
 * Output: "cab"
 * Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"
 * Example 4:
 * <p>
 * Input: n = 2, k = 7
 * Output: ""
 * Example 5:
 * <p>
 * Input: n = 10, k = 100
 * Output: "abacbabacb"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10
 * 1 <= k <= 100
 */
public class HappyStrings {

    private void generateHappyStrings(StringBuilder sb, int n, PriorityQueue<String> happyStrings,
                                      Set<String> acceptedChars, String lastAdded) {
        if (sb.length() == n) {
            happyStrings.add(sb.toString());
        } else {
            for (String acceptedChar : acceptedChars) {
                if (acceptedChar.equals(lastAdded)) {
                    continue;
                }
                generateHappyStrings(new StringBuilder(sb).append(acceptedChar), n, happyStrings, acceptedChars, acceptedChar);
            }
        }
    }

    public String getHappyString(int n, int k) {
        Set<String> acceptedChars = Stream.of("a", "b", "c").collect(Collectors.toCollection(HashSet::new));
        PriorityQueue<String> happyStrings = new PriorityQueue<>();
        for (String acceptedChar : acceptedChars) {
            generateHappyStrings(new StringBuilder().append(acceptedChar), n, happyStrings, acceptedChars, acceptedChar);
        }
        String kthHappyString = "";
        for (int i = 0; i < k; i++) {
            kthHappyString = happyStrings.poll();
        }
        return kthHappyString == null ? "" : kthHappyString;
    }

    public static void main(String[] args) {
        HappyStrings happyStrings = new HappyStrings();
        System.out.println(happyStrings.getHappyString(1, 3));
        System.out.println(happyStrings.getHappyString(1, 4));
        System.out.println(happyStrings.getHappyString(3, 9));
        System.out.println(happyStrings.getHappyString(2, 7));
    }

}
