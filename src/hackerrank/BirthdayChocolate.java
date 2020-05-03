package hackerrank;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BirthdayChocolate {

    static int birthday(List<Integer> s, int d, int m) {
        int[] sArray = s.stream().mapToInt(Integer::intValue).toArray();
        int n = sArray.length;
        int nMinusM = n - m;
        int solutionCounter = 0;
        if (n < m) {
            return solutionCounter;
        }
        if (n == m) {
            if (Arrays.stream(sArray).sum() == d) {
                solutionCounter++;
            }
            return solutionCounter;
        }
        for (int i = 0; i <= nMinusM; i++) {
            int summation = 0;
            for (int j = i; j < i + m; j++) {
                summation += sArray[j];
            }
            if (summation == d) {
                solutionCounter++;
            }
        }
        return solutionCounter;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/bc.out")));
        ) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            String[] dm = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int d = Integer.parseInt(dm[0]);

            int m = Integer.parseInt(dm[1]);

            int result = birthday(s, d, m);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
