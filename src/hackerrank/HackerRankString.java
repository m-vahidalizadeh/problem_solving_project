package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Checks if the word HackerRank exists in a String.
 */
public class HackerRankString {
    private static Pattern MY_PATTERN = Pattern.compile("[a-z]*h[a-z]*a[a-z]*c[a-z]*k[a-z]*e[a-z]*r[a-z]*r[a-z]*a[a-z]*n[a-z]*k[a-z]*");

    static String hackerrankInString(String s) {
        return MY_PATTERN.asPredicate().test(s) ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/hris.out")));
             Scanner scanner = new Scanner(System.in)) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int qItr = 0; qItr < q; qItr++) {
                String s = scanner.nextLine();
                String result = hackerrankInString(s);
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
        }
    }
}
