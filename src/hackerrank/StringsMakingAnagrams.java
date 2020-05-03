package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Finds the difference of the frequencies of the characters between two words.
 */
public class StringsMakingAnagrams {

    static int makeAnagram(String a, String b) {
        int[] aFrequencies = new int[26];
        int[] bFrequencies = new int[26];
        for (int i = 0; i < a.length(); i++) {
            aFrequencies[a.charAt(i) - 'a']++;
        }
        for (int i = 0; i < b.length(); i++) {
            bFrequencies[b.charAt(i) - 'a']++;
        }
        int totalDeletions = 0;
        for (int i = 0; i < 26; i++) {
            totalDeletions += Math.abs(aFrequencies[i] - bFrequencies[i]);
        }
        return totalDeletions;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/sma.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            int res = makeAnagram(a, b);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }
    }

}
