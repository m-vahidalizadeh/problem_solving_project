package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 1240. Tiling a Rectangle with the Fewest Squares
 * Given a rectangle of size n x m, find the minimum number of integer-sided squares that tile the rectangle.
 *
 * Example 1:
 *
 * Input: n = 2, m = 3
 * Output: 3
 * Explanation: 3 squares are necessary to cover the rectangle.
 * 2 (squares of 1x1)
 * 1 (square of 2x2)
 * Example 2:
 *
 * Input: n = 5, m = 8
 * Output: 5
 * Example 3:
 *
 * Input: n = 11, m = 13
 * Output: 6
 *
 * Constraints:
 *
 * 1 <= n <= 13
 * 1 <= m <= 13
 */
public class FillingRect {

    Map<int[], Integer> cache;
    int res;

    public int tilingRectangle(int n, int m) {
        if (n == m) return 1;
        if (n > m) return tilingRectangle(m, n);
        cache = new HashMap<>();
        res = Integer.MAX_VALUE;
        dfs(n, m, new int[n], 0);
        return res;
    }

    private void dfs(int n, int m, int[] h, int count) {
        if (count >= res) return;
        int left = -1;
        int minHeight = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (h[i] < minHeight) {
                left = i;
                minHeight = h[i];
            }
        }
        if (minHeight == m) {
            res = count;
            return;
        }
        if (cache.containsKey(h) && cache.get(h) <= count) return;
        cache.put(h, count);
        int right = left;
        while (right + 1 < n && h[right + 1] == h[left] && (right + 1 - left + 1 + minHeight <= m)) right++;
        for (int k = right; k >= left; k--) {
            int[] modifiedH = h.clone();
            for (int l = left; l <= k; l++) modifiedH[l] += k - left + 1;
            dfs(n, m, modifiedH, count + 1);
        }
    }

}
