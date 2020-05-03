package hackerrank;

import java.util.Scanner;

public class WeightedMean {

    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            int n = scanner.nextInt();
            int[] numbers = new int[n];
            int[] weights = new int[n];
            double weightsSum = 0;
            double numbersMultiplyWeightsSum = 0;
            for (int i = 0; i < n; i++) {
                numbers[i] = scanner.nextInt();
            }
            for (int i = 0; i < n; i++) {
                weights[i] = scanner.nextInt();
                weightsSum += weights[i];
            }
            for (int i = 0; i < n; i++) {
                numbersMultiplyWeightsSum += numbers[i] * weights[i];
            }
            System.out.println(Math.round((numbersMultiplyWeightsSum / weightsSum) * 10.0) / 10.0);
        }
    }

}
