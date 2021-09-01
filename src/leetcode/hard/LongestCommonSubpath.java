package leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 1923. Longest Common Subpath
 * There is a country of n cities numbered from 0 to n - 1. In this country, there is a road connecting every pair of cities.
 *
 * There are m friends numbered from 0 to m - 1 who are traveling through the country. Each one of them will take a path consisting of some cities. Each path is represented by an integer array that contains the visited cities in order. The path may contain a city more than once, but the same city will not be listed consecutively.
 *
 * Given an integer n and a 2D integer array paths where paths[i] is an integer array representing the path of the ith friend, return the length of the longest common subpath that is shared by every friend's path, or 0 if there is no common subpath at all.
 *
 * A subpath of a path is a contiguous sequence of cities within that path.
 *
 * Example 1:
 *
 * Input: n = 5, paths = [[0,1,2,3,4],
 *                        [2,3,4],
 *                        [4,0,1,2,3]]
 * Output: 2
 * Explanation: The longest common subpath is [2,3].
 * Example 2:
 *
 * Input: n = 3, paths = [[0],[1],[2]]
 * Output: 0
 * Explanation: There is no common subpath shared by the three paths.
 * Example 3:
 *
 * Input: n = 5, paths = [[0,1,2,3,4],
 *                        [4,3,2,1,0]]
 * Output: 1
 * Explanation: The possible longest common subpaths are [0], [1], [2], [3], and [4]. All have a length of 1.
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * m == paths.length
 * 2 <= m <= 105
 * sum(paths[i].length) <= 105
 * 0 <= paths[i][j] < n
 * The same city is not listed multiple times consecutively in paths[i].
 */
public class LongestCommonSubpath {

    long[] pow;
    long mod;
    long base;

    public int longestCommonSubpath(int n, int[][] paths) {
        base = 100_001;
        mod = (long) (Math.pow(10, 11) + 7);
        int res = 0;
        int min = Integer.MAX_VALUE;
        for (int[] path : paths) min = Math.min(min, path.length);
        pow = new long[min + 1];
        pow[0] = 1;
        for (int i = 1; i <= min; i++) pow[i] = (pow[i - 1] * base) % mod;
        int start = 1;
        int end = min;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (exists(paths, mid)) {
                res = mid;
                start = mid + 1;
            } else end = mid - 1;
        }
        return res;
    }

    private boolean exists(int[][] paths, int l) {
        Set<Long> set = rollingHash(paths[0], l);
        for (int i = 1; i < paths.length; i++) {
            set.retainAll(rollingHash(paths[i], l));
            if (set.isEmpty()) return false;
        }
        return true;
    }

    private Set<Long> rollingHash(int[] path, int l) {
        Set<Long> set = new HashSet<>();
        long hash = 0;
        for (int i = 0; i < l; i++) hash = (hash * base + path[i]) % mod;
        set.add(hash);
        for (int i = l; i < path.length; i++) {
            hash = (((hash * base) % mod - (path[i - l] * pow[l]) % mod + path[i]) + mod) % mod;
            set.add(hash);
        }
        return set;
    }

}
