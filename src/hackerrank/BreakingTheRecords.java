package hackerrank;

import java.io.*;
import java.util.Scanner;

/**
 * Breaking the records min and max example.
 */
public class BreakingTheRecords {

    static int[] breakingRecords(int[] scores) {
        int min = scores[0];
        int max = scores[0];
        int minChangesCount = 0;
        int maxChangesCount = 0;
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
                minChangesCount++;
            } else if (scores[i] > max) {
                max = scores[i];
                maxChangesCount++;
            }
        }
        int[] result = new int[2];
        result[0] = maxChangesCount;
        result[1] = minChangesCount;
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (
                Scanner scanner = new Scanner(new File("iofiles/btr.in"));
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/btr.out")))
        ) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] scores = new int[n];
            String[] scoresItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < n; i++) {
                int scoresItem = Integer.parseInt(scoresItems[i]);
                scores[i] = scoresItem;
            }
            int[] result = breakingRecords(scores);
            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));
                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }
            bufferedWriter.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
