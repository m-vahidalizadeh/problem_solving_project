package leetcode.companies.google;

/**
 * Android Unlock Patterns
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
 *
 * Rules for a valid pattern:
 *
 * Each pattern must connect at least m keys and at most n keys.
 * All the keys must be distinct.
 * If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
 * The order of keys used matters.
 *
 * Explanation:
 *
 * | 1 | 2 | 3 |
 * | 4 | 5 | 6 |
 * | 7 | 8 | 9 |
 * Invalid move: 4 - 1 - 3 - 6
 * Line 1 - 3 passes through key 2 which had not been selected in the pattern.
 *
 * Invalid move: 4 - 1 - 9 - 2
 * Line 1 - 9 passes through key 5 which had not been selected in the pattern.
 *
 * Valid move: 2 - 4 - 1 - 3 - 6
 * Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
 *
 * Valid move: 6 - 5 - 4 - 1 - 9 - 2
 * Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
 *
 * Example:
 *
 * Input: m = 1, n = 1
 * Output: 9
 */
public class NumberOfPatterns {

    int[][] jumps;

    public int numberOfPatterns(int m, int n) {
        jumps = new int[10][10];
        jumps[1][3] = jumps[3][1] = 2;
        jumps[1][7] = jumps[7][1] = 4;
        jumps[7][9] = jumps[9][7] = 8;
        jumps[3][9] = jumps[9][3] = 6;
        jumps[1][9] = jumps[9][1] = jumps[2][8] = jumps[8][2] = jumps[3][7] = jumps[7][3] = jumps[4][6] = jumps[6][4] = 5;
        boolean[] visited = new boolean[10];
        int sum = 0;
        for (int i = m; i <= n; i++) {
            sum += 4 * rec(1, visited, i - 1);
            sum += 4 * rec(2, visited, i - 1);
            sum += rec(5, visited, i - 1);
        }
        return sum;
    }

    private int rec(int i, boolean[] visited, int l) {
        if (l < 0) return 0;
        if (l == 0) return 1;
        visited[i] = true;
        int sum = 0;
        for (int j = 1; j <= 9; j++) {
            if (j == i) continue;
            if (visited[j]) continue;
            if (jumps[i][j] == 0 || visited[jumps[i][j]]) sum += rec(j, visited, l - 1);
        }
        visited[i] = false;
        return sum;
    }

    public static void main(String[] args) {
        NumberOfPatterns n = new NumberOfPatterns();
        System.out.println(n.numberOfPatterns(1, 2));
    }

}
