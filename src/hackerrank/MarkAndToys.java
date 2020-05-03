package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Finds the maximum number of toys bought with a budget.
 */
public class MarkAndToys {

    static int maximumToys(int[] prices, int k) {
        int counter = 0;
        Arrays.sort(prices);
        for (int i = 0; i < prices.length; i++) {
            if (k >= prices[i]) {
                k = k - prices[i];
                counter++;
            } else {
                break;
            }
        }
        return counter;
    }

    public static void main(String[] args) throws IOException {

        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/mat.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String[] nk = scanner.nextLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            int[] prices = new int[n];
            String[] pricesItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < n; i++) {
                int pricesItem = Integer.parseInt(pricesItems[i]);
                prices[i] = pricesItem;
            }
            int result = maximumToys(prices, k);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
