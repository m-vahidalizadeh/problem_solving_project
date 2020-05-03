package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BeautifulDaysAtTheMovies {

    static int beautifulDays(int i, int j, int k) {
        int counter = 0;
        for (int a = i; a <= j; a++) {
            int aReverse = Integer.valueOf(new StringBuilder(String.valueOf(a)).reverse().toString());
            if (Math.abs(a - aReverse) % k == 0) {
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/bdam.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String[] ijk = scanner.nextLine().split(" ");

            int i = Integer.parseInt(ijk[0]);

            int j = Integer.parseInt(ijk[1]);

            int k = Integer.parseInt(ijk[2]);

            int result = beautifulDays(i, j, k);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
