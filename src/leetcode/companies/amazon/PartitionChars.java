package leetcode.companies.amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. Partition Labels
 * A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 *
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase English letters ('a' to 'z') only.
 */
public class PartitionChars {

    public List<Integer> partitionLabels(String S) {
        int n = S.length();
        List<Integer> res = new ArrayList<>();
        int[] lastSeen = new int[26];
        for (int i = 0; i < n; i++) lastSeen[S.charAt(i) - 'a'] = i;
        int start = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            char c = S.charAt(i);
            j = Math.max(j, lastSeen[c - 'a']);
            if (i == j) {
                res.add(j - start + 1);
                start = j + 1;
            }
        }
        return res;
    }

}
