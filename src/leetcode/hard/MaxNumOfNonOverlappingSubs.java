package leetcode.hard;

import java.util.*;

/**
 * 1520. Maximum Number of Non-Overlapping Substrings
 * Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet the following conditions:
 *
 * The substrings do not overlap, that is for any two substrings s[i..j] and s[k..l], either j < k or i > l is true.
 * A substring that contains a certain character c must also contain all occurrences of c.
 * Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same number of substrings, return the one with minimum total length. It can be shown that there exists a unique solution of minimum total length.
 *
 * Notice that you can return the substrings in any order.
 *
 * Example 1:
 *
 * Input: s = "adefaddaccc"
 * Output: ["e","f","ccc"]
 * Explanation: The following are all the possible substrings that meet the conditions:
 * [
 *   "adefaddaccc"
 *   "adefadda",
 *   "ef",
 *   "e",
 *   "f",
 *   "ccc",
 * ]
 * If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda", we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the same number of substrings exist.
 * Example 2:
 *
 * Input: s = "abbaccd"
 * Output: ["d","bb","cc"]
 * Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 */
public class MaxNumOfNonOverlappingSubs {

    public List<String> maxNumOfSubstrings(String s) {
        Map<Character, int[]> letterRange = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (letterRange.containsKey(c)) letterRange.get(c)[1] = i;
            else letterRange.put(c, new int[]{i, i});
        }
        for (Map.Entry<Character, int[]> entry : letterRange.entrySet()) {
            char key = entry.getKey();
            int start = entry.getValue()[0];
            int end = entry.getValue()[1];
            Stack<int[]> stack = new Stack<>();
            stack.push(new int[]{start, end});
            while (!stack.isEmpty()) {
                int[] curr = stack.pop();
                int currS = curr[0];
                int currE = curr[1];
                for (int i = currS; i <= currE; i++) {
                    int[] newRange = letterRange.get(s.charAt(i));
                    int newS = newRange[0];
                    int newE = newRange[1];
                    if (newS < start) {
                        stack.push(new int[]{newS, start - 1});
                        start = newS;
                    }
                    if (newE > end) {
                        stack.push(new int[]{end + 1, newE});
                        end = newE;
                    }
                }
            }
            letterRange.put(key, new int[]{start, end});
        }
        List<int[]> sortedRanges = new ArrayList<>();
        sortedRanges.addAll(letterRange.values());
        sortedRanges.sort((a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
        List<int[]> seenRanges = new ArrayList<>();
        List<String> res = new ArrayList<>();
        for (int[] range : sortedRanges) {
            if (isValid(range, seenRanges)) {
                seenRanges.add(range);
                res.add(s.substring(range[0], range[1] + 1));
            }
        }
        return res;
    }

    private boolean isValid(int[] range, List<int[]> seenRanges) {
        int start = range[0];
        int end = range[1];
        for (int[] seenRange : seenRanges) {
            int s1 = seenRange[0];
            int e1 = seenRange[1];
            if (end >= s1 && start <= e1) return false;
        }
        return true;
    }

}
