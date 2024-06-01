package leetcode.companies.facebook;

import java.util.*;

/**
 * 1439. Find the Kth Smallest Sum of a Matrix With Sorted Rows
 * Solved
 * Hard
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * You are given an m x n matrix mat that has its rows sorted in non-decreasing order and an integer k.
 *
 * You are allowed to choose exactly one element from each row to form an array.
 *
 * Return the kth smallest array sum among all possible arrays.
 *
 * Example 1:
 *
 * Input: mat = [[1,3,11],[2,4,6]], k = 5
 * Output: 7
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.
 * Example 2:
 *
 * Input: mat = [[1,3,11],[2,4,6]], k = 9
 * Output: 17
 * Example 3:
 *
 * Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * Output: 9
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat.length[i]
 * 1 <= m, n <= 40
 * 1 <= mat[i][j] <= 5000
 * 1 <= k <= min(200, n^m)
 * mat[i] is a non-decreasing array.
 */
public class KthSmallestArraySum {

    public int kthSmallest(int[][] mat, int k) {
        int[] row = mat[0];
        for (int i = 1; i < mat.length; i++) {
            row = findKthSmallest(row, mat[i], k);
        }
        return row[k - 1];
    }

    private int[] findKthSmallest(int[] num1, int[] num2, int k) {
        List<Integer> list = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
        for (int i = 0; i < num1.length && i < k; i++) minHeap.add(new int[]{num1[i], num2[0], 0});
        for (int i = 0; i < k && !minHeap.isEmpty(); i++) {
            int[] candidate = minHeap.poll();
            list.add(candidate[0] + candidate[1]);
            int num2Idx = candidate[2];
            if (num2Idx < num2.length - 1) minHeap.add(new int[]{candidate[0], num2[num2Idx + 1], num2Idx + 1});
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

}
