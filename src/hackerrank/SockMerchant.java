package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Counts the number of the paired socks.
 */
public class SockMerchant {

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        int max = Arrays.stream(ar).max().getAsInt();
        int[] arCounter = new int[max + 1];
        Arrays.stream(ar).forEach(e -> arCounter[e]++);
        IntStream.range(0, arCounter.length).forEach(i -> arCounter[i] =
                Double.valueOf(Math.floor((double) arCounter[i] / 2)).intValue());
        return Long.valueOf(Arrays.stream(arCounter).filter(e -> e > 0).sum()).intValue();
    }

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/socks_merchant.out")));) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] ar = new int[n];

            String[] arItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arItem = Integer.parseInt(arItems[i]);
                ar[i] = arItem;
            }

            int result = sockMerchant(n, ar);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
