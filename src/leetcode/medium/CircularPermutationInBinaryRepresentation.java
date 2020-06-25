package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Circular Permutation in Binary Representation
 * Given 2 integers n and start. Your task is return any permutation p of (0,1,2.....,2^n -1) such that :
 * <p>
 * p[0] = start
 * p[i] and p[i+1] differ by only one bit in their binary representation.
 * p[0] and p[2^n -1] must also differ by only one bit in their binary representation.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2, start = 3
 * Output: [3,2,0,1]
 * Explanation: The binary representation of the permutation is (11,10,00,01).
 * All the adjacent element differ by one bit. Another valid permutation is [3,1,0,2]
 * Example 2:
 * <p>
 * Input: n = 3, start = 2
 * Output: [2,6,7,5,4,0,1,3]
 * Explanation: The binary representation of the permutation is (010,110,111,101,100,000,001,011).
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 16
 * 0 <= start < 2 ^ n
 */
public class CircularPermutationInBinaryRepresentation {

    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) result.add(start ^ i ^ i >> 1);
        return result;
    }

}
