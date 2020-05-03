package hackerrank;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class SMMakingAnagrams {

    static int makeAnagram(String a, String b) {
        // Build the freq map of a
        a = a.toLowerCase();
        int[] freqA = new int[26];
        for (char ai : a.toCharArray()) {
            freqA[ai - 'a']++;
        }
        // Build the freq map of b
        b = b.toLowerCase();
        int[] freqB = new int[26];
        for (char bi : b.toCharArray()) {
            freqB[bi - 'a']++;
        }
        // Sum up the differences of the entries of the freq maps
        int differences = 0;
        for (int i = 0; i < 26; i++) {
            differences += Math.abs(freqA[i] - freqB[i]);
        }
        // Return the summation
        return differences;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/smma.out")));
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
