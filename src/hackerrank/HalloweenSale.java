package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Solves the Halloween Sale problem.
 */
public class HalloweenSale {
    static int howManyGames(int p, int d, int m, int s) {
        int numberOfBoughtGames = 0;
        int currentPrice = p;
        if (s < p) {
            return 0;
        } else if (s == p) {
            return 1;
        }
        while (s >= m) {
            if (numberOfBoughtGames > 0) {
                if (s < currentPrice) {
                    return numberOfBoughtGames;
                }
                if (currentPrice > m) {
                    s = s - currentPrice;
                    currentPrice = currentPrice - d;
                    if (currentPrice <= m) {
                        currentPrice = m;
                    }
                    numberOfBoughtGames++;
                } else {
                    s = s - currentPrice;
                    numberOfBoughtGames++;
                }
            } else {
                s = s - currentPrice;
                currentPrice = p - d;
                if (currentPrice <= m) {
                    currentPrice = m;
                }
                numberOfBoughtGames++;
            }
        }
        return numberOfBoughtGames;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/hs.out")));
             Scanner scanner = new Scanner(System.in)) {
            String[] pdms = scanner.nextLine().split(" ");
            int p = Integer.parseInt(pdms[0]);
            int d = Integer.parseInt(pdms[1]);
            int m = Integer.parseInt(pdms[2]);
            int s = Integer.parseInt(pdms[3]);
            int answer = howManyGames(p, d, m, s);
            bufferedWriter.write(String.valueOf(answer));
            bufferedWriter.newLine();
        }
    }
}
