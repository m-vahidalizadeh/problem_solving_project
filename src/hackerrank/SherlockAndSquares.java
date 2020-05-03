package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SherlockAndSquares {

    static int squares(int a, int b) {
        return Double.valueOf(Math.floor(Math.sqrt(b)) - Math.ceil(Math.sqrt(a)) + 1).intValue();
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/sas.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int qItr = 0; qItr < q; qItr++) {
                String[] ab = scanner.nextLine().split(" ");

                int a = Integer.parseInt(ab[0]);

                int b = Integer.parseInt(ab[1]);

                int result = squares(a, b);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }

}
