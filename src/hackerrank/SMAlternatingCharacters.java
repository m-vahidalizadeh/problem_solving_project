package hackerrank;

import java.io.*;
import java.util.*;

public class SMAlternatingCharacters {

    static int alternatingCharacters(String s) {
        int n = s.length();
        if (n == 0 || n == 1) {
            return 0;
        }
        int counter = 0;
        char[] sChars = s.toCharArray();
        char prevChar = sChars[0];
        for (int i = 1; i < n; i++) {
            if (sChars[i] == prevChar) {
                counter++;
            } else {
                prevChar = sChars[i];
            }
        }
        return counter;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/smac.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int qItr = 0; qItr < q; qItr++) {
                String s = scanner.nextLine();

                int result = alternatingCharacters(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }

}
