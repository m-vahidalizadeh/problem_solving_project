package leetcode.companies.random;

import java.util.Arrays;

/**
 * Permutation in String
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 * <p>
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 * <p>
 * Constraints:
 * <p>
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 */
public class FindPermutationInTheOther {

    public boolean checkInclusion(String s1, String s2) {
        int n = s2.length();
        if (n < s1.length()) return false;
        int target = getSumByAdding(s1);
        int[] sums = new int[n];
        sums[0] = s2.charAt(0);
        for (int i = 1; i < n; i++) sums[i] = sums[i - 1] + Character.valueOf(s2.charAt(i)).hashCode();
        int s = -1, e = 0;
        while (e < n) {
            int sum;
            if (s == -1) {
                sum = sums[e];
            } else {
                sum = sums[e] - sums[s];
            }
            if (sum == target) {
                if (equalFreqs(s1, s2.substring(s + 1, e + 1))) {
                    return true;
                } else {
                    s++;
                    e = s;
                }
            } else if (sum < target) {
                e++;
            } else {
                s++;
                e = s;
            }
        }
        return false;
    }

    private boolean equalFreqs(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        Arrays.sort(aChars);
        Arrays.sort(bChars);
        for (int i = 0; i < aChars.length; i++) if (aChars[i] != bChars[i]) return false;
        return true;
    }

    private int getSumByAdding(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) sum += Character.valueOf(s.charAt(i)).hashCode();
        return sum;
    }

    public static void main(String[] args) {
        FindPermutationInTheOther f = new FindPermutationInTheOther();
//        System.out.println(f.checkInclusion("ab", "eidbaooo"));
//        System.out.println(f.checkInclusion("ab", "eidboaoo"));
//        System.out.println(f.checkInclusion("a", "ab"));
//        System.out.println(f.checkInclusion("ab", "ab"));
        System.out.println(f.checkInclusion("abc", "ccccbbbbaaaa"));
    }

}
