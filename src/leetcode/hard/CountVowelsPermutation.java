package leetcode.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Count Vowels Permutation
 * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
 * <p>
 * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
 * Each vowel 'a' may only be followed by an 'e'.
 * Each vowel 'e' may only be followed by an 'a' or an 'i'.
 * Each vowel 'i' may not be followed by another 'i'.
 * Each vowel 'o' may only be followed by an 'i' or a 'u'.
 * Each vowel 'u' may only be followed by an 'a'.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1
 * Output: 5
 * Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: 10
 * Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
 * Example 3:
 * <p>
 * Input: n = 5
 * Output: 68
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 2 * 10^4
 */
public class CountVowelsPermutation {

    public static final int FACTOR = 1_000_000_007;
    Map<Character, Set<Character>> can;
    Map<String, Long> dp;

    public int countVowelPermutation(int n) {
        can = new HashMap<>() {{
            put('a', Set.of('e'));
            put('e', Set.of('a', 'i'));
            put('i', Set.of('a', 'e', 'o', 'u'));
            put('o', Set.of('i', 'u'));
            put('u', Set.of('a'));
        }};
        dp = new HashMap<>();
        long res = 0;
        for (char c : can.keySet()) res += rec(n, c);
        return (int) (res % FACTOR);
    }

    private long rec(int n, char lastChar) {
        String key = n + "," + lastChar;
        if (dp.containsKey(key)) return dp.get(key);
        if (n == 1) return 1;
        long res = 0;
        int newN = n - 1;
        for (char c : can.get(lastChar)) res += rec(newN, c);
        res = res % FACTOR;
        dp.put(key, res);
        return res;
    }

    public static void main(String[] args) {
        CountVowelsPermutation c = new CountVowelsPermutation();
        System.out.println(c.countVowelPermutation(1));
        System.out.println(c.countVowelPermutation(2));
        System.out.println(c.countVowelPermutation(5));
    }

}
