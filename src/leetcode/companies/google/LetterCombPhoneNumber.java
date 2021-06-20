package leetcode.companies.google;

import java.util.*;

/**
 * 17. Letter Combinations of a Phone Number
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Example 1:
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 *
 * Input: digits = ""
 * Output: []
 * Example 3:
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * Constraints:
 *
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */
public class LetterCombPhoneNumber {

    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        if (n == 0) return Collections.emptyList();
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', List.of('a', 'b', 'c'));
        map.put('3', List.of('d', 'e', 'f'));
        map.put('4', List.of('g', 'h', 'i'));
        map.put('5', List.of('j', 'k', 'l'));
        map.put('6', List.of('m', 'n', 'o'));
        map.put('7', List.of('p', 'q', 'r', 's'));
        map.put('8', List.of('t', 'u', 'v'));
        map.put('9', List.of('w', 'x', 'y', 'z'));
        return dfs(0, n, digits, map, new StringBuilder());
    }

    private List<String> dfs(int i, int n, String digits, Map<Character, List<Character>> map, StringBuilder sb) {
        if (i == n) return List.of(sb.toString());
        List<String> res = new ArrayList<>();
        char digit = digits.charAt(i);
        for (char c : map.get(digit)) {
            sb.append(c);
            res.addAll(dfs(i + 1, n, digits, map, sb));
            sb.deleteCharAt(sb.length() - 1);
        }
        return res;
    }

}
