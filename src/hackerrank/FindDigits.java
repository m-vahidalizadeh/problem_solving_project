package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FindDigits {

    static int findDigits(int n) {
        int counter = 0;
        int number = n;
        while (number > 0) {
            int tempDigit = number % 10;
            if (tempDigit != 0 && n % tempDigit == 0) {
                counter++;
            }
            number = number / 10;
        }
        return counter;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/fd.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int tItr = 0; tItr < t; tItr++) {
                int n = scanner.nextInt();

                int result = findDigits(n);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }

}
