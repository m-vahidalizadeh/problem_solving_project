package leetcode.companies.google;

import java.util.*;

/**
 * 711. Number of Distinct Islands II
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).
 *
 * Return the number of distinct islands.
 *
 * Example 1:
 *
 * Input: grid = [[1,1,0,0,0],[1,0,0,0,0],[0,0,0,0,1],[0,0,0,1,1]]
 * Output: 1
 * Explanation: The two islands are considered the same because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
 * Example 2:
 *
 * Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
 * Output: 1
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 */
public class NumOfDistinctIslandsII {

    int[][] directions;
    int[][] transformations;
    int n;
    int m;

    public int numDistinctIslands2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        transformations = new int[][]{{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        n = grid.length;
        m = grid[0].length;
        Set<String> islands = new HashSet<>();
        List<Point> shape = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, shape);
                    islands.add(normalize(shape));
                    shape.clear();
                }
            }
        }
        return islands.size();
    }

    private void bfs(int[][] grid, int row, int col, List<Point> shape) {
        Queue<Point> q = new ArrayDeque<>();
        Point start = new Point(row, col);
        shape.add(start);
        q.add(start);
        grid[row][col] = 2;
        while (!q.isEmpty()) {
            Point point = q.poll();
            for (int[] dir : directions) {
                int newRow = point.x + dir[0];
                int newCol = point.y + dir[1];
                if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m || grid[newRow][newCol] != 1) continue;
                Point newPoint = new Point(newRow, newCol);
                shape.add(newPoint);
                q.add(newPoint);
                grid[newRow][newCol] = 2;
            }
        }
    }

    private String normalize(List<Point> shape) {
        List<Point>[] transformedShapes = new ArrayList[8];
        for (int i = 0; i < 4; i++) {
            transformedShapes[i] = new ArrayList<>();
            transformedShapes[i + 4] = new ArrayList<>();
            for (Point point : shape) {
                transformedShapes[i].add(new Point(point.x * transformations[i][0], point.y * transformations[i][1]));
                transformedShapes[i + 4].add(new Point(point.y * transformations[i][1], point.x * transformations[i][0]));
            }
        }
        for (int i = 0; i < 8; i++) {
            Collections.sort(transformedShapes[i], (a, b) -> a.x == b.x ? a.y - b.y : a.x - b.x);
        }
        String[] newShapes = new String[8];
        for (int i = 0; i < 8; i++) {
            StringBuilder newShape = new StringBuilder();
            int x0 = transformedShapes[i].get(0).x;
            int y0 = transformedShapes[i].get(0).y;
            for (Point point : transformedShapes[i]) {
                newShape.append(point.x - x0).append(",").append(point.y - y0).append(";");
            }
            newShapes[i] = newShape.toString();
        }
        Arrays.sort(newShapes);
        return newShapes[0];
    }

    public class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
