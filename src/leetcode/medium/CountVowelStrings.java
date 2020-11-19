package leetcode.medium;

import java.util.Map;

/**
 * 1641. Count Sorted Vowel Strings
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
 *
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 * Example 2:
 *
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 * Example 3:
 *
 * Input: n = 33
 * Output: 66045
 *
 * Constraints:
 *
 * 1 <= n <= 50
 */
public class CountVowelStrings {

    Map<Integer, Character> vowels;

    public int countVowelStrings(int n) {
        vowels = Map.of(0, 'a', 1, 'e', 2, 'i', 3, 'o', 4, 'u');
        int[][] dp = new int[n + 1][5];
        for (int j = 0; j < 5; j++) dp[1][j] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                for (Map.Entry<Integer, Character> e : vowels.entrySet()) {
                    if (j <= e.getKey()) {
                        dp[i + 1][e.getKey()] += dp[i][j];
                    }
                }
            }
        }
        int sum = 0;
        for (int j = 0; j < 5; j++) sum += dp[n][j];
        return sum;
    }

    public static void main(String[] args) {
        CountVowelStrings c = new CountVowelStrings();
        System.out.println(c.countVowelStrings(1));
        System.out.println(c.countVowelStrings(2));
        System.out.println(c.countVowelStrings(33));
    }

}
