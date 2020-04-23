package mixed;

public class MatrixBlockSum {

    public static void main(String[] args) {
        MatrixBlockSum matrixBlockSum = new MatrixBlockSum();
        int[][] mat1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printArray(matrixBlockSum.matrixBlockSum(mat1, 1));
        System.out.println();
        int[][] mat2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printArray(matrixBlockSum.matrixBlockSum(mat2, 2));
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] matrixBlockSum(int[][] mat, int K) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] resultDP = new int[n][m];
        resultDP[0][0] = sumFirstBlock(mat, K);
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                int sumI = resultDP[i - 1][0];
                int rowToBeAdded = i + K;
                if (rowToBeAdded < n) {
                    sumI = sumI + getRowSum(mat, rowToBeAdded, 0, K);
                }
                int rowToBeRemoved = i - 1 - K;
                if (0 <= rowToBeRemoved) {
                    sumI = sumI - getRowSum(mat, rowToBeRemoved, 0, K);
                }
                resultDP[i][0] = sumI;
            }
            for (int j = 1; j < m; j++) {
                int sumJ = resultDP[i][j - 1];
                int columnToBeAdded = j + K;
                if (columnToBeAdded < m) {
                    sumJ = sumJ + getColumnSum(mat, columnToBeAdded, i - K, i + K);
                }
                int columnToBeRemoved = j - 1 - K;
                if (columnToBeRemoved >= 0) {
                    sumJ = sumJ - getColumnSum(mat, columnToBeRemoved, i - K, i + K);
                }
                resultDP[i][j] = sumJ;
            }
        }
        return resultDP;
    }

    private int sumFirstBlock(int[][] mat, int K) {
        int sum = 0;
        int n = mat.length - 1;
        int m = mat[0].length - 1;
        if (K < n) {
            n = K;
        }
        if (K < m) {
            m = K;
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                sum += mat[i][j];
            }
        }
        return sum;
    }

    private int getRowSum(int[][] mat, int i, int start, int end) {
        int sum = 0;
        if (start < 0) {
            start = 0;
        }
        int m = mat[0].length;
        if (end >= m) {
            end = m - 1;
        }
        for (int j = start; j <= end; j++) {
            sum += mat[i][j];
        }
        return sum;
    }

    private int getColumnSum(int[][] mat, int j, int start, int end) {
        int sum = 0;
        if (start < 0) {
            start = 0;
        }
        int n = mat.length;
        if (end >= n) {
            end = n - 1;
        }
        for (int i = start; i <= end; i++) {
            sum += mat[i][j];
        }
        return sum;
    }

}
