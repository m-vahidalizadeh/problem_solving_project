package leetcode.medium;

public class ScoreAfterFlippingMatrix {

    public static void main(String[] args) {
        int[][] input = {{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};
//        int[][] input={{0,1},{1,1}};
//        int[][] input={{0,1,1},{1,1,1},{0,1,0}};
        ScoreAfterFlippingMatrix scoreAfterFlippingMatrix = new ScoreAfterFlippingMatrix();
        System.out.println(scoreAfterFlippingMatrix.matrixScore(input));
    }

    public int matrixScore(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int zeroTry = zeroTry(A, n, m);
        int oneTry = oneTry(A, n, m);
        return Math.max(zeroTry, oneTry);
    }

    private int zeroTry(int[][] A, int n, int m) {
        int[][] newA = A.clone();
        // Make beginning of each row zero
        for (int i = 0; i < n; i++) {
            if (newA[i][0] != 0) {
                togleRow(newA, i, n, m);
            }
        }
        // Toggle column if number of zeros are > n/2
        int halfN = n / 2;
        for (int j = 1; j < m; j++) {
            int numberOfOnesInColumn = countOnesInColumn(newA, j, n, m);
            if (numberOfOnesInColumn > halfN) {
                toggleColumn(newA, j, n, m);
            }
        }
        for (int i = 0; i < n; i++) {
            togleRow(newA, i, n, m);
        }
        return sumMatrix(newA, n, m);
    }

    private int oneTry(int[][] A, int n, int m) {
        int[][] newA = A.clone();
        // Make beginning of each row one
        for (int i = 0; i < n; i++) {
            if (A[i][0] != 1) {
                togleRow(newA, i, n, m);
            }
        }
        // Toggle column if number of zeros are > n/2
        int halfN = n / 2;
        for (int j = 1; j < m; j++) {
            int numberOfZerosInColumnJ = countZerosInColumn(newA, j, n, m);
            if (numberOfZerosInColumnJ > halfN) {
                toggleColumn(newA, j, n, m);
            }
        }
        return sumMatrix(newA, n, m);
    }

    private void togleRow(int[][] A, int i, int n, int m) {
        for (int j = 0; j < m; j++) {
            A[i][j] = A[i][j] == 0 ? 1 : 0;
        }
    }

    private void toggleColumn(int[][] A, int j, int n, int m) {
        for (int i = 0; i < n; i++) {
            A[i][j] = A[i][j] == 0 ? 1 : 0;
        }
    }

    private int countZerosInColumn(int[][] A, int j, int n, int m) {
        int counter = 0;
        for (int i = 0; i < n; i++) {
            if (A[i][j] == 0)
                counter++;
        }
        return counter;
    }

    private int countOnesInColumn(int[][] A, int j, int n, int m) {
        int counter = 0;
        for (int i = 0; i < n; i++) {
            if (A[i][j] == 1)
                counter++;
        }
        return counter;
    }

    private int sumMatrix(int[][] A, int n, int m) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += evaluateRow(A, i, n, m);
        }
        return sum;
    }

    private int evaluateRow(int[][] A, int i, int n, int m) {
        int num = 0;
        int pow = 0;
        for (int j = m - 1; j >= 0; j--) {
            num += A[i][j] * Math.pow(2, pow);
            pow++;
        }
        return num;
    }

}
