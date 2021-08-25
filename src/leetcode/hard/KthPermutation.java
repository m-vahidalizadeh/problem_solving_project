package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. Permutation Sequence
 * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
 *
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 *
 * Example 1:
 *
 * Input: n = 3, k = 3
 * Output: "213"
 * Example 2:
 *
 * Input: n = 4, k = 9
 * Output: "2314"
 * Example 3:
 *
 * Input: n = 3, k = 1
 * Output: "123"
 *
 * Constraints:
 *
 * 1 <= n <= 9
 * 1 <= k <= n!
 */
public class KthPermutation {

    public String getPermutation(int n, int k) {
        StringBuilder res = new StringBuilder();
        List<Integer> digits = new ArrayList<>();
        int[] fact = new int[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i;
        for (int i = 1; i <= n; i++) digits.add(i);
        for (int i = 1; i <= n; i++) {
            int index = 0;
            int count = fact[n - i]; // these are all the permutations smaller than this one
            while (k > count) {
                index++;
                k -= count;
            }
            res.append(digits.get(index) + "");
            digits.remove(digits.get(index));
        }
        return res.toString();
    }

}
