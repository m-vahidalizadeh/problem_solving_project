package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BeautifulTriplets {

    static int beautifulTriplets(int d, int[] arr) {
        int n = arr.length;
        if (n < 3) {
            return 0;
        }
        int beautifulTripletsCount = 0;
        int[][] differences = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                differences[i][j] = arr[j] - arr[i];
            }
        }

        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                for (int c = b + 1; c < n; c++) {
                    if (differences[a][b] == d && differences[b][c] == d) {
                        beautifulTripletsCount++;
                    }
                }
            }
        }
        return beautifulTripletsCount;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/bt.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String[] nd = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nd[0]);

            int d = Integer.parseInt(nd[1]);

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int result = beautifulTriplets(d, arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

    }

}
