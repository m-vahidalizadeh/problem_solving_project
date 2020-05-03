package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Finds the single integer among pairs.
 */
public class LonelyInteger {
    static int lonelyinteger(int[] a) {
        int max = Arrays.stream(a).max().getAsInt();
        int[] frequencies = new int[max + 1];
        for (int i = 0; i < a.length; i++) {
            frequencies[a[i]]++;
        }
        for (int i = 1; i <= max; i++) {
            if (frequencies[i] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/li.out")));
             Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] a = new int[n];
            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < n; i++) {
                int aItem = Integer.parseInt(aItems[i]);
                a[i] = aItem;
            }
            int result = lonelyinteger(a);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
