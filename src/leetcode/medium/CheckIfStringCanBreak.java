package leetcode.medium;

import java.util.Arrays;

/**
 * Check If a String Can Break Another String
 * Given two strings: s1 and s2 with the same size, check if some permutation of string s1 can break some permutation of string s2 or vice-versa (in other words s2 can break s1).
 * <p>
 * A string x can break string y (both of size n) if x[i] >= y[i] (in alphabetical order) for all i between 0 and n-1.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "abc", s2 = "xya"
 * Output: true
 * Explanation: "ayx" is a permutation of s2="xya" which can break to string "abc" which is a permutation of s1="abc".
 * Example 2:
 * <p>
 * Input: s1 = "abe", s2 = "acd"
 * Output: false
 * Explanation: All permutations for s1="abe" are: "abe", "aeb", "bae", "bea", "eab" and "eba" and all permutation for s2="acd" are: "acd", "adc", "cad", "cda", "dac" and "dca". However, there is not any permutation from s1 which can break some permutation from s2 and vice-versa.
 * Example 3:
 * <p>
 * Input: s1 = "leetcodee", s2 = "interview"
 * Output: true
 * <p>
 * Constraints:
 * <p>
 * s1.length == n
 * s2.length == n
 * 1 <= n <= 10^5
 * All strings consist of lowercase English letters.
 */
public class CheckIfStringCanBreak {

    public boolean checkIfCanBreak(String s1, String s2) {
        int n = s1.length();
        String s1Sorted = bucketSort(s1, n);
        String s2Sorted = bucketSort(s2, n);
        return canAnyOfTwoBreak(s1Sorted, s2Sorted, n);
    }

    /**
     * Check if any of the strings can beat the other one.
     *
     * @param s1Sorted String 1 sorted
     * @param s2Sorted String 2 sorted
     * @param n        The length of two strings
     * @return If any of two can beat the other one
     */
    private boolean canAnyOfTwoBreak(String s1Sorted, String s2Sorted, int n) {
        Boolean s1Greater = isS1Greater(s1Sorted, s2Sorted, n);
        if (s1Greater == null) {
            return true;
        } else if (s1Greater) {
            for (int i = 0; i < n; i++) {
                if (s1Sorted.charAt(i) < s2Sorted.charAt(i)) return false;
            }
            return true;
        }
        // s1Greater=false;
        for (int i = 0; i < n; i++) {
            if (s1Sorted.charAt(i) > s2Sorted.charAt(i)) return false;
        }
        return true;
    }

    /**
     * Tells if s1 is greater than s2 or s2 is greater or they are equal. This method gets the tone like increasing.
     *
     * @param s1Sorted String 1 sorted
     * @param s2Sorted String 2 sorted
     * @param n        The length of string 1 and 2
     * @return If string 1 is greater or not
     */
    private Boolean isS1Greater(String s1Sorted, String s2Sorted, int n) {
        int index = 0;
        Boolean s1Greater = null;
        while (index < n) {
            if (s1Sorted.charAt(index) > s2Sorted.charAt(index)) {
                s1Greater = true;
                break;
            } else if (s1Sorted.charAt(index) < s2Sorted.charAt(index)) {
                s1Greater = false;
                break;
            }
            index++;
        }
        return s1Greater;
    }

    /**
     * Sorts a string in English letter.
     *
     * @param input A string in English letters
     * @param n     The length of the string
     * @return The sorted string
     */
    private String bucketSort(String input, int n) {
        int[] chars = new int[26];
        for (int i = 0; i < n; i++) chars[input.charAt(i) - 'a']++;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= chars[i]; j++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CheckIfStringCanBreak c = new CheckIfStringCanBreak();
        String s1 = "abc", s2 = "xya";
        System.out.println(c.checkIfCanBreak(s1, s2));
        s1 = "abe";
        s2 = "acd";
        System.out.println(c.checkIfCanBreak(s1, s2));
        s1 = "leetcodee";
        s2 = "interview";
        System.out.println(c.checkIfCanBreak(s1, s2));
    }

}
