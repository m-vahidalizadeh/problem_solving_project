package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 546. Remove Boxes
 * You are given several boxes with different colors represented by different positive numbers.
 *
 * You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.
 *
 * Return the maximum points you can get.
 *
 * Example 1:
 *
 * Input: boxes = [1,3,2,2,2,3,4,3,1]
 * Output: 23
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 * ----> [1, 3, 3, 3, 1] (1*1=1 points)
 * ----> [1, 1] (3*3=9 points)
 * ----> [] (2*2=4 points)
 * Example 2:
 *
 * Input: boxes = [1,1,1]
 * Output: 9
 * Example 3:
 *
 * Input: boxes = [1]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= boxes.length <= 100
 * 1 <= boxes[i] <= 100
 */
public class RemoveBoxes {

    Map<String, Integer> cache;

    public int removeBoxes(int[] boxes) {
        cache = new HashMap<>();
        return dp(0, boxes.length - 1, 0, boxes);
    }

    private int dp(int i, int j, int k, int[] boxes) {
        if (i > j) return 0;
        String key = i + "-" + j + "-" + k;
        if (cache.containsKey(key)) return cache.get(key);
        int ii = i;
        int count = 1;
        ii++;
        while (ii <= j && boxes[ii - 1] == boxes[ii]) {
            ii++;
            count++;
        }
        int ans = dp(ii, j, 0, boxes) + (int) Math.pow(k + count, 2);
        for (int m = ii; m <= j; m++) {
            if (boxes[m] == boxes[i]) {
                ans = Math.max(ans, dp(ii, m - 1, 0, boxes) + dp(m, j, k + count, boxes));
            }
        }
        cache.put(key, ans);
        return ans;
    }

}
