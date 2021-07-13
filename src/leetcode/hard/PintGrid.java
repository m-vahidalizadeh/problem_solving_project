package leetcode.hard;

import javafx.util.Pair;

import java.util.*;

/**
 * 1931. Painting a Grid With Three Different Colors
 * You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint each cell red, green, or blue. All cells must be painted.
 *
 * Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer can be very large, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: m = 1, n = 1
 * Output: 3
 * Explanation: The three possible colorings are shown in the image above.
 * Example 2:
 *
 * Input: m = 1, n = 2
 * Output: 6
 * Explanation: The six possible colorings are shown in the image above.
 * Example 3:
 *
 * Input: m = 5, n = 5
 * Output: 580986
 *
 * Constraints:
 *
 * 1 <= m <= 5
 * 1 <= n <= 1000
 */
public class PintGrid {

    Map<Pair<Integer, Integer>, Integer> cache;
    int MOD = 1_000_000_007;
    int n;
    int m;
    int seqEnd;

    public int colorTheGrid(int m, int n) {
        this.cache = new HashMap<>();
        this.m = m;
        this.n = n;
        this.seqEnd = m * n;
        return rec(0, 0); // seq: 0 ... n*m
    }

    private int rec(int seq, int prevRow) {
        if (seq == seqEnd) return 1;
        Pair<Integer, Integer> key = new Pair<>(seq, prevRow);
        if (cache.containsKey(key)) return cache.get(key);
        int i = seq / m;
        int j = seq % m;
        Set<Integer> neiColors = new HashSet<>();
        if (i > 0) neiColors.add(getColor(prevRow, j));
        if (j > 0) neiColors.add(getColor(prevRow, j - 1));
        long res = 0;
        for (int color = 0; color < 3; color++) {
            if (!neiColors.contains(color)) res = (res + rec(seq + 1, setColor(prevRow, j, color))) % MOD;
        }
        int ans = (int) (res % MOD);
        cache.put(key, ans);
        return ans;
    }

    private int getColor(int mask, int index) {
        return (mask >> (2 * index)) & 3;
    }

    private int setColor(int mask, int index, int newColor) {
        int oldColor = getColor(mask, index);
        return mask ^ (oldColor << (2 * index)) | (newColor << (2 * index));
    }

}
