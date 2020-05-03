package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Finds the differences of two arrays
 */
public class MissingNumbers {

    static int[] missingNumbers(int[] arr, int[] brr) {
        int arrMax = Arrays.stream(arr).max().getAsInt();
        int[] arrOccurances = new int[arrMax + 1];
        for (int a = 0; a < arr.length; a++) {
            arrOccurances[arr[a]]++;
        }
        int brrMax = Arrays.stream(brr).max().getAsInt();
        int[] brrOccurances = new int[brrMax + 1];
        for (int a = 0; a < brr.length; a++) {
            brrOccurances[brr[a]]++;
        }
        Set<Integer> result = new TreeSet<>();
        int max = Math.max(arrMax, brrMax);
        for (int a = 1; a <= max; a++) {
            if (a > arrMax) {
                result.add(a);
            } else if (a > brrMax) {
                result.add(a);
            } else if (arrOccurances[a] != brrOccurances[a]) {
                result.add(a);
            }
        }
        Integer[] resultArray = result.toArray(new Integer[result.size()]);
        return Arrays.stream(resultArray).mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/mn.out")));
             Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] arr = new int[n];
            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }
            int m = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] brr = new int[m];
            String[] brrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < m; i++) {
                int brrItem = Integer.parseInt(brrItems[i]);
                brr[i] = brrItem;
            }
            int[] result = missingNumbers(arr, brr);
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
