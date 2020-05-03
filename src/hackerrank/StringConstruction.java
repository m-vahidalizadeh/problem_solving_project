package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Copy a String to another String.
 */
public class StringConstruction {
    static int stringConstruction(String s) {
        Set<Character> p = new HashSet<>();
        int cost = 0;
        for (int a = 0; a < s.length(); a++) {
            Character c = s.charAt(a);
            if (!p.contains(c)) {
                cost++;
                p.add(c);
            }
        }
        return cost;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/sc.out")));
             Scanner scanner = new Scanner(System.in)) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int qItr = 0; qItr < q; qItr++) {
                String s = scanner.nextLine();
                int result = stringConstruction(s);
                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }
}
