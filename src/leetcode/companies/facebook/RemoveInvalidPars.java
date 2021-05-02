package leetcode.companies.facebook;

import java.util.*;

/**
 * 301. Remove Invalid Parentheses
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 *
 * Return all the possible results. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 * Example 2:
 *
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 * Example 3:
 *
 * Input: s = ")("
 * Output: [""]
 *
 * Constraints:
 *
 * 1 <= s.length <= 25
 * s consists of lowercase English letters and parentheses '(' and ')'.
 * There will be at most 20 parentheses in s.
 */
public class RemoveInvalidPars {

    Map<String, Integer> resMap;
    Set<String> visited;

    public List<String> removeInvalidParentheses(String s) {
        resMap = new HashMap<>();
        visited = new HashSet<>();
        rec(0, s, "", 0);
        int min = Integer.MAX_VALUE;
        for (int num : resMap.values()) min = Math.min(min, num);
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> e : resMap.entrySet()) {
            if (e.getValue() == min) result.add(e.getKey());
        }
        if (result.size() == 0) result.add("");
        return result;
    }

    private void rec(int index, String s, String curr, int open) {
        if (index == s.length()) {
            if (open == 0 && curr.length() > 0) resMap.put(curr, s.length() - curr.length());
            return;
        }
        String key = index + "," + curr + "," + open;
        if (visited.contains(key)) return;
        visited.add(key);
        char c = s.charAt(index);
        if (c == '(') {
            rec(index + 1, s, curr, open); // skip it
            rec(index + 1, s, curr + c, open + 1);
        } else if (c == ')') {
            rec(index + 1, s, curr, open); // skip it
            if (open > 0) rec(index + 1, s, curr + c, open - 1);
        } else {
            rec(index + 1, s, curr + c, open);
        }
    }

}
