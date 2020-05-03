package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Adds the solution to check for primality.
 */
public class TimeComplexityPrimality {

    static String primality(int n) {
        if (n < 2)
            return "Not prime";
        if (n == 2 || n == 3)
            return "Prime";
        if (n % 2 == 0 || n % 3 == 0)
            return "Not prime";
        int sqrtPlusOne = Double.valueOf(Math.sqrt(n)).intValue() + 1;
        for (int i = 6; i <= sqrtPlusOne; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0)
                return "Not prime";
        }
        return "Prime";
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/tcp.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int p = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int pItr = 0; pItr < p; pItr++) {
                int n = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                String result = primality(n);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
        }
    }

}
