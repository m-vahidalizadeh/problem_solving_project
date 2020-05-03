package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RunningTimeOfAlgorithms {
    static int runningTime(int[] arr) {
        int i, key, j;
        int counter = 0;
        for (i = 1; i < arr.length; i++)
        {
            key = arr[i];
            j = i-1;
            while (j >= 0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
                counter++;
            }
            arr[j+1] = key;
        }
        return counter;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("iofiles/rtoa.out")));
             Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] arr = new int[n];
            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }
            int result = runningTime(arr);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
