package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Solves the counting valleys problem.
 */
public class CountingValleys {

    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
        int numberOfValleys = 0;
        for (int index = 0; index < s.length(); index++) {
            if ("U".equals(String.valueOf(s.charAt(index)))) {
                int balance = 1;
                while (index + 1 < s.length() && balance != 0) {
                    index++;
                    if ("U".equals(String.valueOf(s.charAt(index)))) {
                        balance++;
                    } else if ("D".equals(String.valueOf(s.charAt(index)))) {
                        balance--;
                    }
                }
            } else if ("D".equals(String.valueOf(s.charAt(index)))) {
                int balance = -1;
                while (index + 1 < s.length() && balance != 0) {
                    index++;
                    if ("U".equals(String.valueOf(s.charAt(index)))) {
                        balance++;
                    } else if ("D".equals(String.valueOf(s.charAt(index)))) {
                        balance--;
                    }
                }
                if (balance == 0) {
                    numberOfValleys++;
                }
            } else {
                // The character is not recognized.
            }
        }
        return numberOfValleys;
    }

    public static void main(String[] args) throws IOException {

        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/cv.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String s = scanner.nextLine();

            int result = countingValleys(n, s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

    }

}
