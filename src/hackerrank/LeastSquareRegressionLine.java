package hackerrank;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class LeastSquareRegressionLine {

    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            int[] x = new int[5];
            int[] y = new int[5];
            for (int i = 0; i < 5; i++) {
                x[i] = scanner.nextInt();
                y[i] = scanner.nextInt();
            }
            double xMean = calculateMean(x);
            double yMean = calculateMean(y);
            double b = calculateB(x, y);
            double a = yMean - b * xMean;
            DecimalFormat df = new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            System.out.println(df.format(a + b * 80));
        }
    }

    private static double calculateMean(int[] input) {
        int n = input.length;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += input[i];
        }
        return sum / n;
    }

    private static double calculateB(int[] x, int[] y) {
        double xiYiSum = 0;
        double xiSum = 0;
        double yiSum = 0;
        double xi2Sum = 0;
        int n = x.length;
        for (int i = 0; i < n; i++) {
            xiYiSum += x[i] * y[i];
            xiSum += x[i];
            yiSum += y[i];
            xi2Sum += Math.pow(x[i], 2);
        }
        return ((n * xiYiSum) - (xiSum * yiSum)) / ((n * xi2Sum) - Math.pow(xiSum, 2));
    }

}
