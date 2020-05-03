package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TwoStrings {

    static String twoStrings(String s1, String s2) {
        boolean[] charOccurances = new boolean[26];
        Arrays.fill(charOccurances, false);
        if (s1.length() < s2.length()) {
            for (int i = 0; i < s1.length(); i++) {
                charOccurances[s1.charAt(i) - 'a'] = true;
            }
            for (int i = 0; i < s2.length(); i++) {
                if (charOccurances[s2.charAt(i) - 'a']) {
                    return "YES";
                }
            }
        } else {
            for (int i = 0; i < s2.length(); i++) {
                charOccurances[s2.charAt(i) - 'a'] = true;
            }
            for (int i = 0; i < s1.length(); i++) {
                if (charOccurances[s1.charAt(i) - 'a']) {
                    return "YES";
                }
            }
        }
        return "NO";
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/ts.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int qItr = 0; qItr < q; qItr++) {
                String s1 = scanner.nextLine();

                String s2 = scanner.nextLine();

                String result = twoStrings(s1, s2);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
        }
    }

}
