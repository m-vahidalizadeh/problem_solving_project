package leetcode.companies.google;

/**
 * Android Unlock Patterns
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
 * <p>
 * Rules for a valid pattern:
 * <p>
 * Each pattern must connect at least m keys and at most n keys.
 * All the keys must be distinct.
 * If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
 * The order of keys used matters.
 * <p>
 * Explanation:
 * <p>
 * | 1 | 2 | 3 |
 * | 4 | 5 | 6 |
 * | 7 | 8 | 9 |
 * Invalid move: 4 - 1 - 3 - 6
 * Line 1 - 3 passes through key 2 which had not been selected in the pattern.
 * <p>
 * Invalid move: 4 - 1 - 9 - 2
 * Line 1 - 9 passes through key 5 which had not been selected in the pattern.
 * <p>
 * Valid move: 2 - 4 - 1 - 3 - 6
 * Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
 * <p>
 * Valid move: 6 - 5 - 4 - 1 - 9 - 2
 * Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
 * <p>
 * Example:
 * <p>
 * Input: m = 1, n = 1
 * Output: 9
 */
public class AndroidUnlockPatterns {

    public int numberOfPatterns(int m, int n) {
        int counter = 0;
        int[][] skip = new int[10][10];
        // In these 2-steps jumps, the middle one should be visited.
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[1][9] = skip[9][1] = 5;
        skip[2][8] = skip[8][2] = 5;
        skip[3][7] = skip[7][3] = 5;
        skip[4][6] = skip[6][4] = 5;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        boolean[] visited = new boolean[10];
        for (int i = m; i <= n; i++) {
            counter += dfs(skip, visited, 1, i - 1) * 4; // 1,3,7,9
            counter += dfs(skip, visited, 2, i - 1) * 4; // 2,4,6,8
            counter += dfs(skip, visited, 5, i - 1); // 5
        }
        return counter;
    }

    private int dfs(int[][] skip, boolean[] visited, int current, int l) {
        if (l < 0) return 0;
        if (l == 0) return 1;
        visited[current] = true;
        int sum = 0;
        for (int i = 1; i <= 9; i++) {
            if (!visited[i] && (skip[current][i] == 0 || visited[skip[current][i]]))
                sum += dfs(skip, visited, i, l - 1);
        }
        visited[current] = false;
        return sum;
    }

    public static void main(String[] args) {
        AndroidUnlockPatterns a = new AndroidUnlockPatterns();
        System.out.println(a.numberOfPatterns(1, 2));
    }

}
