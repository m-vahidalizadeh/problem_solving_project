package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BirthdayCakeCandles {

    static int birthdayCakeCandles(int[] ar) {
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> occuranceFrequencies = new HashMap<>();
        for (int i = 0; i < ar.length; i++) {
            int tempElement = ar[i];
            if (max < tempElement) {
                max = tempElement;
            }
            if (occuranceFrequencies.containsKey(tempElement)) {
                occuranceFrequencies.put(tempElement, occuranceFrequencies.get(tempElement) + 1);
            } else {
                occuranceFrequencies.put(tempElement, 1);
            }
        }
        return occuranceFrequencies.get(max);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/bcc.out")));
                Scanner scanner = new Scanner(System.in)
        ) {

            int arCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] ar = new int[arCount];

            String[] arItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < arCount; i++) {
                int arItem = Integer.parseInt(arItems[i]);
                ar[i] = arItem;
            }

            int result = birthdayCakeCandles(ar);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
