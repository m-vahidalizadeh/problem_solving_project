package hackerrank;

import java.io.*;
import java.util.*;

public class LeftRotation {

    static int[] rotLeft(int[] a, int d) {
        int n = a.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int index = i - d;
            if (index < 0) {
                index += n;
            }
            result[index] = a[i];
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/lr.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String[] nd = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nd[0]);

            int d = Integer.parseInt(nd[1]);

            int[] a = new int[n];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int aItem = Integer.parseInt(aItems[i]);
                a[i] = aItem;
            }

            int[] result = rotLeft(a, d);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }
    }

}
