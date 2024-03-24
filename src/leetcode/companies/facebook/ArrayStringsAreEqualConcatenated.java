package leetcode.companies.facebook;

/**
 * 1662. Check If Two String Arrays are Equivalent
 * Solved
 * Easy
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
 *
 * A string is represented by an array if the array elements concatenated in order forms the string.
 *
 * Example 1:
 *
 * Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
 * Output: true
 * Explanation:
 * word1 represents string "ab" + "c" -> "abc"
 * word2 represents string "a" + "bc" -> "abc"
 * The strings are the same, so return true.
 * Example 2:
 *
 * Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
 * Output: false
 * Example 3:
 *
 * Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
 * Output: true
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 10^3
 * 1 <= word1[i].length, word2[i].length <= 10^3
 * 1 <= sum(word1[i].length), sum(word2[i].length) <= 10^3
 * word1[i] and word2[i] consist of lowercase letters.
 */
public class ArrayStringsAreEqualConcatenated {

    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int i1 = 0, i2 = 0, j1 = 0, j2 = 0, n1 = word1.length, n2 = word2.length;
        while (i1 < n1 && j1 < n2) {
            if (i2 == word1[i1].length()) {
                i1++;
                i2 = 0;
            }
            if (j2 == word2[j1].length()) {
                j1++;
                j2 = 0;
            }
            if (i1 < n1 && i2 < word1[i1].length() && j1 < n2 && j2 < word2[j1].length()) {
                if (word1[i1].charAt(i2) != word2[j1].charAt(j2)) return false;
                i2++;
                j2++;
            }
        }
        return i1 == n1 && j1 == n2;
    }

}
