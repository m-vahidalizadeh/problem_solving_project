package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Swap Adjacent in LR String
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.
 * <p>
 * Example 1:
 * <p>
 * Input: start = "X", end = "L"
 * Output: false
 * Explanation:
 * We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 * Example 2:
 * <p>
 * Input: start = "LLR", end = "RRL"
 * Output: false
 * Example 3:
 * <p>
 * Input: start = "XLLR", end = "LXLX"
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * 1 <= start.length <= 104
 * start.length == end.length
 * Both start and end will only consist of characters in 'L', 'R', and 'X'.
 */
public class SwapAdjacentInLRString {

    public boolean canTransform(String start, String end) {
        int n = start.length();
        if (!equalFrequencies(getFrequencies(start), getFrequencies(end))) return false;
        int s = 0;
        int e = 0;
        while (s < n && e < n) {
            while (s < n && start.charAt(s) == 'X') s++;
            while (e < n && end.charAt(e) == 'X') e++;
            if (s == n || e == n) return s == n && e == n;
            char sC = start.charAt(s);
            char eC = end.charAt(e);
            if (sC != eC) return false;
            if (sC == 'L' && s < e) return false;
            if (eC == 'R' && s > e) return false;
            s++;
            e++;
        }
        return true;
    }

    private Map<Character, Integer> getFrequencies(String input) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : input.toCharArray()) frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        return frequencies;
    }

    private boolean equalFrequencies(Map<Character, Integer> freq1, Map<Character, Integer> freq2) {
        for (Map.Entry<Character, Integer> e : freq1.entrySet()) {
            if (!freq2.containsKey(e.getKey()) || !e.getValue().equals(freq2.get(e.getKey()))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwapAdjacentInLRString s = new SwapAdjacentInLRString();
        System.out.println(s.canTransform("RXXLRXRXL", "XRLXXRRLX"));
    }

}
