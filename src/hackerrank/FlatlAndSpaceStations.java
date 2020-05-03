package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FlatlAndSpaceStations {

    static int flatlandSpaceStations(int n, int[] c) {
        boolean[] cities = new boolean[n];
        for (int i = 0; i < c.length; i++) {
            cities[c[i]] = true;
        }
        int[] distances = new int[n];
        for (int i = 0; i < n; i++) {
            if (cities[i]) {
                distances[i] = 0;
            } else {
                boolean found = false;
                // Traverse left
                int j = i - 1;
                int minLeft = n - 1;
                while (!found) {
                    if (j < 0) {
                        found = true;
                    } else {
                        if (cities[j]) {
                            minLeft = Math.abs(j - i);
                            found = true;
                        }
                    }
                    j--;
                }
                // Traverse right
                found = false;
                j = i + 1;
                int minRight = n - 1;
                while (!found) {
                    int tempDistance = Math.abs(j - i);
                    if (j >= n) {
                        found = true;
                    } else {
                        if (cities[j]) {
                            minRight = tempDistance;
                            found = true;
                        }
                    }
                    if (tempDistance > minLeft) {
                        found = true;
                        minRight = tempDistance;
                    }
                    j++;
                }
                distances[i] = Math.min(minLeft, minRight);
            }
        }
        return Arrays.stream(distances).max().getAsInt();
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/fass.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[] c = new int[m];

            String[] cItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < m; i++) {
                int cItem = Integer.parseInt(cItems[i]);
                c[i] = cItem;
            }

            int result = flatlandSpaceStations(n, c);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
