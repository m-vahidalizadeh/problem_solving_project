package leetcode.companies.google;

import java.util.*;

/**
 * 249. Group Shifted Strings
 * We can shift a string by shifting each of its letters to its successive letter.
 *
 * For example, "abc" can be shifted to be "bcd".
 * We can keep shifting the string to form a sequence.
 *
 * For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
 * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
 * Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
 * Example 2:
 *
 * Input: strings = ["a"]
 * Output: [["a"]]
 *
 * Constraints:
 *
 * 1 <= strings.length <= 200
 * 1 <= strings[i].length <= 50
 * strings[i] consists of lowercase English letters.
 */
public class GroupStrings {

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> resMap = new HashMap<>();
        for (String string : strings) {
            String sequence = getSequence(string);
            resMap.computeIfAbsent(sequence, x -> new ArrayList<>());
            resMap.get(sequence).add(string);
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : resMap.entrySet()) {
            res.add(new ArrayList<>(entry.getValue()));
        }
        return res;
    }

    private String getSequence(String string) {
        int n = string.length();
        int[] sequence = new int[n];
        char prev = ' ';
        for (int i = 0; i < n; i++) {
            char curr = string.charAt(i);
            if (prev == ' ') {
                sequence[i] = 0;
            } else {
                int count = 0;
                while (prev != curr) {
                    count++;
                    if (prev - 'z' == 0) prev = 'a';
                    else prev++;
                }
                sequence[i] = count;
            }
            prev = curr;
        }
        return Arrays.toString(sequence);
    }

}
