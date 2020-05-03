package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Find the minimum swaps required to sor the array ascending.
 */
public class MinimumSwaps {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int swap = 0;
        boolean visited[] = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = i, cycle = 0;
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j] - 1;
                cycle++;
            }
            if (cycle != 0)
                swap += cycle - 1;
        }
        return swap;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/ms.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] arr = new int[n];
            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }
            int res = minimumSwaps(arr);
            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

    }

}
