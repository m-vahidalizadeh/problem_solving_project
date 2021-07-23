package leetcode.hard;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1851. Minimum Interval to Include Each Query
 * You are given a 2D integer array intervals, where intervals[i] = [lefti, righti] describes the ith interval starting at lefti and ending at righti (inclusive). The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.
 *
 * You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i such that lefti <= queries[j] <= righti. If no such interval exists, the answer is -1.
 *
 * Return an array containing the answers to the queries.
 *
 * Example 1:
 *
 * Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * Output: [3,3,1,4]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
 * - Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
 * - Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
 * - Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.
 * Example 2:
 *
 * Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * Output: [2,-1,4,6]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.
 * - Query = 19: None of the intervals contain 19. The answer is -1.
 * - Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.
 * - Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 = 6.
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 105
 * 1 <= queries.length <= 105
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 107
 * 1 <= queries[j] <= 107
 */
public class MinIntervalToIncludeQuery {

    public int[] minInterval(int[][] intervals, int[] queries) {
        int[][] sortedQueries = new int[queries.length][2]; // num, index
        int index = 0; // index of queries
        for (int i = 0; i < queries.length; i++) {
            sortedQueries[index][0] = queries[i];
            sortedQueries[index++][1] = i;
        }
        Arrays.sort(sortedQueries, (a, b) -> a[0] - b[0]);
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // size, right
        int j = 0; // index of intervals
        int[] res = new int[queries.length];
        for (int[] query : sortedQueries) {
            int num = query[0];
            int qIndex = query[1];
            while (j < intervals.length && intervals[j][0] <= num) {
                int l = intervals[j][0];
                int r = intervals[j][1];
                if (r >= num)
                    pq.add(new int[]{r - l + 1, r});
                j++;
            }
            while (!pq.isEmpty() && pq.peek()[1] < num) pq.poll();
            res[qIndex] = pq.isEmpty() ? -1 : pq.peek()[0];
        }
        return res;
    }

}
