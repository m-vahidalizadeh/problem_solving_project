package leetcode.hard;

import java.util.*;

/**
 * 1998. GCD Sort of an Array
 * You are given an integer array nums, and you can perform the following operation any number of times on nums:
 *
 * Swap the positions of two elements nums[i] and nums[j] if gcd(nums[i], nums[j]) > 1 where gcd(nums[i], nums[j]) is the greatest common divisor of nums[i] and nums[j].
 * Return true if it is possible to sort nums in non-decreasing order using the above swap method, or false otherwise.
 *
 * Example 1:
 *
 * Input: nums = [7,21,3]
 * Output: true
 * Explanation: We can sort [7,21,3] by performing the following operations:
 * - Swap 7 and 21 because gcd(7,21) = 7. nums = [21,7,3]
 * - Swap 21 and 3 because gcd(21,3) = 3. nums = [3,7,21]
 * Example 2:
 *
 * Input: nums = [5,2,6,2]
 * Output: false
 * Explanation: It is impossible to sort the array because 5 cannot be swapped with any other element.
 * Example 3:
 *
 * Input: nums = [10,5,9,3,15]
 * Output: true
 * We can sort [10,5,9,3,15] by performing the following operations:
 * - Swap 10 and 15 because gcd(10,15) = 5. nums = [15,5,9,3,10]
 * - Swap 15 and 3 because gcd(15,3) = 3. nums = [3,5,9,15,10]
 * - Swap 10 and 15 because gcd(10,15) = 5. nums = [3,5,9,10,15]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * 2 <= nums[i] <= 105
 */
public class GCDSortOfAnArray {

    public boolean gcdSort(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        int max = nums[0];
        for (int num : nums) {
            numSet.add(num);
            max = Math.max(max, num);
        }
        UF uf = new UF(nums, max, numSet);
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != sortedNums[i] && uf.find(nums[i]) != uf.find(sortedNums[i])) return false;
        }
        return true;
    }

    public class UF {
        int[] parents;

        public UF(int[] nums, int max, Set<Integer> numSet) {
            parents = new int[max + 1];
            for (int num : nums) {
                parents[num] = num;
            }
            boolean[] primes = new boolean[max + 1];
            Arrays.fill(primes, true);
            int p = 2; // the first prime
            while (p <= max) {
                if (primes[p]) {
                    int pre = 0;
                    for (int i = p; i <= max; i += p) {
                        if (numSet.contains(i)) {
                            if (pre > 0) union(pre, i);
                            pre = i;
                        }
                        primes[i] = false;
                    }
                }
                p++;
            }
        }

        public int find(int x) {
            while (parents[x] != x) x = parents[x];
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) parents[rootY] = rootX;
        }
    }

}
