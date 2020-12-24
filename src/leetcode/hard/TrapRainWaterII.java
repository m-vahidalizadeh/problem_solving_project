package leetcode.hard;

import java.util.PriorityQueue;

/**
 * 407. Trapping Rain Water II
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.
 *
 * Example:
 *
 * Given the following 3x6 height map:
 * [
 *   [1,4,3,1,3,2],
 *   [3,2,1,3,2,4],
 *   [2,3,3,2,3,1]
 * ]
 *
 * Return 4.
 *
 * The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.
 *
 * After the rain, water is trapped between the blocks. The total volume of water trapped is 4.
 *
 * Constraints:
 *
 * 1 <= m, n <= 110
 * 0 <= heightMap[i][j] <= 20000
 */
public class TrapRainWaterII {

    public class Cell {
        int x;
        int y;
        int h;

        public Cell(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int n = heightMap.length;
        int m = heightMap[0].length;
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<Cell> heap = new PriorityQueue<>((a, b) -> a.h - b.h);
        for (int i = 0; i < n; i++) {
            visited[i][0] = true;
            heap.add(new Cell(i, 0, heightMap[i][0]));
            visited[i][m - 1] = true;
            heap.add(new Cell(i, m - 1, heightMap[i][m - 1]));
        }
        for (int j = 0; j < m; j++) {
            visited[0][j] = true;
            heap.add(new Cell(0, j, heightMap[0][j]));
            visited[n - 1][j] = true;
            heap.add(new Cell(n - 1, j, heightMap[n - 1][j]));
        }
        int trapped = 0;
        while (!heap.isEmpty()) {
            Cell curr = heap.poll();
            for (int[] dir : directions) {
                int newX = curr.x + dir[0];
                int newY = curr.y + dir[1];
                if (0 < newX && newX < n - 1 && 0 < newY && newY <= m - 1 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    trapped += Math.max(0, curr.h - heightMap[newX][newY]);
                    heap.add(new Cell(newX, newY, Math.max(curr.h, heightMap[newX][newY])));
                }
            }
        }
        return trapped;
    }

    public static void main(String[] args) {
        TrapRainWaterII t = new TrapRainWaterII();
        System.out.println(t.trapRainWater(new int[][]{
                {12, 13, 1, 12},
                {13, 4, 13, 12},
                {13, 8, 10, 12},
                {12, 13, 12, 12},
                {13, 13, 13, 13}
        }));
    }

}
