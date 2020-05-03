package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Pairs {

    static int pairs(int k, int[] arr) {
        Map<Integer, Integer> pastOccurances = new HashMap<>();
        Map<Integer, Integer> results = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int arrI = arr[i];
            int kMinusArrI = k - arrI < 0 ? arrI - k : k - arrI;
            int kPlusArrI = k + arrI;
            if (pastOccurances.containsKey(kMinusArrI)) {
                Integer resultGetI = results.get(i);
                int pastOccurance = pastOccurances.get(kMinusArrI);
                if (
                        !(
                                (resultGetI != null && results.get(i) == pastOccurance)
                                        ||
                                        (results.containsKey(pastOccurance) && i == pastOccurance)
                        )
                ) {
                    if (resultGetI == null) {
                        results.put(i, pastOccurance);
                    } else {
                        results.put(pastOccurance, i);
                    }
                }
            }
            if (pastOccurances.containsKey(kPlusArrI)) {
                Integer resultGetI = results.get(i);
                int pastOccurance = pastOccurances.get(kPlusArrI);
                if (
                        !(
                                (resultGetI != null && results.get(i) == pastOccurance)
                                        ||
                                        (results.containsKey(pastOccurance) && i == pastOccurance)
                        )
                ) {
                    if (resultGetI == null) {
                        results.put(i, pastOccurance);
                    } else {
                        results.put(pastOccurance, i);
                    }
                }
            }
            pastOccurances.put(arrI, i);
        }
        return results.size();
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/p.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int result = pairs(k, arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
