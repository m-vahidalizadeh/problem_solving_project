package leetcode.companies.google;

import java.util.ArrayList;
import java.util.List;

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
public class ShortestDistanceColor {

    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int n = colors.length;
        Integer[] color1 = new Integer[n];
        Integer[] color2 = new Integer[n];
        Integer[] color3 = new Integer[n];
        Integer index1 = null;
        Integer index2 = null;
        Integer index3 = null;
        for (int i = 0; i < n; i++) {
            if (colors[i] == 1) index1 = i;
            else if (colors[i] == 2) index2 = i;
            else index3 = i;
            if (index1 != null)
                color1[i] = color1[i] == null ? Math.abs(i - index1) : Math.min(color1[i], Math.abs(i - index1));
            if (index2 != null)
                color2[i] = color2[i] == null ? Math.abs(i - index2) : Math.min(color2[i], Math.abs(i - index2));
            if (index3 != null)
                color3[i] = color3[i] == null ? Math.abs(i - index3) : Math.min(color3[i], Math.abs(i - index3));
        }
        for (int i = n - 1; i >= 0; i--) {
            if (colors[i] == 1) index1 = i;
            else if (colors[i] == 2) index2 = i;
            else index3 = i;
            if (index1 != null)
                color1[i] = color1[i] == null ? Math.abs(index1 - i) : Math.min(color1[i], Math.abs(index1 - i));
            if (index2 != null)
                color2[i] = color2[i] == null ? Math.abs(index2 - i) : Math.min(color2[i], Math.abs(index2 - i));
            if (index3 != null)
                color3[i] = color3[i] == null ? Math.abs(index3 - i) : Math.min(color3[i], Math.abs(index3 - i));
        }
        List<Integer> res = new ArrayList<>();
        for (int[] query : queries) {
            int pos = query[0];
            int color = query[1];
            if (color == 1) res.add(color1[pos] == null ? -1 : color1[pos]);
            else if (color == 2) res.add(color2[pos] == null ? -1 : color2[pos]);
            else res.add(color3[pos] == null ? -1 : color3[pos]);
        }
        return res;
    }

}
