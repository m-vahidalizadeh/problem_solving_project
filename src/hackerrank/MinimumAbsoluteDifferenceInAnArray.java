package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MinimumAbsoluteDifferenceInAnArray {

    static int minimumAbsoluteDifference(int[] arr) {
        int n = arr.length;
        int minAbsoluteDiff = Integer.MAX_VALUE;
        Arrays.sort(arr);
        for (int i = 0; i < n - 1; i++) {
            int tempAbsoluteDiff = Math.abs(arr[i] - arr[i + 1]);
            if (tempAbsoluteDiff < minAbsoluteDiff) {
                minAbsoluteDiff = tempAbsoluteDiff;
            }
        }
        return minAbsoluteDiff;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/mad.out")));
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

            int result = minimumAbsoluteDifference(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
