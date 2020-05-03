package hackerrank;

import java.util.*;

public class InsertionSort2 {

    static void insertionSort2(int n, int[] arr) {
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            int index = i;
            while (j >= 0 && index >= 0 && arr[j] > arr[index]) {
                int temp = arr[j];
                arr[j] = arr[index];
                arr[index] = temp;
                j--;
                index--;
            }
            printArray(n, arr);
        }
    }

    public static void printArray(int n, int[] arr) {
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            insertionSort2(n, arr);
        }
    }
}
