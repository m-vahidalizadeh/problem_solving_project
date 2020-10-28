package leetcode.companies.amazon;

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
public class LetterCombinationOfAPhoneNumber {

    Map<Integer, List<Character>> map;

    public List<String> letterCombinations(String digits) {
        if (digits.isBlank()) return Collections.emptyList();
        map = new HashMap<>() {{
            put(2, List.of('a', 'b', 'c'));
            put(3, List.of('d', 'e', 'f'));
            put(4, List.of('g', 'h', 'i'));
            put(5, List.of('j', 'k', 'l'));
            put(6, List.of('m', 'n', 'o'));
            put(7, List.of('p', 'q', 'r', 's'));
            put(8, List.of('t', 'u', 'v'));
            put(9, List.of('w', 'x', 'y', 'z'));
        }};
        List<String> result = new ArrayList<>();
        rec(digits, 0, digits.length(), result, "");
        return result;
    }

    private void rec(String digits, int i, int n, List<String> result, String curr) {
        if (i == n) result.add(curr);
        else {
            int num = Character.getNumericValue(digits.charAt(i));
            for (char c : map.get(num)) rec(digits, i + 1, n, result, curr + c);
        }
    }

}
