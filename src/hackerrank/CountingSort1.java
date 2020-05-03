package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Implements counting sort.
 */
public class CountingSort1 {

    static int[] countingSort(int[] arr) {
        int arrMax = Arrays.stream(arr).max().getAsInt();
        int[] occurances = new int[arrMax + 1];
        Arrays.stream(arr).forEach(n -> {
            occurances[n]++;
        });
        return occurances;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/cs1")));
             Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] arr = new int[n];
            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }
            int[] result = countingSort(arr);
            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));
                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }
            bufferedWriter.newLine();
        }
    }
}
