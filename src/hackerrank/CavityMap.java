package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CavityMap {

    static String[] cavityMap(String[] grid) {
        int n = grid.length;
        int[][] input = new int[n][n];
        String[][] result = new String[n][n];
        String[] output = new String[n];
        // Make matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                input[i][j] = Integer.parseInt(String.valueOf(grid[i].charAt(j)));
            }
        }
        // Recognize cavity
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tempElement = input[i][j];
                if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
                    result[i][j] = String.valueOf(tempElement);
                } else if (
                        tempElement > input[i][j - 1]
                                && tempElement > input[i - 1][j]
                                && tempElement > input[i][j + 1]
                                && tempElement > input[i + 1][j]
                ) {
                    result[i][j] = "X";
                } else {
                    result[i][j] = String.valueOf(tempElement);
                }
            }
        }
        // Assemble
        for (int i = 0; i < n; i++) {
            output[i] = "";
            for (int j = 0; j < n; j++) {
                output[i] += result[i][j];
            }
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/cm.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String[] grid = new String[n];

            for (int i = 0; i < n; i++) {
                String gridItem = scanner.nextLine();
                grid[i] = gridItem;
            }

            String[] result = cavityMap(grid);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(result[i]);

                if (i != result.length - 1) {
                    bufferedWriter.write("\n");
                }
            }

            bufferedWriter.newLine();
        }
    }

}
