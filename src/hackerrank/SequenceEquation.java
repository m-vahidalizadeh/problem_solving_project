package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SequenceEquation {

    static int[] permutationEquation(int[] p) {
        int n = p.length;
        Map<Integer, Integer> locations = new HashMap<>();
        for (int i = 0; i < n; i++) {
            locations.put(p[i], i + 1);
        }
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            y[i] = locations.get(locations.get(i + 1));
        }
        return y;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/se.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] p = new int[n];

            String[] pItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int pItem = Integer.parseInt(pItems[i]);
                p[i] = pItem;
            }

            int[] result = permutationEquation(p);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write("\n");
                }
            }

            bufferedWriter.newLine();
        }
    }

}
