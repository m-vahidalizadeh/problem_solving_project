package leetcode.hard;

/**
 * 302. Smallest Rectangle Enclosing Black Pixels
 * You are given an image that is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
 *
 * The black pixels are connected (i.e., there is only one black region). Pixels are connected horizontally and vertically.
 *
 * Given two integers x and y that represent the location of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
 *
 * Example 1:
 *
 * Input: image = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
 * Output: 6
 * Example 2:
 *
 * Input: image = [["1"]], x = 0, y = 0
 * Output: 1
 *
 * Constraints:
 *
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 100
 * image[i][j] is either '0' or '1'.
 * 1 <= x < m
 * 1 <= y < n
 * image[x][y] == '1'.
 * The black pixels in the image only form one component.
 */
public class SmallestRectEnclosingBlackPixels {

    public int minArea(char[][] image, int x, int y) {
        int n = image.length;
        int m = image[0].length;
        int up = n - 1;
        int down = 0;
        int left = m - 1;
        int right = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (image[i][j] == '1') {
                    up = Math.min(up, i);
                    down = Math.max(down, i);
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                }
            }
        }
        return (down - up + 1) * (right - left + 1);
    }

}
