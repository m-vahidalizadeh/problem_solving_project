package leetcode.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1714. Sum Of Special Evenly-Spaced Elements In Array
 * You are given a 0-indexed integer array nums consisting of n non-negative integers.
 *
 * You are also given an array queries, where queries[i] = [xi, yi]. The answer to the ith query is the sum of all nums[j] where xi <= j < n and (j - xi) is divisible by yi.
 *
 * Return an array answer where answer.length == queries.length and answer[i] is the answer to the ith query modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: nums = [0,1,2,3,4,5,6,7], queries = [[0,3],[5,1],[4,2]]
 * Output: [9,18,10]
 * Explanation: The answers of the queries are as follows:
 * 1) The j indices that satisfy this query are 0, 3, and 6. nums[0] + nums[3] + nums[6] = 9
 * 2) The j indices that satisfy this query are 5, 6, and 7. nums[5] + nums[6] + nums[7] = 18
 * 3) The j indices that satisfy this query are 4 and 6. nums[4] + nums[6] = 10
 * Example 2:
 *
 * Input: nums = [100,200,101,201,102,202,103,203], queries = [[0,7]]
 * Output: [303]
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5 * 104
 * 0 <= nums[i] <= 109
 * 1 <= queries.length <= 1.5 * 105
 * 0 <= xi < n
 * 1 <= yi <= 5 * 104
 */
public class SumOfSpecialEvenlySpacedElementsInArray {

    public int[] solve(int[] nums, int[][] queries) {
        int MOD = 1_000_000_007;
        int n = nums.length;
        int m = queries.length;
        Map<Integer, PriorityQueue<int[]>> startToQueriesMap = new HashMap<>(); // start -> pq(x,y,index)
        for (int i = 0; i < m; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            int start = (y << 10) + (x % y); // To make sure that these queries have equals y and their start is the same
            startToQueriesMap.computeIfAbsent(start, z -> new PriorityQueue<>((a, b) -> b[0] - a[0]));
            startToQueriesMap.get(start).add(new int[]{x, y, i}); // ex. 1)3x+4 and 2)3x+7, They have the same start
        }
        int[] res = new int[m];
        for (PriorityQueue<int[]> pq : startToQueriesMap.values()) {
            long sum = 0;
            int limit = n; // after limit is already part of sum, let's just calculate from x to limit
            while (!pq.isEmpty()) {
                int[] query = pq.poll();
                int x = query[0];
                int y = query[1];
                int i = query[2];
                for (int j = x; j < limit; j += y) sum += nums[j];
                res[i] = (int) (sum % MOD);
                limit = x;
            }
        }
        return res;
    }

}
