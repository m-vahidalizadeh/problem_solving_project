package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Counts the number of words in a camel case String.
 */
public class CamelCase {

    static int camelcase(String s) {
        return Long.valueOf(s.chars().filter(c -> Character.isUpperCase(c)).count()).intValue() + 1;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/cc.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String s = scanner.nextLine();
            int result = camelcase(s);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
