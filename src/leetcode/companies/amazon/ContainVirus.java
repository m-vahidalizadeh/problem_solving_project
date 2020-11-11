package leetcode.companies.amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 749. Contain Virus
 * A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.
 *
 * The world is modeled as a 2-D array of cells, where 0 represents uninfected cells, and 1 represents cells contaminated with the virus. A wall (and only one wall) can be installed between any two 4-directionally adjacent cells, on the shared boundary.
 *
 * Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall. Resources are limited. Each day, you can install walls around only one region -- the affected area (continuous block of infected cells) that threatens the most uninfected cells the following night. There will never be a tie.
 *
 * Can you save the day? If so, what is the number of walls required? If not, and the world becomes fully infected, return the number of walls used.
 *
 * Example 1:
 *
 * Input: grid =
 * [[0,1,0,0,0,0,0,1],
 *  [0,1,0,0,0,0,0,1],
 *  [0,0,0,0,0,0,0,1],
 *  [0,0,0,0,0,0,0,0]]
 * Output: 10
 * Explanation:
 * There are 2 contaminated regions.
 * On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:
 *
 * [[0,1,0,0,0,0,1,1],
 *  [0,1,0,0,0,0,1,1],
 *  [0,0,0,0,0,0,1,1],
 *  [0,0,0,0,0,0,0,1]]
 *
 * On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.
 * Example 2:
 *
 * Input: grid =
 * [[1,1,1],
 *  [1,0,1],
 *  [1,1,1]]
 * Output: 4
 * Explanation: Even though there is only one cell saved, there are 4 walls built.
 * Notice that walls are only built on the shared boundary of two different cells.
 * Example 3:
 *
 * Input: grid =
 * [[1,1,1,0,0,0,0,0,0],
 *  [1,0,1,0,1,1,1,1,1],
 *  [1,1,1,0,0,0,0,0,0]]
 * Output: 13
 * Explanation: The region on the left only builds two new walls.
 * Note:
 *
 * The number of rows and columns of grid will each be in the range [1, 50].
 * Each grid[i][j] will be either 0 or 1.
 * Throughout the described process, there is always a contiguous viral region that will infect strictly more uncontaminated squares in the next round.
 */
public class ContainVirus {

    public class NeighborAndPerimeter {
        int perimeter;
        Set<Integer> infected;
        Set<Integer> notInfected;

        public NeighborAndPerimeter() {
            this.perimeter = 0;
            this.infected = new HashSet<>();
            this.notInfected = new HashSet<>();
        }
    }

    private int[][] directions;
    private int n;
    private int m;

    public int containVirus(int[][] grid) {
        if (grid == null) return 0;
        directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        int wallCount = 0;
        while (true) {
            List<NeighborAndPerimeter> regionNeighbors = new ArrayList<>();
            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++) { // Find regions
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && grid[i][j] == 1) {
                        NeighborAndPerimeter neighborAndPerimeter = new NeighborAndPerimeter();
                        dfs(grid, i, j, visited, neighborAndPerimeter);
                        if (!neighborAndPerimeter.notInfected.isEmpty()) regionNeighbors.add(neighborAndPerimeter);
                    }
                }
            }
            if (regionNeighbors.isEmpty()) break; // No more region
            regionNeighbors.sort((a, b) -> b.notInfected.size() - a.notInfected.size()); // Sort by most potential infection
            NeighborAndPerimeter theRegion = regionNeighbors.remove(0); // Remove the most infectious region
            wallCount += theRegion.perimeter; // Build wall
            for (int cell : theRegion.infected) grid[cell / m][cell % m] = 2; // Contain virus
            for (NeighborAndPerimeter region : regionNeighbors) { // for the rest of the regions
                for (int cell : region.notInfected) grid[cell / m][cell % m] = 1; // Spread the virus
            }
        }
        return wallCount;
    }

    private void dfs(int[][] grid, int i, int j, boolean[][] visited, NeighborAndPerimeter neighborAndPerimeter) {
        if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == 2) return;
        int newCell = i * m + j;
        if (grid[i][j] == 1) {
            neighborAndPerimeter.infected.add(newCell);
            if (!visited[i][j]) {
                visited[i][j] = true;
                for (int[] dir : directions) dfs(grid, i + dir[0], j + dir[1], visited, neighborAndPerimeter);
            }
        } else {
            neighborAndPerimeter.notInfected.add(newCell);
            neighborAndPerimeter.perimeter++;
        }
    }

    public static void main(String[] args) {
        ContainVirus c = new ContainVirus();
        System.out.println(c.containVirus(new int[][]
                {{0, 1, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0}}
        ));
        System.out.println(c.containVirus(new int[][]
                {{1, 1, 1},
                        {1, 0, 1},
                        {1, 1, 1}}
        ));
        System.out.println(c.containVirus(new int[][]
                {{1, 1, 1, 0, 0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 1, 1, 1, 1, 1},
                        {1, 1, 1, 0, 0, 0, 0, 0, 0}}
        ));
    }

}
