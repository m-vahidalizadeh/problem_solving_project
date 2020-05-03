package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArrayManipulation {

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        long[] result = new long[n];
        long max = Long.MIN_VALUE;
        for (int i = 0; i < queries.length; i++) {
            for (int j = queries[i][0] - 1; j <= queries[i][1] - 1; j++) {
                result[j] += queries[i][2];
                if (max < result[j]) {
                    max = result[j];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/am.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] queries = new int[m][3];

            for (int i = 0; i < m; i++) {
                String[] queriesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 3; j++) {
                    int queriesItem = Integer.parseInt(queriesRowItems[j]);
                    queries[i][j] = queriesItem;
                }
            }

            long result = arrayManipulation(n, queries);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
