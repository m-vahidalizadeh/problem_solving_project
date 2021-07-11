package leetcode.hard;

/**
 * 1210. Minimum Moves to Reach Target with Rotations
 * In an n*n grid, there is a snake that spans 2 cells and starts moving from the top left corner at (0, 0) and (0, 1). The grid has empty cells represented by zeros and blocked cells represented by ones. The snake wants to reach the lower right corner at (n-1, n-2) and (n-1, n-1).
 *
 * In one move the snake can:
 *
 * Move one cell to the right if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
 * Move down one cell if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
 * Rotate clockwise if it's in a horizontal position and the two cells under it are both empty. In that case the snake moves from (r, c) and (r, c+1) to (r, c) and (r+1, c).
 *
 * Rotate counterclockwise if it's in a vertical position and the two cells to its right are both empty. In that case the snake moves from (r, c) and (r+1, c) to (r, c) and (r, c+1).
 *
 * Return the minimum number of moves to reach the target.
 *
 * If there is no way to reach the target, return -1.
 *
 * Example 1:
 *
 * Input: grid = [[0,0,0,0,0,1],
 *                [1,1,0,0,1,0],
 *                [0,0,0,0,1,1],
 *                [0,0,1,0,1,0],
 *                [0,1,1,0,0,0],
 *                [0,1,1,0,0,0]]
 * Output: 11
 * Explanation:
 * One possible solution is [right, right, rotate clockwise, right, down, down, down, down, rotate counterclockwise, right, down].
 * Example 2:
 *
 * Input: grid = [[0,0,1,1,1,1],
 *                [0,0,0,0,1,1],
 *                [1,1,0,0,0,1],
 *                [1,1,1,0,0,1],
 *                [1,1,1,0,0,1],
 *                [1,1,1,0,0,0]]
 * Output: 9
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * 0 <= grid[i][j] <= 1
 * It is guaranteed that the snake starts at empty cells.
 */
public class MinSnakeMoves {

    public int minimumMoves(int[][] grid) { // DP bottom up
        int n = grid.length;
        int[][][] dp = new int[n][n][2]; // row, column, 0 for -- horizontal and 1 for | vertical
        for (int i = 0; i < n; i++) { // build cases bottom to top
            for (int j = 0; j < n; j++) { // build cases bottom to top
                for (int k = 0; k < 2; k++) { // vertical or horizontal, | or --, 0 or 1
                    if (i == 0 && j == 0 && k == 0) dp[i][j][k] = 0; // start of the game- feasible
                    else if (grid[i][j] == 1) dp[i][j][k] = Integer.MAX_VALUE; // blocked cell- not feasible
                    else if (k == 0 && (j + 1 >= n || grid[i][j + 1] == 1))
                        dp[i][j][k] = Integer.MAX_VALUE; // head of snake can't be in blocked cell (snake --).
                    else if (k == 1 && (i + 1 >= n || grid[i + 1][j] == 1))
                        dp[i][j][k] = Integer.MAX_VALUE; // head of snake can't be in blocked cell (snake |).
                    else { // The move with tail snake in (i,j) is a feasible move
                        int vertical = i == 0 ? Integer.MAX_VALUE : dp[i - 1][j][k]; // Move vertically |
                        int horizontal = j == 0 ? Integer.MAX_VALUE : dp[i][j - 1][k]; // Move horizontally --
                        if (vertical == Integer.MAX_VALUE && horizontal == Integer.MAX_VALUE) // no feasible move
                            dp[i][j][k] = Integer.MAX_VALUE;
                        else
                            dp[i][j][k] = Math.min(vertical, horizontal) + 1; // Move to the min +1 is the cost of curr move
                    }
                } // It is possible that we could do the same move without move and just with rotation. Let's check.
                if (i + 1 < n && j + 1 < n && grid[i][j + 1] != 1 && grid[i + 1][j + 1] != 1 && dp[i][j][1] != Integer.MAX_VALUE)
                    dp[i][j][0] = Math.min(dp[i][j][0], dp[i][j][1] + 1); // rotate from | to -- or 1 to 0
                if (i + 1 < n && j + 1 < n && grid[i + 1][j] != 1 && grid[i + 1][j + 1] != 1 && dp[i][j][0] != Integer.MAX_VALUE)
                    dp[i][j][1] = Math.min(dp[i][j][1], dp[i][j][0] + 1); // rotate from - to | -- or 0 to 1
            }
        } // At the end this should be the snake state: tail: (n-1,n-2) head: (n-1,n-1) direction: 0 or --
        return dp[n - 1][n - 2][0] == Integer.MAX_VALUE ? -1 : dp[n - 1][n - 2][0]; // At the end the tail of snake is at (n-1,n-2) dir -- or 0
    }

}
