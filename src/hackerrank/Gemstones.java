package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Finds the common letters among some Strings.
 */
public class Gemstones {
    static int gemstones(String[] arr) {
        Set<Character> commonChars = new HashSet<>();
        arr[0].chars().forEach(c -> commonChars.add((char) c));
        for (int a = 1; a < arr.length; a++) {
            Set<Character> tempCommonChars = new HashSet<>();
            Set<Character> removableChars = new HashSet<>();
            arr[a].chars().forEach(c -> tempCommonChars.add((char) c));
            commonChars.stream().filter(c -> !tempCommonChars.contains(c)).forEach(c -> removableChars.add(c));
            removableChars.stream().forEach(c -> commonChars.remove(c));
        }
        return commonChars.size();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/gs.out")));
             Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                String arrItem = scanner.nextLine();
                arr[i] = arrItem;
            }
            int result = gemstones(arr);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
