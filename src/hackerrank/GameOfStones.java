package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GameOfStones {

    static String gameOfStones(int n) {
        int winner = -1;
        int currentPlayer = 1;
        while (winner == -1) {
            if (n < 2) {
                return currentPlayer == 2 ? "First" : "Second";
            }
            int tempN = n;
            if (tempN - 2 < 2) {
                winner = currentPlayer;
            } else if (tempN - 3 < 2) {
                winner = currentPlayer;
            } else if (tempN - 5 < 2) {
                winner = currentPlayer;
            }
            if (winner == -1) {
                // Prevent losing if possible
                if (!((tempN - 2 - 2 < 2) || (tempN - 2 - 3 < 2) || (tempN - 2 - 5 < 2))) {
                    n = n - 2;
                } else if (!((tempN - 3 - 2 < 2) || (tempN - 3 - 3 < 2) || (tempN - 3 - 5 < 2))) {
                    n = n - 3;
                } else if (!((tempN - 5 - 2 < 2) || (tempN - 5 - 3 < 2) || (tempN - 5 - 5 < 2))) {
                    n = n - 5;
                } else {
                    n = n - 2;
                    return currentPlayer == 2 ? "First" : "Second";
                }
            }
            // Change player
            if (currentPlayer == 1) {
                currentPlayer = 2;
            } else {
                currentPlayer = 1;
            }
        }
        return winner == 1 ? "First" : "Second";
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/gos.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int tItr = 0; tItr < t; tItr++) {
                int n = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                String result = gameOfStones(n);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
        }
    }

}
