package leetcode.mixed;

import java.util.Arrays;

public class FindSmallestCommonElementInAllRows {

    public static void main(String[] args) {
/*
Example 1:
Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
Output: 5
 */
        int[][] mat = {{1, 2, 3, 4, 5}, {2, 4, 5, 8, 10}, {3, 5, 7, 9, 11}, {1, 3, 5, 7, 9}};
        FindSmallestCommonElementInAllRows findSmallestCommonElementInAllRows = new FindSmallestCommonElementInAllRows();
        System.out.println(findSmallestCommonElementInAllRows.smallestCommonElement(mat));
    }

    public int smallestCommonElement(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[] minIndexes = new int[n];
        Arrays.fill(minIndexes, 0);
        int row = 1;
        int currentMin = mat[0][0];
        while (row < n) {
            int rowMin = mat[row][minIndexes[row]];
            if (rowMin > currentMin) {
                while (mat[row][minIndexes[row]] < currentMin) {
                    minIndexes[row]++;
                    if (minIndexes[row] >= m) {
                        return -1;
                    }
                }
                currentMin = mat[row][minIndexes[row]];
                row = 0;
            } else if (rowMin < currentMin) {
                while (mat[row][minIndexes[row]] < currentMin) {
                    minIndexes[row]++;
                    if (minIndexes[row] >= m) {
                        return -1;
                    }
                }
                rowMin = mat[row][minIndexes[row]];
                if (rowMin > currentMin) {
                    currentMin = rowMin;
                    row = 0;
                } else {
                    row++;
                }
            } else {
                row++;
            }
        }
        return currentMin;
    }

}
