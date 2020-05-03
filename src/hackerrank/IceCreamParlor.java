package hackerrank;

import java.io.*;
import java.util.*;

public class IceCreamParlor {

    static int[] icecreamParlor(int m, int[] arr) {
        Map<Integer, Integer> complements = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < arr.length; i++) {
            int currentElement = arr[i];
            int currentComplement = m - currentElement;
            if (complements.containsKey(currentComplement)) {
                result[0] = complements.get(currentComplement) + 1;
                result[1] = i + 1;
            } else {
                complements.put(currentElement, i);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        /*
Sample Input
2
4
5
1 4 5 3 2
4
4
2 2 4 3
Sample Output
1 4
1 2
         */
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/icp.out")));
             Scanner scanner = new Scanner(System.in)) {
            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int tItr = 0; tItr < t; tItr++) {
                int m = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                int n = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                int[] arr = new int[n];

                String[] arrItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int i = 0; i < n; i++) {
                    int arrItem = Integer.parseInt(arrItems[i]);
                    arr[i] = arrItem;
                }

                int[] result = icecreamParlor(m, arr);

                for (int i = 0; i < result.length; i++) {
                    bufferedWriter.write(String.valueOf(result[i]));

                    if (i != result.length - 1) {
                        bufferedWriter.write(" ");
                    }
                }

                bufferedWriter.newLine();
            }
        }
    }
}