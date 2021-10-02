package leetcode.companies.google;

/**
 * 351. Android Unlock Patterns
 * Android devices have a special lock screen with a 3 x 3 grid of dots. Users can set an "unlock pattern" by connecting the dots in a specific sequence, forming a series of joined line segments where each segment's endpoints are two consecutive dots in the sequence. A sequence of k dots is a valid unlock pattern if both of the following are true:
 *
 * All the dots in the sequence are distinct.
 * If the line segment connecting two consecutive dots in the sequence passes through the center of any other dot, the other dot must have previously appeared in the sequence. No jumps through the center non-selected dots are allowed.
 * For example, connecting dots 2 and 9 without dots 5 or 6 appearing beforehand is valid because the line from dot 2 to dot 9 does not pass through the center of either dot 5 or 6.
 * However, connecting dots 1 and 3 without dot 2 appearing beforehand is invalid because the line from dot 1 to dot 3 passes through the center of dot 2.
 * Here are some example valid and invalid unlock patterns:
 *
 * The 1st pattern [4,1,3,6] is invalid because the line connecting dots 1 and 3 pass through dot 2, but dot 2 did not previously appear in the sequence.
 * The 2nd pattern [4,1,9,2] is invalid because the line connecting dots 1 and 9 pass through dot 5, but dot 5 did not previously appear in the sequence.
 * The 3rd pattern [2,4,1,3,6] is valid because it follows the conditions. The line connecting dots 1 and 3 meets the condition because dot 2 previously appeared in the sequence.
 * The 4th pattern [6,5,4,1,9,2] is valid because it follows the conditions. The line connecting dots 1 and 9 meets the condition because dot 5 previously appeared in the sequence.
 * Given two integers m and n, return the number of unique and valid unlock patterns of the Android grid lock screen that consist of at least m keys and at most n keys.
 *
 * Two unlock patterns are considered unique if there is a dot in one sequence that is not in the other, or the order of the dots is different.
 *
 * Example 1:
 *
 * Input: m = 1, n = 1
 * Output: 9
 * Example 2:
 *
 * Input: m = 1, n = 2
 * Output: 65
 *
 * Constraints:
 *
 * 1 <= m, n <= 9
 */
public class NumberOfUnlockPatterns {

    Integer[][] middles;
    int m;
    int n;

    public int numberOfPatterns(int m, int n) {
        this.m = m;
        this.n = n;
        middles = new Integer[10][10];
        middles[1][9] = middles[9][1] = middles[2][8] = middles[8][2] = middles[3][7] = middles[7][3] = middles[6][4] = middles[4][6] = 5;
        middles[1][7] = middles[7][1] = 4;
        middles[1][3] = middles[3][1] = 2;
        middles[3][9] = middles[9][3] = 6;
        middles[7][9] = middles[9][7] = 8;
        int count = 0;
        for (int rem = n; rem >= m; rem--) {
            count += 4 * dfs(1, rem - 1, new boolean[10]);
            count += 4 * dfs(2, rem - 1, new boolean[10]);
            count += dfs(5, rem - 1, new boolean[10]);
        }
        return count;
    }

    private int dfs(int node, int rem, boolean[] visited) {
        if (rem < 0) return 0;
        if (rem == 0) return 1;
        int count = 0;
        visited[node] = true;
        for (int nei = 1; nei <= 9; nei++) {
            if (visited[nei]) continue;
            Integer middle = middles[node][nei];
            if (middle == null || visited[middle]) count += dfs(nei, rem - 1, visited);
        }
        visited[node] = false;
        return count;
    }

}
