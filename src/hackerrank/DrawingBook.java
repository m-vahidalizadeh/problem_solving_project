package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DrawingBook {

    static int pageCount(int n, int p) {
        if (p == 1) {
            return 0;
        }
        int remainder = n % 2;
        if (remainder == 0) {
            if (p == n) {
                return 0;
            }
        } else {
            if (p == n || p == n - 1) {
                return 0;
            }
        }
        // Traverse left
        int leftIndex = 1;
        boolean found = false;
        int minLeft = 0;
        while (!found) {
            leftIndex = leftIndex + 2;
            minLeft++;
            if (p == leftIndex || p == leftIndex - 1) {
                found = true;
            }
        }
        // Traverse right
        int rightIndex = n;
        if (remainder == 0) {
            rightIndex--;
        } else {
            rightIndex = rightIndex - 2;
        }
        found = false;
        int minRight = 1;
        while (!found) {
            if (p == rightIndex || p == rightIndex - 1) {
                found = true;
            } else {
                minRight++;
                rightIndex = rightIndex - 2;
            }
        }
        return Math.min(minLeft, minRight);
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/db.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

            int p = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

            int result = pageCount(n, p);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
