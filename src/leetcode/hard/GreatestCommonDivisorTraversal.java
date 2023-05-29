package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 2709. Greatest Common Divisor Traversal
 * You are given a 0-indexed integer array nums, and you are allowed to traverse between its indices. You can traverse between index i and index j, i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is the greatest common divisor.
 *
 * Your task is to determine if for every pair of indices i and j in nums, where i < j, there exists a sequence of traversals that can take us from i to j.
 *
 * Return true if it is possible to traverse between all such pairs of indices, or false otherwise.
 *
 * Example 1:
 *
 * Input: nums = [2,3,6]
 * Output: true
 * Explanation: In this example, there are 3 possible pairs of indices: (0, 1), (0, 2), and (1, 2).
 * To go from index 0 to index 1, we can use the sequence of traversals 0 -> 2 -> 1, where we move from index 0 to index 2 because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1, and then move from index 2 to index 1 because gcd(nums[2], nums[1]) = gcd(6, 3) = 3 > 1.
 * To go from index 0 to index 2, we can just go directly because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1. Likewise, to go from index 1 to index 2, we can just go directly because gcd(nums[1], nums[2]) = gcd(3, 6) = 3 > 1.
 * Example 2:
 *
 * Input: nums = [3,9,5]
 * Output: false
 * Explanation: No sequence of traversals can take us from index 0 to index 2 in this example. So, we return false.
 * Example 3:
 *
 * Input: nums = [4,3,12,8]
 * Output: true
 * Explanation: There are 6 possible pairs of indices to traverse between: (0, 1), (0, 2), (0, 3), (1, 2), (1, 3), and (2, 3). A valid sequence of traversals exists for each pair, so we return true.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class GreatestCommonDivisorTraversal {

    public boolean canTraverseAllPairs(int[] nums) {
        int n = nums.length;
        UnionFind u = new UnionFind(n);
        Map<Integer, Integer> numToIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int p = 2; p * p <= nums[i]; p++) {
                if (nums[i] % p != 0) continue;
                if (numToIndexMap.containsKey(p)) u.merge(numToIndexMap.get(p), i);
                else numToIndexMap.put(p, i);
                while (nums[i] % p == 0) nums[i] /= p;
            }
            if (nums[i] > 1) {
                if (numToIndexMap.containsKey(nums[i])) u.merge(numToIndexMap.get(nums[i]), i);
                else numToIndexMap.put(nums[i], i);
            }
        }
        return u.size[u.getFa(0)] == n;
    }

    static class UnionFind {
        public int[] parent;
        public int[] size;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int getFa(int i) {
            return i == parent[i] ? i : (parent[i] = getFa(parent[i]));
        }

        public void merge(int i, int j) {
            int fi = getFa(i), fj = getFa(j);
            if (fi == fj) return;
            if (size[fi] > size[fj]) {
                size[fi] += size[fj];
                parent[fj] = fi;
            } else {
                size[fj] += size[fi];
                parent[fi] = fj;
            }
        }
    }

}
