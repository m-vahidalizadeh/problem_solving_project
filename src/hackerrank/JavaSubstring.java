package hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Java substring example.
 */
public class JavaSubstring {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("iofiles/js.in"))) {
            String S = in.next();
            int start = in.nextInt();
            int end = in.nextInt();
            System.out.println(S.substring(start, end));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
