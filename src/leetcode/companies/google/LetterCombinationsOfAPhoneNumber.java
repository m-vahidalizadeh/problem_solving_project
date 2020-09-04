package leetcode.companies.google;

import java.util.*;

/**
 * Letter Combinations of a Phone Number
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * Example:
 * <p>
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * <p>
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationsOfAPhoneNumber {

    Map<Character, List<Character>> map = new HashMap<>() {{
        put('2', List.of('a', 'b', 'c'));
        put('3', List.of('d', 'e', 'f'));
        put('4', List.of('g', 'h', 'i'));
        put('5', List.of('j', 'k', 'l'));
        put('6', List.of('m', 'n', 'o'));
        put('7', List.of('p', 'q', 'r', 's'));
        put('8', List.of('t', 'u', 'v'));
        put('9', List.of('w', 'x', 'y', 'z'));
    }};

    List<String> result;

    public List<String> letterCombinations(String digits) {
        if (digits == null) return Collections.emptyList();
        int n = digits.length();
        if (n == 0) return Collections.emptyList();
        result = new ArrayList<>();
        addCombinations(digits, 0, n, "");
        return result;
    }

    private void addCombinations(String digits, int i, int n, String curr) {
        if (i == n) result.add(curr);
        else {
            for (char c : map.get(digits.charAt(i))) {
                addCombinations(digits, i + 1, n, curr + c);
            }
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber l = new LetterCombinationsOfAPhoneNumber();
        System.out.println(l.letterCombinations("23"));
    }

}
