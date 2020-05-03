package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Solves service lane problem.
 */
public class ServiceLane {
    static int[] serviceLane(int n, int[][] cases, int[] width) {
        int[] result = new int[cases.length];
        for (int a = 0; a < cases.length; a++) {
            int i = cases[a][0];
            int j = cases[a][1];
            int minimum = width[i];
            for (int b = i + 1; b <= j; b++) {
                if (width[b] < minimum) {
                    minimum = width[b];
                }
            }
            result[a] = minimum;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/sl.out")));
             Scanner scanner = new Scanner(System.in)) {
            String[] nt = scanner.nextLine().split(" ");
            int n = Integer.parseInt(nt[0]);
            int t = Integer.parseInt(nt[1]);
            int[] width = new int[n];
            String[] widthItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < n; i++) {
                int widthItem = Integer.parseInt(widthItems[i]);
                width[i] = widthItem;
            }
            int[][] cases = new int[t][2];
            for (int i = 0; i < t; i++) {
                String[] casesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                for (int j = 0; j < 2; j++) {
                    int casesItem = Integer.parseInt(casesRowItems[j]);
                    cases[i][j] = casesItem;
                }
            }
            int[] result = serviceLane(n, cases, width);
            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write("\n");
                }
            }
            bufferedWriter.newLine();
        }
    }
}
