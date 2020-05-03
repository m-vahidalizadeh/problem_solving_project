package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LoveLetterMystery {

    static int theLoveLetterMystery(String s) {
        int n = s.length();
        int minChanges = 0;
        int mid = n / 2;
        for (int i = 0; i < mid; i++) {
            String left = String.valueOf(s.charAt(i)).toLowerCase();
            String right = String.valueOf(s.charAt(n - i - 1)).toLowerCase();
            minChanges += Math.abs(left.charAt(0) - right.charAt(0));
        }
        return minChanges;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/llm.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int qItr = 0; qItr < q; qItr++) {
                String s = scanner.nextLine();

                int result = theLoveLetterMystery(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }

}
