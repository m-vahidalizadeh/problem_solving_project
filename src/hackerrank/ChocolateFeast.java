package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ChocolateFeast {

    static int chocolateFeast(int n, int c, int m) {
        boolean canBuy = true;
        int totalEatenChocolates = 0;
        int wrappers = 0;
        while (canBuy) {
            canBuy = false;
            int canAfford = Double.valueOf(Math.ceil(n / c)).intValue();
            if (canAfford > 0) {
                canBuy = true;
                totalEatenChocolates = totalEatenChocolates + canAfford;
                n = n - (canAfford * c);
                wrappers = wrappers + canAfford;
            }
            if (wrappers >= m) {
                canAfford = Double.valueOf(Math.ceil(wrappers / m)).intValue();
                totalEatenChocolates = totalEatenChocolates + canAfford;
                wrappers = wrappers - canAfford;
                canBuy = true;
            }
        }
        return totalEatenChocolates;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/cf.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int tItr = 0; tItr < t; tItr++) {
                String[] ncm = scanner.nextLine().split(" ");

                int n = Integer.parseInt(ncm[0]);

                int c = Integer.parseInt(ncm[1]);

                int m = Integer.parseInt(ncm[2]);

                int result = chocolateFeast(n, c, m);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }

}
