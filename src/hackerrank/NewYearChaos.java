package hackerrank;

import java.util.Scanner;

/**
 * Solves the new year chaos problem.
 */
public class NewYearChaos {

    static void minimumBribes(int[] q) {
        int totalSwaps = 0;
        boolean tooChaotic = false;
        int[] swaps = new int[q.length + 1];
        if (isSorted(q)) {
            System.out.println(totalSwaps);
        } else {
            while (!isSorted(q) && !tooChaotic) {
                for (int i = 0; i < q.length - 1; i++) {

                    if (q[i] > q[i + 1]) {
                        if (swaps[q[i]] < 2) {
                            swaps[q[i]]++;
                            totalSwaps++;
                            int temp = q[i];
                            q[i] = q[i + 1];
                            q[i + 1] = temp;
                        } else {
                            tooChaotic = true;
                        }
                    }
                }
            }
            if (!tooChaotic && isSorted(q)) {
                System.out.println(totalSwaps);
            } else {
                System.out.println("Too chaotic");
            }
        }
    }

    public static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int tItr = 0; tItr < t; tItr++) {
                int n = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                int[] q = new int[n];

                String[] qItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int i = 0; i < n; i++) {
                    int qItem = Integer.parseInt(qItems[i]);
                    q[i] = qItem;
                }

                minimumBribes(q);
            }
        }
    }
}
