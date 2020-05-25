package leetcode.companies.microsoft;

public class MSFindWords {

    public static void main(String[] args) {
/*
Example:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 */
//        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
//        System.out.println(exist(board, "ABCCED"));
//        System.out.println(exist(board, "SEE"));
//        System.out.println(exist(board, "ABCB"));
//        char[][] board = new char[][]{{'a', 'a'}};
//        System.out.println(exist(board, "aaa"));
        char[][] board = new char[][]{{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}};
        System.out.println(exist(board, "AAB"));
    }

    public static boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length + 1][board[0].length + 1];
        char[] wordChars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == wordChars[0]) {
                    for (int a = 0; a < visited.length; a++) {
                        for (int b = 0; b < visited[0].length; b++) {
                            visited[a][b] = false;
                        }
                    }
                    boolean tempFound = true;
                    int index = 1;
                    int currentI = i;
                    int currentJ = j;
                    visited[i][j] = true;
                    int n = wordChars.length;
                    while (index < n && tempFound) {
                        if (currentJ + 1 <= board[0].length - 1 && board[currentI][currentJ + 1] == wordChars[index] && !visited[currentI][currentJ + 1]) {
                            visited[currentI][currentJ + 1] = true;
                            currentJ++;
                            index++;
                        } else if (currentJ - 1 >= 0 && board[currentI][currentJ - 1] == wordChars[index] && !visited[currentI][currentJ - 1]) {
                            visited[currentI][currentJ - 1] = true;
                            currentJ--;
                            index++;
                        } else if (currentI + 1 <= board.length - 1 && board[currentI + 1][currentJ] == wordChars[index] && !visited[currentI + 1][currentJ]) {
                            visited[currentI + 1][currentJ] = true;
                            currentI++;
                            index++;
                        } else if (currentI - 1 >= 0 && board[currentI - 1][currentJ] == wordChars[index] && !visited[currentI - 1][currentJ]) {
                            visited[currentI - 1][currentJ] = true;
                            currentI--;
                            index++;
                        } else {
                            tempFound = false;
                        }
                    }
                    if (tempFound) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
