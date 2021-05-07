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
public class RemoveInvalidParentheses {

    int min;
    Set<String> visited;
    Map<String, Integer> resMap;
    String s;
    int l;

    public List<String> removeInvalidParentheses(String s) {
        min = Integer.MAX_VALUE;
        visited = new HashSet<>();
        resMap = new HashMap<>();
        l = s.length();
        this.s = s;
        rec(0, "", 0);
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : resMap.entrySet()) {
            if (entry.getValue() == min) res.add(entry.getKey());
        }
        return res;
    }

    private void rec(int index, String curr, int open) {
        if (index == l) {
            if (open == 0) {
                int removed = l - curr.length();
                min = Math.min(min, removed);
                resMap.put(curr, removed);
            }
            return;
        }
        String key = index + "-" + curr + "-" + open;
        if (visited.contains(key)) return;
        visited.add(key);
        char c = s.charAt(index);
        if (c == '(') {
            rec(index + 1, curr + c, open + 1);
            rec(index + 1, curr, open);
        } else if (c == ')') {
            if (open > 0) rec(index + 1, curr + c, open - 1);
            rec(index + 1, curr, open);
        } else {
            rec(index + 1, curr + c, open);
        }

    }

}
