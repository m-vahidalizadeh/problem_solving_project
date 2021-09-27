package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 1840. Maximum Building Height
 * You want to build n new buildings in a city. The new buildings will be built in a line and are labeled from 1 to n.
 *
 * However, there are city restrictions on the heights of the new buildings:
 *
 * The height of each building must be a non-negative integer.
 * The height of the first building must be 0.
 * The height difference between any two adjacent buildings cannot exceed 1.
 * Additionally, there are city restrictions on the maximum height of specific buildings. These restrictions are given as a 2D integer array restrictions where restrictions[i] = [idi, maxHeighti] indicates that building idi must have a height less than or equal to maxHeighti.
 *
 * It is guaranteed that each building will appear at most once in restrictions, and building 1 will not be in restrictions.
 *
 * Return the maximum possible height of the tallest building.
 *
 * Example 1:
 *
 * Input: n = 5, restrictions = [[2,1],[4,1]]
 * Output: 2
 * Explanation: The green area in the image indicates the maximum allowed height for each building.
 * We can build the buildings with heights [0,1,2,1,2], and the tallest building has a height of 2.
 * Example 2:
 *
 * Input: n = 6, restrictions = []
 * Output: 5
 * Explanation: The green area in the image indicates the maximum allowed height for each building.
 * We can build the buildings with heights [0,1,2,3,4,5], and the tallest building has a height of 5.
 * Example 3:
 *
 * Input: n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
 * Output: 5
 * Explanation: The green area in the image indicates the maximum allowed height for each building.
 * We can build the buildings with heights [0,1,2,3,3,4,4,5,4,3], and the tallest building has a height of 5.
 *
 * Constraints:
 *
 * 2 <= n <= 109
 * 0 <= restrictions.length <= min(n - 1, 105)
 * 2 <= idi <= n
 * idi is unique.
 * 0 <= maxHeighti <= 109
 */
public class MaxBuildingHeight {

    public int maxBuilding(int n, int[][] restrictions) {
        if (restrictions.length == 0) return n - 1;
        List<int[]> restList = new ArrayList<>();
        restList.add(new int[]{1, 0});
        for (int[] rest : restrictions) restList.add(rest);
        restList.sort((a, b) -> a[0] - b[0]);
        for (int i = 1; i < restList.size(); i++) {
            int[] prevItem = restList.get(i - 1);
            int id1 = prevItem[0];
            int h1 = prevItem[1];
            int[] item = restList.get(i);
            int id2 = item[0];
            int h2 = item[1];
            int dist = id2 - id1;
            item[1] = Math.min(h2, h1 + dist);
        }
        for (int i = restList.size() - 2; i >= 0; i--) {
            int[] item = restList.get(i);
            int id1 = item[0];
            int h1 = item[1];
            int[] nextItem = restList.get(i + 1);
            int id2 = nextItem[0];
            int h2 = nextItem[1];
            int dist = id2 - id1;
            item[1] = Math.min(h1, h2 + dist);
        }
        int res = 0;
        for (int i = 1; i < restList.size(); i++) {
            int[] items1 = restList.get(i - 1);
            int id1 = items1[0];
            int h1 = items1[1];
            int[] items2 = restList.get(i);
            int id2 = items2[0];
            int h2 = items2[1];
            int hDiff = Math.abs(h1 - h2);
            int d = id2 - id1;
            int rem = d - hDiff;
            res = Math.max(res, Math.max(h1, h2) + rem / 2);
        }
        return Math.max(res, restList.get(restList.size() - 1)[1] + n - restList.get(restList.size() - 1)[0]);
    }

}
