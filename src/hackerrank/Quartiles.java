package hackerrank;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Quartiles {

    public static void main(String[] args) throws IOException {
        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            int n = scanner.nextInt();
            int[] inputArray = new int[n];
            for (int i = 0; i < n; i++) {
                inputArray[i] = scanner.nextInt();
            }
            Arrays.sort(inputArray);
            if (n % 2 == 0) {
                int mid = n / 2;
                System.out.println(getMedian(inputArray, 0, mid - 1));
                System.out.println(getMedian(inputArray, 0, n - 1));
                System.out.println(getMedian(inputArray, mid, n - 1));
            } else {
                int mid = n / 2;
                System.out.println(getMedian(inputArray, 0, mid - 1));
                System.out.println(getMedian(inputArray, 0, n - 1));
                System.out.println(getMedian(inputArray, mid + 1, n - 1));
            }
        }
    }

    private static int getMedian(int[] sortedArray, int first, int last) {
        int n = last - first + 1;
        int mid = first + n / 2;
        if (n % 2 == 0) {
            return Double.valueOf(Math.floor((sortedArray[mid] + sortedArray[mid - 1]) / 2.0)).intValue();
        }
        return sortedArray[mid];
    }

}
