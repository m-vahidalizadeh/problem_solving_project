package leetcode.companies.google;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Campus Bikes
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.
 * <p>
 * Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.
 * <p>
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 * <p>
 * Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.
 * <p>
 * Example 1:
 * <p>
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: [1,0]
 * Explanation:
 * Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
 * Example 2:
 * <p>
 * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * Output: [0,2,1]
 * Explanation:
 * Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
 * <p>
 * Note:
 * <p>
 * 0 <= workers[i][j], bikes[i][j] < 1000
 * All worker and bike locations are distinct.
 * 1 <= workers.length <= bikes.length <= 1000
 */
public class AssignBikes {

    public class WorkerBike {
        int wId;
        int bId;
        int m;

        public WorkerBike(int wId, int wX, int wY, int bId, int bX, int bY) {
            this.wId = wId;
            this.bId = bId;
            this.m = Math.abs(wX - bX) + Math.abs(wY - bY);
        }
    }

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        PriorityQueue<WorkerBike> pq = new PriorityQueue<>((a, b) -> a.m == b.m ? a.wId == b.wId ? a.bId - b.bId : a.wId - b.wId : a.m - b.m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pq.add(new WorkerBike(i, workers[i][0], workers[i][1], j, bikes[j][0], bikes[j][1]));
            }
        }
        int[] result = new int[n];
        Set<Integer> aW = new HashSet<>();
        Set<Integer> aB = new HashSet<>();
        int i = 0;
        while (i < n) {
            WorkerBike wb = pq.poll();
            if (aW.contains(wb.wId) || aB.contains(wb.bId)) continue;
            aW.add(wb.wId);
            aB.add(wb.bId);
            result[wb.wId] = wb.bId;
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        AssignBikes a = new AssignBikes();
        System.out.println(Arrays.toString(a.assignBikes(new int[][]{{0, 0}, {2, 1}}, new int[][]{{1, 2}, {3, 3}})));
        System.out.println(Arrays.toString(a.assignBikes(new int[][]{{0, 0}, {1, 1}, {2, 0}}, new int[][]{{1, 0}, {2, 2}, {2, 1}})));
    }

}
