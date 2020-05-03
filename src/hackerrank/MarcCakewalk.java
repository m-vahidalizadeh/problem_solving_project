package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MarcCakewalk {

    static long marcsCakewalk(int[] calorie) {
        Integer[] calorieIntegers = Arrays.stream(calorie).boxed().toArray(Integer[]::new);
        Arrays.sort(calorieIntegers, Collections.reverseOrder());
        long steps = 0;
        for (int i = 0; i < calorieIntegers.length; i++) {
            steps += calorieIntegers[i] * Math.pow(2, i);
        }
        return steps;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/mc.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] calorie = new int[n];

            String[] calorieItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int calorieItem = Integer.parseInt(calorieItems[i]);
                calorie[i] = calorieItem;
            }

            long result = marcsCakewalk(calorie);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
