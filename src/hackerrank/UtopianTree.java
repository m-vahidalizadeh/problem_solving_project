package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Solves the Utopian Tree problem.
 */
public class UtopianTree {
    static int utopianTree(int n) {
        int height = 1;
        if (n > 0) {
            for (int a = 1; a <= n; a++) {
                if (a % 2 == 0) {
                    height++;
                } else {
                    height = height * 2;
                }
            }
        }
        return height;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/ut.out")));
             Scanner scanner = new Scanner(System.in)) {
            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int tItr = 0; tItr < t; tItr++) {
                int n = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                int result = utopianTree(n);
                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }
}
