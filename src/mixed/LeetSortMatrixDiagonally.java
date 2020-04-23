package mixed;

import java.util.PriorityQueue;

public class LeetSortMatrixDiagonally {

    public static void main(String[] args) {
        int[][] mat = {{3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}};
        LeetSortMatrixDiagonally leetSortMatrixDiagonally = new LeetSortMatrixDiagonally();
        int[][] result = leetSortMatrixDiagonally.diagonalSort(mat);
        leetSortMatrixDiagonally.printArray(result);
    }

    public int[][] diagonalSort(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        for (int j = 0; j < m; j++) {
            sortDiagonal(mat, 0, j, n, m);
        }
        for (int i = 0; i < n; i++) {
            sortDiagonal(mat, i, 0, n, m);
        }
        return mat;
    }

    public void sortDiagonal(int[][] mat, int i, int j, int n, int m) {
        int iIndex = i;
        int jIndex = j;
        PriorityQueue<Integer> sortedDiagonal = new PriorityQueue<>();
        while (iIndex < n && jIndex < m) {
            sortedDiagonal.add(mat[iIndex][jIndex]);
            iIndex++;
            jIndex++;
        }
        iIndex = i;
        jIndex = j;
        while (iIndex < n && jIndex < m) {
            mat[iIndex][jIndex] = sortedDiagonal.poll();
            iIndex++;
            jIndex++;
        }
    }

    public void printArray(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

}
