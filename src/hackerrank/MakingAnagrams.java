package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MakingAnagrams {

    static int makingAnagrams(String s1, String s2) {
        int[] s1Frequencies = new int[26];
        int[] s2Frequencies = new int[26];
        int differences = 0;
        for (int i = 0; i < s1.length(); i++) {
            s1Frequencies[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            s2Frequencies[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            differences += Math.abs(s1Frequencies[i] - s2Frequencies[i]);
        }
        return differences;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/ma.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            int result = makingAnagrams(s1, s2);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

    }

}
