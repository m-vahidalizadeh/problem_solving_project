package leetcode.companies.google;

public class LongestLine {

    public static void main(String[] args) {
/*
Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
 */
        int[][] M = {
                {1, 1, 0, 0, 1, 0, 0, 1, 1, 0},
                {1, 0, 0, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                {0, 1, 0, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 0, 1, 1, 1, 1}};
        LongestLine longestLine = new LongestLine();
        System.out.println(longestLine.longestLine(M));
    }

    public int longestLine(int[][] M) {
        int maxLineLength = 0;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    int tempMaxLineLength = maxLineLength(i, j, M);
                    if (tempMaxLineLength > maxLineLength)
                        maxLineLength = tempMaxLineLength;
                }
            }
        }
        return maxLineLength;
    }

    private int maxLineLength(int i, int j, int[][] M) {
        int v = 0;
        int h = 0;
        int d1 = 0;
        int d2 = 0;
        int d3 = 0;
        int d4 = 0;
        int tempI = i;
        int tempJ = j;
        while (tempI < M.length && tempJ < M[0].length && M[tempI][tempJ] == 1) {
            v++;
            tempJ++;
        }
        tempI = i;
        tempJ = j;
        while (tempI < M.length && tempJ < M[0].length && M[tempI][tempJ] == 1) {
            h++;
            tempI++;
        }
        tempI = i;
        tempJ = j;
        while (tempI < M.length && tempJ < M[0].length && M[tempI][tempJ] == 1) {
            d1++;
            tempI++;
            tempJ++;
        }
        tempI = i;
        tempJ = j;
        while (tempI < M.length && tempJ >= 0 && M[tempI][tempJ] == 1) {
            d2++;
            tempI++;
            tempJ--;
        }
        tempI = i;
        tempJ = j;
        while (tempI >= 0 && tempJ < M[0].length && M[tempI][tempJ] == 1) {
            d3++;
            tempI--;
            tempJ++;
        }
        tempI = i;
        tempJ = j;
        while (tempI >= 0 && tempJ >= 0 && M[tempI][tempJ] == 1) {
            d4++;
            tempI--;
            tempJ--;
        }
        return Math.max(h, Math.max(v, Math.max(Math.max(d1, d2), Math.max(d3, d4))));
    }

}
