package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

// The Hardle race.
public class HurdleRace {

    static int hurdleRace(int k, int[] height) {
        int maxHurdle = Arrays.stream(height).max().getAsInt();
        return k >= maxHurdle ? 0 : maxHurdle - k;
    }

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/hr.out")))) {
            String[] nk = scanner.nextLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            int[] height = new int[n];
            String[] heightItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < n; i++) {
                int heightItem = Integer.parseInt(heightItems[i]);
                height[i] = heightItem;
            }
            int result = hurdleRace(k, height);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
