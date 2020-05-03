package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Solves the alternating characters problem.
 */
public class AlternatingCharacters {

    static int alternatingCharacters(String s) {
        int deletions = 0;
        char prevSeenChar = '_';
        for (int i = 0; i < s.length(); i++) {
            if (prevSeenChar != s.charAt(i)) {
                prevSeenChar = s.charAt(i);
            } else {
                deletions++;
            }
        }
        return deletions;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/ac.out")));
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
