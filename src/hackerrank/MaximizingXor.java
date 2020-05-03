package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Solves Maximizing XOR problem.
 */
public class MaximizingXor {
    static int maximizingXor(int l, int r) {
        int maximum = Integer.MIN_VALUE;
        for (int a = l; a <= r; a++) {
            for (int b = a; b <= r; b++) {
                int xor = a ^ b;
                if (maximum < xor) {
                    maximum = xor;
                }
            }
        }
        return maximum;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/mx.out")));
             Scanner scanner = new Scanner(System.in)) {
            int l = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int r = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int result = maximizingXor(l, r);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}