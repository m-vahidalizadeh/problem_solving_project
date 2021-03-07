package leetcode.companies.microsoft;

import java.util.*;

/**
 * 296. Best Meeting Point
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * Example:
 *
 * Input:
 *
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * Output: 6
 *
 * Explanation: Given three people living at (0,0), (0,4), and (2,2):
 *              The point (0,2) is an ideal meeting point, as the total travel distance
 *              of 2+2+2=6 is minimal. So return 6.
 */
public class MinTotalDistance {

    public int minTotalDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        List<int[]> group = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) group.add(new int[]{i, j});
            }
        }
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long sumDist = getSumdist(group, i, j);
                min = Math.min(min, sumDist);
            }
        }
        return Long.valueOf(min).intValue();
    }

    long getSumdist(List<int[]> group, int i, int j) {
        long sum = 0;
        for (int[] person : group) sum += Math.abs(i - person[0]) + Math.abs(j - person[1]);
        return sum;
    }

    public static void main(String[] args) {
        MinTotalDistance m = new MinTotalDistance();
        System.out.println(m.minTotalDistance(new int[][]{{1, 1}}));
    }

}
