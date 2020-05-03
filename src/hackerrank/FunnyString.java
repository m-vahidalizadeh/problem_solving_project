package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Figures out if the String is funny or not.
 */
public class FunnyString {
    static String funnyString(String s) {
        String reversedS = new StringBuilder(s).reverse().toString();
        for (int a = 0; a < s.length() - 1; a++) {
            if (Math.abs(s.charAt(a) - s.charAt(a + 1)) != Math.abs(reversedS.charAt(a) - reversedS.charAt(a + 1))) {
                return "Not Funny";
            }
        }
        return "Funny";
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/fs.out")));
             Scanner scanner = new Scanner(System.in)) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int qItr = 0; qItr < q; qItr++) {
                String s = scanner.nextLine();
                String result = funnyString(s);
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
        }
    }
}
