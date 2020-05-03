package hackerrank;

import java.io.*;
import java.util.*;


public class AVeryBigSum {

    static long aVeryBigSum(long[] ar) {
        /*
Input:
5
1000000001 1000000002 1000000003 1000000004 1000000005
Output:
5000000015
         */
        return Arrays.stream(ar).sum();
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/avbs.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int arCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            long[] ar = new long[arCount];

            String[] arItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < arCount; i++) {
                long arItem = Long.parseLong(arItems[i]);
                ar[i] = arItem;
            }

            long result = aVeryBigSum(ar);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
