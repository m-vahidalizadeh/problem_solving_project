package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LibraryFine {

    static int libraryFine(int d1, int m1, int y1, int d2, int m2, int y2) {
        int yearDiff = y1 - y2;
        int monthDiff = m1 - m2;
        int dayDiff = d1 - d2;
        if (yearDiff > 0) {
            return 10_000;
        }
        if (yearDiff < 0 || monthDiff < 0 || (monthDiff == 0 && dayDiff <= 0)) {
            return 0;
        }
        if (monthDiff == 0) {
            return dayDiff * 15;
        }
        return monthDiff * 500;
    }

    public static void main(String[] args) throws IOException {

        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/lf.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String[] d1M1Y1 = scanner.nextLine().split(" ");

            int d1 = Integer.parseInt(d1M1Y1[0]);

            int m1 = Integer.parseInt(d1M1Y1[1]);

            int y1 = Integer.parseInt(d1M1Y1[2]);

            String[] d2M2Y2 = scanner.nextLine().split(" ");

            int d2 = Integer.parseInt(d2M2Y2[0]);

            int m2 = Integer.parseInt(d2M2Y2[1]);

            int y2 = Integer.parseInt(d2M2Y2[2]);

            int result = libraryFine(d1, m1, y1, d2, m2, y2);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

    }

}
