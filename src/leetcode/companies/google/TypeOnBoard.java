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
public class TypeOnBoard {

    public String alphabetBoardPath(String target) {
        int currId = 0;
        StringBuilder resSB = new StringBuilder();
        for (char c : target.toCharArray()) {
            int nextId = c - 'a';
            resSB.append(getMove(currId, nextId)).append('!');
            currId = nextId;
        }
        return resSB.toString();
    }

    private String getMove(int currId, int nextId) {
        int currRow = currId / 5;
        int currCol = currId % 5;
        int nextRow = nextId / 5;
        int nextCol = nextId % 5;
        int rowDiff = Math.abs(currRow - nextRow);
        int colDiff = Math.abs(currCol - nextCol);
        StringBuilder res = new StringBuilder();
        if (nextId == 25) {
            if (nextCol < currCol) {
                for (int i = 0; i < colDiff; i++) res.append('L');
            } else if (currCol < nextCol) {
                for (int i = 0; i < colDiff; i++) res.append('R');
            }
            if (nextRow < currRow) {
                for (int i = 0; i < rowDiff; i++) res.append('U');
            } else if (currRow < nextRow) {
                for (int i = 0; i < rowDiff; i++) res.append('D');
            }
        } else {
            if (nextRow < currRow) {
                for (int i = 0; i < rowDiff; i++) res.append('U');
            } else if (currRow < nextRow) {
                for (int i = 0; i < rowDiff; i++) res.append('D');
            }
            if (nextCol < currCol) {
                for (int i = 0; i < colDiff; i++) res.append('L');
            } else if (currCol < nextCol) {
                for (int i = 0; i < colDiff; i++) res.append('R');
            }
        }
        return res.toString();
    }

}
