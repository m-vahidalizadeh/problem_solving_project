package leetcode.hard;

import java.util.Arrays;

/**
 * 1289. Minimum Falling Path Sum II
 * Given a square grid of integers arr, a falling path with non-zero shifts is a choice of exactly one element from each row of arr, such that no two elements chosen in adjacent rows are in the same column.
 *
 * Return the minimum sum of a falling path with non-zero shifts.
 *
 * Example 1:
 *
 * Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 13
 * Explanation:
 * The possible falling paths are:
 * [1,5,9], [1,5,7], [1,6,7], [1,6,8],
 * [2,4,8], [2,4,9], [2,6,7], [2,6,8],
 * [3,4,8], [3,4,9], [3,5,7], [3,5,9]
 * The falling path with the smallest sum is [1,5,7], so the answer is 13.
 *
 * Constraints:
 *
 * 1 <= arr.length == arr[i].length <= 200
 * -99 <= arr[i][j] <= 99
 */
public class MinFallingPathSum {

    public class Element {
        int index;
        int val;

        public Element(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        Element[][] dp = new Element[n][n];
        for (int j = 0; j < n; j++) dp[0][j] = new Element(j, arr[0][j]);
        Arrays.sort(dp[0], (a, b) -> a.val - b.val);
        for (int row = 1; row < n; row++) {
            for (int currCol = 0; currCol < n; currCol++) {
                for (Element element : dp[row - 1]) {
                    if (element.index == currCol) continue;
                    dp[row][currCol] = new Element(currCol, element.val + arr[row][currCol]);
                    break;
                }
            }
            Arrays.sort(dp[row], (a, b) -> a.val - b.val);
        }
        int min = dp[n - 1][0].val;
        for (int j = 0; j < n; j++) min = Math.min(min, dp[n - 1][j].val);
        return min;
    }

    public static void main(String[] args) {
        MinFallingPathSum m = new MinFallingPathSum();
        System.out.println(m.minFallingPathSum(new int[][]
                {
                        {2, 2, 1, 2, 2},
                        {2, 2, 1, 2, 2},
                        {2, 2, 1, 2, 2},
                        {2, 2, 1, 2, 2},
                        {2, 2, 1, 2, 2}
                }
        ));
    }

}
