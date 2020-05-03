package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GameOfThrones {

    static String gameOfThrones(String s) {
        int[] freq = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            freq[chars[i] - 'a']++;
        }
        int odds = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                odds++;
            }
            if (odds > 1) {
                return "NO";
            }
        }
        return "YES";
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/got.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String s = scanner.nextLine();

            String result = gameOfThrones(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }
    }

}
