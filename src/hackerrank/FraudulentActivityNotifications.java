package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FraudulentActivityNotifications {

    static int activityNotifications(int[] expenditure, int d) {
        int notifications = 0;
        for (int i = d; i < expenditure.length; i++) {
            int[] history = Arrays.copyOfRange(expenditure, i - d, i);
            int expenditureI = expenditure[i];
            if (isCandidate(history, expenditureI)) {
                double median = getMedian(history);
                if (expenditureI >= 2 * median) {
                    notifications++;
                }
            }
        }
        return notifications;
    }

    private static boolean isCandidate(int[] input, int expenditureI) {
        double halfOfExpenditureI = expenditureI / 2.0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] >= halfOfExpenditureI) {
                return true;
            }
        }

        return false;
    }

    private static double getMedian(int[] input) {
        Arrays.sort(input);
        if (input.length % 2 == 0)
            return ((double) input[input.length / 2] + (double) input[input.length / 2 - 1]) / 2;

        return (double) input[input.length / 2];
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/fan.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String[] nd = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nd[0]);

            int d = Integer.parseInt(nd[1]);

            int[] expenditure = new int[n];

            String[] expenditureItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int expenditureItem = Integer.parseInt(expenditureItems[i]);
                expenditure[i] = expenditureItem;
            }

            int result = activityNotifications(expenditure, d);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
