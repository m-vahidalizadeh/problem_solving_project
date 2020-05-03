package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Finds the index of a char in a String.
 */
public class FindingIndexOfChar {
    static int introTutorial(int V, int[] arr) {
        return IntStream.range(1, arr.length).filter(i -> arr[i] == V).findFirst().getAsInt();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/fioc.out")));
             Scanner scanner = new Scanner(System.in)) {
            int V = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] arr = new int[n];
            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }
            int result = introTutorial(V, arr);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }
}
