package leetcode.companies.google;

import java.util.*;

/**
 * 1182. Shortest Distance to Target Color
 * You are given an array colors, in which there are three colors: 1, 2 and 3.
 *
 * You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.
 *
 * Example 1:
 *
 * Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
 * Output: [3,0,3]
 * Explanation:
 * The nearest 3 from index 1 is at index 4 (3 steps away).
 * The nearest 2 from index 2 is at index 2 itself (0 steps away).
 * The nearest 1 from index 6 is at index 3 (3 steps away).
 * Example 2:
 *
 * Input: colors = [1,2], queries = [[0,3]]
 * Output: [-1]
 * Explanation: There is no 3 in the array.
 *
 * Constraints:
 *
 * 1 <= colors.length <= 5*10^4
 * 1 <= colors[i] <= 3
 * 1 <= queries.length <= 5*10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] < colors.length
 * 1 <= queries[i][1] <= 3
 */
public class FindClosestColor {

    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int cN = colors.length;
        Integer[] color1 = new Integer[cN];
        Integer[] color2 = new Integer[cN];
        Integer[] color3 = new Integer[cN];
        Integer index1 = null;
        Integer index2 = null;
        Integer index3 = null;
        for (int i = 0; i < cN; i++) {
            if (colors[i] == 1) {
                index1 = i;
            } else if (colors[i] == 2) {
                index2 = i;
            } else { // colors[i]==3
                index3 = i;
            }
            if (index1 != null)
                color1[i] = color1[i] == null ? Math.abs(i - index1) : Math.min(color1[i], Math.abs(i - index1));
            if (index2 != null)
                color2[i] = color2[i] == null ? Math.abs(i - index2) : Math.min(color2[i], Math.abs(i - index2));
            if (index3 != null)
                color3[i] = color3[i] == null ? Math.abs(i - index3) : Math.min(color3[i], Math.abs(i - index3));
        }
        index1 = null;
        index2 = null;
        index3 = null;
        for (int i = cN - 1; i >= 0; i--) {
            if (colors[i] == 1) {
                index1 = i;
            } else if (colors[i] == 2) {
                index2 = i;
            } else { // colors[i]==3
                index3 = i;
            }
            if (index1 != null)
                color1[i] = color1[i] == null ? Math.abs(i - index1) : Math.min(color1[i], Math.abs(i - index1));
            if (index2 != null)
                color2[i] = color2[i] == null ? Math.abs(i - index2) : Math.min(color2[i], Math.abs(i - index2));
            if (index3 != null)
                color3[i] = color3[i] == null ? Math.abs(i - index3) : Math.min(color3[i], Math.abs(i - index3));
        }
        List<Integer> res = new ArrayList<>();
        for (int[] query : queries) {
            int i = query[0];
            int color = query[1];
            if (color == 1) {
                res.add(color1[i] == null ? -1 : color1[i]);
            } else if (color == 2) {
                res.add(color2[i] == null ? -1 : color2[i]);
            } else { // color==3
                res.add(color3[i] == null ? -1 : color3[i]);
            }
        }
        return res;
    }

}
