package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Deletes some elements from the array to make all the remaining element equal.
 */
public class EqualizeTheArray {
    // Complete the equalizeArray function below.
    static int equalizeArray(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int[] frequencies = new int[max + 1];
        Arrays.fill(frequencies, 0);
        Arrays.stream(arr).forEach(a -> frequencies[a]++);
        int maxOfFrequencies = Integer.MIN_VALUE;
        for (int a = 1; a <= max; a++) {
            if (frequencies[a] > maxOfFrequencies) {
                maxOfFrequencies = frequencies[a];
            }
        }
        return arr.length - maxOfFrequencies;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/eta.out")));
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
            int result = equalizeArray(arr);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
