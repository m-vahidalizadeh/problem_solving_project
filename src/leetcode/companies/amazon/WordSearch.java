package leetcode.companies.amazon;

/**
 * 79. Word Search
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example 1:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 * Constraints:
 *
 * board and word consists only of lowercase and uppercase English letters.
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class WordSearch {

    int[][] directions;
    char[][] board;
    int n;
    int m;
    String word;
    int l;

    public boolean exist(char[][] board, String word) {
        directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        this.board = board;
        this.n = board.length;
        this.m = board[0].length;
        this.word = word;
        this.l = word.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(0, i, j)) return true;
            }
        }
        return false;
    }

    private boolean dfs(int index, int i, int j) {
        if (index == l) return true;
        if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] == '@' || board[i][j] != word.charAt(index)) return false;
        char c = board[i][j];
        board[i][j] = '@';
        boolean ans = false;
        for (int[] dir : directions) {
            if (dfs(index + 1, i + dir[0], j + dir[1])) {
                ans = true;
                break;
            }
        }
        board[i][j] = c;
        return ans;
    }

}
