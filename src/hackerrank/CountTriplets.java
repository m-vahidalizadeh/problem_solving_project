package hackerrank;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Adds the solution of counter triplets problem.
 */
public class CountTriplets {

    static long countTriplets(List<Long> arr, long r) {
        // Solution 2
        long counter = 0;
        int n = arr.size();
        Long[] arrArray = arr.toArray(new Long[n]);

        Long[][] ratios = new Long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ratios[i][j] = arrArray[j] / arrArray[i];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ratios[i][j] == r) {
                    for (int k = j + 1; k < n; k++) {
                        if (ratios[j][k] == r) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) throws IOException {

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader("iofiles/ct.in"));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("iofiles/ct.out"));
        ) {
            String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            int n = Integer.parseInt(nr[0]);
            long r = Long.parseLong(nr[1]);
            List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Long::parseLong)
                    .collect(toList());
            long ans = countTriplets(arr, r);
            bufferedWriter.write(String.valueOf(ans));
            bufferedWriter.newLine();
        }
    }

}
