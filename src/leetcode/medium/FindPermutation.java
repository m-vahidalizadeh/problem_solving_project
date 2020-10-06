package leetcode.medium;

import java.util.Arrays;

/**
 * 484. Find Permutation
 * By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing relationship between two numbers, 'I' represents an increasing relationship between two numbers. And our secret signature was constructed by a special integer array, which contains uniquely all the different number from 1 to n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string that can't represent the "DI" secret signature.
 * <p>
 * On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to the given secret signature in the input.
 * <p>
 * Example 1:
 * <p>
 * Input: "I"
 * Output: [1,2]
 * Explanation: [1,2] is the only legal initial spectial string can construct secret signature "I", where the number 1 and 2 construct an increasing relationship.
 * Example 2:
 * <p>
 * Input: "DI"
 * Output: [2,1,3]
 * Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI",
 * but since we want to find the one with the smallest lexicographical permutation, you need to output [2,1,3]
 * Note:
 * <p>
 * The input string will only contain the character 'D' and 'I'.
 * The length of input string is a positive integer and will not exceed 10,000
 */
public class FindPermutation {

    public int[] findPermutation(String s) {
        int n = s.length();
        int[] result = new int[n + 1];
        result[0] = 1;
        int i = 1;
        while (i <= n) {
            if (s.charAt(i - 1) == 'I') {
                result[i] = i + 1;
                i++;
            } else {
                int j = i - 1;
                int rem = i;
                while (i <= n && s.charAt(i - 1) == 'D') i++;
                int k = i - 1;
                for (int a = k; a >= j; a--) result[a] = rem++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindPermutation f = new FindPermutation();
        System.out.println(Arrays.toString(f.findPermutation("I")));
        System.out.println(Arrays.toString(f.findPermutation("DI")));
        System.out.println(Arrays.toString(f.findPermutation("D")));
    }

}
