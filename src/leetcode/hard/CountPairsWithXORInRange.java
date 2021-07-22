package leetcode.hard;

/**
 * 1803. Count Pairs With XOR in a Range
 * Given a (0-indexed) integer array nums and two integers low and high, return the number of nice pairs.
 *
 * A nice pair is a pair (i, j) where 0 <= i < j < nums.length and low <= (nums[i] XOR nums[j]) <= high.
 *
 * Example 1:
 *
 * Input: nums = [1,4,2,7], low = 2, high = 6
 * Output: 6
 * Explanation: All nice pairs (i, j) are as follows:
 *     - (0, 1): nums[0] XOR nums[1] = 5
 *     - (0, 2): nums[0] XOR nums[2] = 3
 *     - (0, 3): nums[0] XOR nums[3] = 6
 *     - (1, 2): nums[1] XOR nums[2] = 6
 *     - (1, 3): nums[1] XOR nums[3] = 3
 *     - (2, 3): nums[2] XOR nums[3] = 5
 * Example 2:
 *
 * Input: nums = [9,8,4,2,1], low = 5, high = 14
 * Output: 8
 * Explanation: All nice pairs (i, j) are as follows:
 *     - (0, 2): nums[0] XOR nums[2] = 13
 *     - (0, 3): nums[0] XOR nums[3] = 11
 *     - (0, 4): nums[0] XOR nums[4] = 8
 *     - (1, 2): nums[1] XOR nums[2] = 12
 *     - (1, 3): nums[1] XOR nums[3] = 10
 *     - (1, 4): nums[1] XOR nums[4] = 9
 *     - (2, 3): nums[2] XOR nums[3] = 6
 *     - (2, 4): nums[2] XOR nums[4] = 5
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 2 * 104
 * 1 <= low <= high <= 2 * 104
 */
public class CountPairsWithXORInRange {

    Trie root;

    public int countPairs(int[] nums, int low, int high) {
        root = new Trie();
        int res = 0;
        for (int num : nums) {
            res += countSmaller(num, high + 1) - countSmaller(num, low);
            insert(num);
        }
        return res;
    }

    private int countSmaller(int num, int limit) {
        Trie curr = root;
        int count = 0;
        for (int i = 15; i >= 0; i--) {
            if (curr == null) break;
            int numBit = (num >> i) & 1;
            int limitBit = (limit >> i) & 1;
            if (limitBit == 1) {
                if (curr.children[numBit] != null) count += curr.children[numBit].count;
                curr = curr.children[numBit ^ 1];
            } else curr = curr.children[numBit];
        }
        return count;
    }

    private void insert(int num) {
        Trie curr = root;
        for (int i = 15; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (curr.children[bit] == null) curr.children[bit] = new Trie();
            curr.children[bit].count++;
            curr = curr.children[bit];
        }
    }

    public class Trie {
        Trie[] children;
        int count;

        public Trie() {
            children = new Trie[2];
            count = 0;
        }
    }

}
