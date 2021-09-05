package leetcode.companies.google;

/**
 * 1138. Alphabet Board Path
 * On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 *
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
 *
 * We may make the following moves:
 *
 * 'U' moves our position up one row, if the position exists on the board;
 * 'D' moves our position down one row, if the position exists on the board;
 * 'L' moves our position left one column, if the position exists on the board;
 * 'R' moves our position right one column, if the position exists on the board;
 * '!' adds the character board[r][c] at our current position (r, c) to the answer.
 * (Here, the only positions that exist on the board are positions with letters on them.)
 *
 * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.
 *
 * Example 1:
 *
 * Input: target = "leet"
 * Output: "DDR!UURRR!!DDD!"
 * Example 2:
 *
 * Input: target = "code"
 * Output: "RR!DDRR!UUL!R!"
 *
 * Constraints:
 *
 * 1 <= target.length <= 100
 * target consists only of English lowercase letters.
 */
public class BoardPath {

    public String alphabetBoardPath(String target) {
        StringBuilder res = new StringBuilder();
        char currChar = 'a';
        for (char c : target.toCharArray()) {
            res.append(getPath(currChar, c)).append('!');
            currChar = c;
        }
        return res.toString();
    }

    private String getPath(char s, char t) {
        if (s == t) return "";
        StringBuilder res = new StringBuilder();
        int sNum = s - 'a';
        int sCol = sNum % 5;
        int tNum = t - 'a';
        int tCol = tNum % 5;
        int colDiff = Math.abs(tCol - sCol);
        int sRow = sNum / 5;
        int tRow = tNum / 5;
        int rowDiff = Math.abs(tRow - sRow);
        if (t == 'z') {
            if (sCol < tCol) {
                for (int i = 0; i < colDiff; i++) res.append('R');
            } else if (sCol > tCol) {
                for (int i = 0; i < colDiff; i++) res.append('L');
            }
            if (sRow < tRow) {
                for (int i = 0; i < rowDiff; i++) res.append('D');
            } else if (sRow > tRow) {
                for (int i = 0; i < rowDiff; i++) res.append('U');
            }
        } else {
            if (sRow < tRow) {
                for (int i = 0; i < rowDiff; i++) res.append('D');
            } else if (sRow > tRow) {
                for (int i = 0; i < rowDiff; i++) res.append('U');
            }
            if (sCol < tCol) {
                for (int i = 0; i < colDiff; i++) res.append('R');
            } else if (sCol > tCol) {
                for (int i = 0; i < colDiff; i++) res.append('L');
            }
        }
        return res.toString();
    }

}
