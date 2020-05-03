package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RecursionDavisStaircase {

    static int stepPerms(int n) {
        return countWaysUtil(n + 1, 3);
    }

    static int countWaysUtil(int n, int m) {
        int res[] = new int[n];
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i < n; i++) {
            res[i] = 0;
            for (int j = 1; j <= m && j <= i; j++)
                res[i] += res[i - j];
        }
        return res[n - 1];
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/rds.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int sItr = 0; sItr < s; sItr++) {
                int n = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                int res = stepPerms(n);

                bufferedWriter.write(String.valueOf(res));
                bufferedWriter.newLine();
            }
        }
    }

}
