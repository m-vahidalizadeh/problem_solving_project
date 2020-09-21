package leetcode.hard;

/**
 * Unique Paths III
 * On a 2-dimensional grid, there are 4 types of squares:
 * <p>
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * Example 2:
 * <p>
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * Example 3:
 * <p>
 * Input: [[0,1],[2,0]]
 * Output: 0
 * Explanation:
 * There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 * <p>
 * Note:
 * <p>
 * 1 <= grid.length * grid[0].length <= 20
 */
public class UniquePaths3 {

    int[][] directions;
    int nonObstacles;
    int n;
    int m;

    public int uniquePathsIII(int[][] grid) {
        nonObstacles = 0;
        n = grid.length;
        m = grid[0].length;
        directions = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        int startX = 0, startY = 0, endX = 0, endY = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] >= 0) {
                    nonObstacles++;
                    if (grid[i][j] == 1) {
                        startX = i;
                        startY = j;
                    } else if (grid[i][j] == 2) {
                        endX = i;
                        endY = j;
                    }
                }
            }
        }
        return dfs(grid, startX, startY, endX, endY, 0);
    }

    private int dfs(int[][] grid, int currX, int currY, int endX, int endY, int visited) {
        if (currX < 0 || currX >= n || currY < 0 || currY >= m || grid[currX][currY] == -1) return 0;
        if (visited == nonObstacles - 1) {
            if (currX == endX && currY == endY) return 1;
            else return 0;
        }
        int temp = grid[currX][currY];
        grid[currX][currY] = -1;
        int sum = 0;
        for (int[] direction : directions) {
            sum += dfs(grid, currX + direction[0], currY + direction[1], endX, endY, visited + 1);
        }
        grid[currX][currY] = temp;
        return sum;
    }

    public static void main(String[] args) {
        UniquePaths3 u = new UniquePaths3();
        System.out.println(u.uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}));
        System.out.println(u.uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}}));
        System.out.println(u.uniquePathsIII(new int[][]{{0, 1}, {2, 0}}));
    }

}
