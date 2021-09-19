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
public class TypeWithBoard {

    public String alphabetBoardPath(String target) {
        StringBuilder res = new StringBuilder();
        char curr = 'a';
        for (char c : target.toCharArray()) {
            res.append(getMoves(curr, c)).append('!');
            curr = c;
        }
        return res.toString();
    }

    private String getMoves(char curr, char next) {
        if (curr == next) return "";
        int currNum = curr - 'a';
        int currRow = currNum / 5;
        int currCol = currNum % 5;
        int nextNum = next - 'a';
        int nextRow = nextNum / 5;
        int nextCol = nextNum % 5;
        StringBuilder res = new StringBuilder();
        int verticalDiff = Math.abs(nextRow - currRow);
        int horizontalDiff = Math.abs(nextCol - currCol);
        if (next == 'z') {
            if (currCol < nextCol) {
                for (int i = 0; i < horizontalDiff; i++) res.append('R');
            } else if (nextCol < currCol) {
                for (int i = 0; i < horizontalDiff; i++) res.append('L');
            }
            if (currRow < nextRow) {
                for (int i = 0; i < verticalDiff; i++) res.append('D');
            } else if (nextRow < currRow) {
                for (int i = 0; i < verticalDiff; i++) res.append('U');
            }
        } else {
            if (currRow < nextRow) {
                for (int i = 0; i < verticalDiff; i++) res.append('D');
            } else if (nextRow < currRow) {
                for (int i = 0; i < verticalDiff; i++) res.append('U');
            }
            if (currCol < nextCol) {
                for (int i = 0; i < horizontalDiff; i++) res.append('R');
            } else if (nextCol < currCol) {
                for (int i = 0; i < horizontalDiff; i++) res.append('L');
            }
        }
        return res.toString();
    }

}
