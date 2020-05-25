package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Flips the bits of a decimal Long.
 */
public class FlippingBits {

    public static final char ONE_CHAR = '1';
    public static final char ZERO_CHAR = '0';

    static long flippingBits(long n) {
        String nBinary = String.format("%32s", Long.toBinaryString(n));
        char[] result = new char[nBinary.length()];
        char[] nBinaryChars = nBinary.toCharArray();
        for (int i = 0; i < nBinaryChars.length; i++) {
            result[i] = nBinaryChars[i] == ONE_CHAR ? ZERO_CHAR : ONE_CHAR;
        }
        return Long.parseLong(String.valueOf(result), 2);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/leetcode.companies.fb.out")));
             Scanner scanner = new Scanner(System.in)) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int qItr = 0; qItr < q; qItr++) {
                long n = scanner.nextLong();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                long result = flippingBits(n);
                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }
}
