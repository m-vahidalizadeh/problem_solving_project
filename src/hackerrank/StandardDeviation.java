package hackerrank;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class StandardDeviation {

    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            int n = scanner.nextInt();
            int[] input = new int[n];
            for (int i = 0; i < n; i++) {
                input[i] = scanner.nextInt();
            }
            double mean = getMean(input, n);
            double summation = 0;
            for (int i = 0; i < n; i++) {
                summation += Math.pow((input[i] - mean), 2);
            }
            System.out.println(new DecimalFormat("#.#").format(Math.sqrt(summation / n)));
        }
    }

    static double getMean(int[] inputArray, int n) {
        return Arrays.stream(inputArray).sum() / Double.valueOf(n);
    }

}
