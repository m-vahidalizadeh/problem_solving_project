package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Solves the Viral Advertising problem.
 */
public class ViralAdvertising {

    // Complete the viralAdvertising function below.
    static int viralAdvertising(int n) {
        int numberOfRecepiants = 5;
        int cumulative = 0;
        for (int i = 1; i <= n; i++) {
            int numberOfLikes = Double.valueOf(Math.floor(numberOfRecepiants / 2)).intValue();
            cumulative += numberOfLikes;
            numberOfRecepiants = numberOfLikes * 3;
        }
        return cumulative;
    }

    public static void main(String[] args) throws IOException {
        try (
                Scanner scanner = new Scanner(System.in);
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/va.out")))
        ) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int result = viralAdvertising(n);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
