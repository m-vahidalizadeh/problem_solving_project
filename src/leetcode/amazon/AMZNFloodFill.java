package leetcode.amazon;

public class AMZNFloodFill {

    int[][] colors;

    public static void main(String[] args) {
        /*
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
         */
        /*
        [[0,0,0],[0,1,1]]
1
1
1
         */
//        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] image = {{0, 0, 0}, {0, 1, 1}};
        AMZNFloodFill amznFloodFill = new AMZNFloodFill();
//        int[][] result = amznFloodFill.floodFill(image, 1, 1, 2);
        int[][] result = amznFloodFill.floodFill(image, 1, 1, 1);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        colors = image;
        spread(sr, sc, image[sr][sc], newColor);
        return colors;
    }

    public void spread(int i, int j, int startColor, int newColor) {
        if (startColor == newColor) {
            return;
        }
        if (colors[i][j] == startColor) {
            colors[i][j] = newColor;
            if (i - 1 >= 0)
                spread(i - 1, j, startColor, newColor);
            if (i + 1 < colors.length)
                spread(i + 1, j, startColor, newColor);
            if (j - 1 >= 0)
                spread(i, j - 1, startColor, newColor);
            if (j + 1 < colors[0].length)
                spread(i, j + 1, startColor, newColor);
        }
    }

}
