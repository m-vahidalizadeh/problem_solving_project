package hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Prints minimum and maximum summation os 4 elements in a 5 elements array.
 */
public class MiniMaxSum {

    // Complete the miniMaxSum function below.
    static void miniMaxSum(int[] arr) {
        Arrays.sort(arr);
        BigInteger result = new BigInteger("0");
        for (int i = 0; i < arr.length - 1; i++) {
            result = result.add(BigInteger.valueOf(arr[i]));
        }
        System.out.print(result);
        result = new BigInteger("0");
        Integer[] arrInteger = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrInteger[i] = arr[i];
        }
        Arrays.sort(arrInteger, Collections.reverseOrder());
        for (int i = 0; i < arr.length - 1; i++) {
            result = result.add(BigInteger.valueOf(arrInteger[i]));
        }
        System.out.print(" " + result);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("iofiles/mms.in"));) {
            int[] arr = new int[5];
            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < 5; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }
            miniMaxSum(arr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
