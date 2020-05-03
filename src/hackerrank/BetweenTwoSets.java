package hackerrank;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BetweenTwoSets {

    public static int getTotalX(List<Integer> a, List<Integer> b) {
        int counter = 0;
        int[] aArray = a.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(aArray);
        int aN = aArray.length;
        int[] bArray = b.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(bArray);
        int bN = bArray.length;
        int min = Math.min(aArray[0], bArray[0]);
        int max = Math.max(aArray[aN - 1], bArray[bN - 1]);
        for (int i = min; i <= max; i++) {
            boolean aFlag = true;
            for (int j = 0; j < aN; j++) {
                if (i % aArray[j] != 0) {
                    aFlag = false;
                    break;
                }
            }
            if (!aFlag) {
                continue;
            }
            boolean bFlag = true;
            for (int j = 0; j < bN; j++) {
                if (bArray[j] % i != 0) {
                    bFlag = false;
                    break;
                }
            }
            if (aFlag && bFlag) {
                counter++;
            }
        }

        return counter;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/bts.out")));
        ) {
            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int m = Integer.parseInt(firstMultipleInput[1]);

            String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> arr = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrTemp[i]);
                arr.add(arrItem);
            }

            String[] brrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> brr = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                int brrItem = Integer.parseInt(brrTemp[i]);
                brr.add(brrItem);
            }

            int total = getTotalX(arr, brr);

            bufferedWriter.write(String.valueOf(total));
            bufferedWriter.newLine();
        }
    }

}



