package hackerrank;

import java.util.Scanner;

/**
 * Counts the number of swaps in the bubble sort.
 */
public class BubbleSortCountSwaps {

    static void countSwaps(int[] a) {
        int n = a.length;
        int counter = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    counter++;
                }
            }
        }
        System.out.println("Array is sorted in "+counter+" swaps.");
        System.out.println("First Element: "+a[0]);
        System.out.println("Last Element: "+a[n-1]);
    }

    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] a = new int[n];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int aItem = Integer.parseInt(aItems[i]);
                a[i] = aItem;
            }

            countSwaps(a);
        }
    }

}
