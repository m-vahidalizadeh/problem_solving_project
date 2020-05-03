package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ElectronicsShop {

    static int getMoneySpent(int[] keyboards, int[] drives, int b) {
        int n = keyboards.length;
        int m = drives.length;
        Set<Integer> solutions = new HashSet();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int pairCost = keyboards[i] + drives[j];
                if (pairCost <= b) {
                    solutions.add(pairCost);
                }
            }
        }
        if (solutions.isEmpty()) {
            return -1;
        }
        return solutions.stream().max(Integer::compareTo).get();
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/es.out")));
                Scanner scanner = new Scanner(System.in)
        ) {

            int b = scanner.nextInt();

            int n = scanner.nextInt();

            int m = scanner.nextInt();

            int[] keyboards = new int[n];

            for (int i = 0; i < n; i++) {
                keyboards[i] = scanner.nextInt();
            }

            int[] drives = new int[m];
            for (int i = 0; i < m; i++) {
                drives[i] = scanner.nextInt();
            }

            /*
             * The maximum amount of money she can spend on a keyboard and USB drive, or -1 if she can't purchase both items
             */

            int moneySpent = getMoneySpent(keyboards, drives, b);

            bufferedWriter.write(String.valueOf(moneySpent));
            bufferedWriter.newLine();
        }

    }

}
