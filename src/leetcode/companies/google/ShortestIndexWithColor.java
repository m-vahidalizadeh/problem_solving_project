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
public class ShortestIndexWithColor {

    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        Integer[] colors1 = new Integer[colors.length];
        Integer[] colors2 = new Integer[colors.length];
        Integer[] colors3 = new Integer[colors.length];
        Integer index1 = null;
        Integer index2 = null;
        Integer index3 = null;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == 1) index1 = i;
            else if (colors[i] == 2) index2 = i;
            else index3 = i;
            if (index1 != null) colors1[i] = i - index1;
            if (index2 != null) colors2[i] = i - index2;
            if (index3 != null) colors3[i] = i - index3;
        }
        index1 = null;
        index2 = null;
        index3 = null;
        for (int i = colors.length - 1; i >= 0; i--) {
            if (colors[i] == 1) index1 = i;
            else if (colors[i] == 2) index2 = i;
            else index3 = i;
            if (index1 != null && (colors1[i] == null || (Math.abs(index1 - i) < colors1[i])))
                colors1[i] = index1 - i;
            if (index2 != null && (colors2[i] == null || (Math.abs(index2 - i) < colors2[i])))
                colors2[i] = index2 - i;
            if (index3 != null && (colors3[i] == null || (Math.abs(index3 - i) < colors3[i])))
                colors3[i] = index3 - i;
        }
        List<Integer> res = new ArrayList<>();
        for (int[] query : queries) {
            int i = query[0];
            int c = query[1];
            if (c == 1) res.add(colors1[i] == null ? -1 : colors1[i]);
            else if (c == 2) res.add(colors2[i] == null ? -1 : colors2[i]);
            else res.add(colors3[i] == null ? -1 : colors3[i]);
        }
        return res;
    }

}
