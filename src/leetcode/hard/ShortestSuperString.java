package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 943. Find the Shortest Superstring
 * Given an array A of strings, find any smallest string that contains each string in A as a substring.
 *
 * We may assume that no string in A is substring of another string in A.
 *
 * Example 1:
 *
 * Input: ["alex","loves","leetcode"]
 * Output: "alexlovesleetcode"
 * Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
 * Example 2:
 *
 * Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
 * Output: "gctaagttcatgcatc"
 *
 * Note:
 *
 * 1 <= A.length <= 12
 * 1 <= A[i].length <= 20
 */
public class ShortestSuperString {

    public String shortestSuperstring(String[] A) {
        List<String> words = new ArrayList<>(Arrays.asList(A));
        while (true) {
            int n = words.size();
            if (n == 1) break;
            int maxI = -1;
            int maxJ = -1;
            int maxL = -1;
            String newStr = "";
            for (int i = 0; i < n - 1; i++) {
                String s1 = words.get(i);
                int l1 = s1.length();
                for (int j = i + 1; j < n; j++) {
                    String s2 = words.get(j);
                    int l2 = s2.length();
                    String merged = merge(s1, s2);
                    int saved = l1 + l2 - merged.length();
                    if (saved > maxL) {
                        maxI = i;
                        maxJ = j;
                        newStr = merged;
                        maxL = saved;
                    }
                }
            }
            String s1 = words.get(maxI);
            String s2 = words.get(maxJ);
            words.remove(s1);
            words.remove(s2);
            words.add(newStr);
        }
        return words.get(0);
    }

    private String merge(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int overlapped1 = 0;
        int overlapped2 = 0;
        for (int l = 1; l <= l1 && l <= l2; l++) {
            if (s1.substring(l1 - l).equals(s2.substring(0, l))) overlapped1 = l;
        }
        for (int l = 1; l <= l1 && l <= l2; l++) {
            if (s2.substring(l2 - l).equals(s1.substring(0, l))) overlapped2 = l;
        }
        return overlapped1 >= overlapped2 ? s1.substring(0, l1 - overlapped1) + s2 : s2.substring(0, l2 - overlapped2) + s1;
    }

}
