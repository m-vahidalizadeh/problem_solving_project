package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashTablesIceCreamParlor {

    static void whatFlavors(int[] cost, int money) {
        Map<Integer, Integer> visitedNumbers = new HashMap();
        for (int i = 0; i < cost.length; i++) {
            int complement = money - cost[i];
            int iPlusOne = i + 1;
            if (visitedNumbers.containsKey(complement)) {
                int complementFlavour = visitedNumbers.get(complement);
                if (iPlusOne > complementFlavour) {
                    System.out.println(complementFlavour + " " + iPlusOne);
                } else {
                    System.out.println(iPlusOne + " " + complementFlavour);
                }
            }
            visitedNumbers.put(cost[i], iPlusOne);
        }
    }

    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int tItr = 0; tItr < t; tItr++) {
                int money = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                int n = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                int[] cost = new int[n];

                String[] costItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int i = 0; i < n; i++) {
                    int costItem = Integer.parseInt(costItems[i]);
                    cost[i] = costItem;
                }

                whatFlavors(cost, money);
            }
        }
    }

}
