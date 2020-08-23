package leetcode.companies.google;

/**
 * Longest Increasing Path in a Matrix
 * Given an integer matrix, find the length of the longest increasing path.
 * <p>
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * <p>
 * Example 1:
 * <p>
 * Input: nums =
 * [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 * <p>
 * Input: nums =
 * [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathInMatrix {

    private Direction[] directions = new Direction[]{
            new Direction(0, 1), new Direction(1, 0), new Direction(0, -1), new Direction(-1, 0)
    };

    public class Direction {
        int i;
        int j;

        public Direction(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int max = 0;
        int[][] memory = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfs(matrix, i, j, memory));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] memory) {
        if (memory[i][j] != 0) return memory[i][j];
        int max = 0;
        for (Direction d : directions) {
            int newI = i + d.i;
            int newJ = j + d.j;
            if (newI >= 0 && newI < matrix.length && newJ >= 0 && newJ < matrix[0].length && matrix[i][j] < matrix[newI][newJ])
                max = Math.max(max, dfs(matrix, newI, newJ, memory));
        }
        memory[i][j] = 1 + max;
        return memory[i][j];
    }

    public static void main(String[] args) {
        LongestIncreasingPathInMatrix l = new LongestIncreasingPathInMatrix();
        int[][] nums1 =
                {
                        {9, 9, 4},
                        {6, 6, 8},
                        {2, 1, 1}
                };
        System.out.println(l.longestIncreasingPath(nums1));
        int[][] nums2 =
                {
                        {3, 4, 5},
                        {3, 2, 6},
                        {2, 2, 1}
                };
        System.out.println(l.longestIncreasingPath(nums2));
    }

}
