package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Panagrams {

    static String pangrams(String s) {
        Set<String> foundChars = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            Character foundChar = s.charAt(i);
            if (Character.isLetter(foundChar)) {
                String foundCharStr = String.valueOf(foundChar).toLowerCase();
                foundChars.add(foundCharStr);
            }
        }
        return foundChars.size() == 26 ? "pangram" : "not pangram";
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/panagrams.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String s = scanner.nextLine();

            String result = pangrams(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }
    }

}
