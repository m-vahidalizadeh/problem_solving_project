package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1707. Maximum XOR With an Element From Array
 * You are given an array nums consisting of non-negative integers. You are also given a queries array, where queries[i] = [xi, mi].
 *
 * The answer to the ith query is the maximum bitwise XOR value of xi and any element of nums that does not exceed mi. In other words, the answer is max(nums[j] XOR xi) for all j such that nums[j] <= mi. If all elements in nums are larger than mi, then the answer is -1.
 *
 * Return an integer array answer where answer.length == queries.length and answer[i] is the answer to the ith query.
 *
 * Example 1:
 *
 * Input: nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
 * Output: [3,3,7]
 * Explanation:
 * 1) 0 and 1 are the only two integers not greater than 1. 0 XOR 3 = 3 and 1 XOR 3 = 2. The larger of the two is 3.
 * 2) 1 XOR 2 = 3.
 * 3) 5 XOR 2 = 7.
 * Example 2:
 *
 * Input: nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
 * Output: [15,-1,5]
 *
 * Constraints:
 *
 * 1 <= nums.length, queries.length <= 105
 * queries[i].length == 2
 * 0 <= nums[j], xi, mi <= 109
 */
public class MaxXORWithAnElement {

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int[] res = new int[queries.length];
        Arrays.fill(res, -1);
        Arrays.sort(nums);
        List<int[]> queryList = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) queryList.add(new int[]{i, queries[i][0], queries[i][1]}); // i, x, m
        queryList.sort((a, b) -> a[2] - b[2]);
        int j = 0;
        Trie trie = new Trie();
        for (int[] query : queryList) {
            int i = query[0];
            int x = query[1];
            int m = query[2];
            while (j < nums.length && nums[j] <= m) trie.insert(nums[j++]);
            if (j > 0) res[i] = trie.query(x);
        }
        return res;
    }

    public class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(int num) {
            Node curr = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (curr.children[bit] == null) curr.children[bit] = new Node();
                curr = curr.children[bit];
            }
        }

        public int query(int num) {
            Node curr = root;
            int res = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                int target = bit ^ 1;
                if (curr.children[target] != null) {
                    res += (1 << i);
                    curr = curr.children[target];
                } else curr = curr.children[bit];
            }
            return res;
        }

        private class Node {
            Node[] children;

            public Node() {
                children = new Node[2]; // 0 or 1 binary
            }
        }
    }

}
