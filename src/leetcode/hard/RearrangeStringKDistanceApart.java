package leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 358. Rearrange String k Distance Apart
 * Given a string s and an integer k, rearrange s such that the same characters are at least distance k from each other. If it is not possible to rearrange the string, return an empty string "".
 *
 * Example 1:
 *
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc"
 * Explanation: The same letters are at least a distance of 3 from each other.
 * Example 2:
 *
 * Input: s = "aaabc", k = 3
 * Output: ""
 * Explanation: It is not possible to rearrange the string.
 * Example 3:
 *
 * Input: s = "aaadbbcc", k = 2
 * Output: "abacabcd"
 * Explanation: The same letters are at least a distance of 2 from each other.
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of only lowercase English letters.
 * 0 <= k <= s.length
 */
public class RearrangeStringKDistanceApart {

    public String rearrangeString(String s, int k) {
        if (k == 0) return s;
        StringBuilder res = new StringBuilder();
        int[] freqArr = new int[26];
        for (char c : s.toCharArray()) freqArr[c - 'a']++;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]); // (character,frequency)
        for (int i = 0; i < 26; i++) {
            if (freqArr[i] > 0) pq.add(new int[]{i, freqArr[i]});
        }
        while (!pq.isEmpty()) {
            List<int[]> tempList = new ArrayList<>();
            if (pq.size() < k && pq.peek()[1] > 1) return "";
            int limit = Math.min(k, pq.size());
            for (int i = 0; i < limit; i++) {
                int[] currItem = pq.poll();
                char currChar = (char) ('a' + currItem[0]);
                int freq = currItem[1];
                res.append(currChar);
                freq--;
                if (freq > 0) tempList.add(new int[]{currItem[0], freq});
            }
            pq.addAll(tempList);
        }
        return res.toString();
    }

}
