package hackerrank;

import java.util.*;

public class MeanMedianMode {

    static class NumberFrequency implements Comparable {
        int number;
        int frequency;

        NumberFrequency(int number, int frequency) {
            this.number = number;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Object o) {
            NumberFrequency that = (NumberFrequency) o;
            if (this.frequency > that.frequency) {
                return 1;
            } else if (this.frequency < that.frequency) {
                return -1;
            }
            if (this.number < that.number) {
                return 1;
            } else if (this.number > that.number) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            int n = scanner.nextInt();
            int[] inputArray = new int[n];
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            System.out.println();
            for (int i = 0; i < n; i++) {
                int item = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                inputArray[i] = item;
            }
            Arrays.sort(inputArray);
            System.out.println(getMean(inputArray, n));
            System.out.println(getMedian(inputArray, n));
            System.out.println(getMode(inputArray, n));
        }
    }

    static double getMean(int[] sortedInputArray, int n) {
        return Arrays.stream(sortedInputArray).sum() / Double.valueOf(n);
    }

    static double getMedian(int[] sortedInputArray, int n) {
        int mid = Double.valueOf(Math.ceil(n / 2)).intValue();
        return (sortedInputArray[mid] + sortedInputArray[mid - 1]) / 2.0;
    }

    static int getMode(int[] sortedInputArray, int n) {
        NumberFrequency[] pairs = null;
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int itemI = sortedInputArray[i];
            if (frequencies.containsKey(itemI)) {
                frequencies.put(itemI, frequencies.get(itemI) + 1);
            } else {
                frequencies.put(itemI, 1);
            }
        }
        pairs = new NumberFrequency[frequencies.size()];
        Iterator<Map.Entry<Integer, Integer>> it = frequencies.entrySet().iterator();
        int counter = 0;
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> pair = it.next();
            pairs[counter] = new NumberFrequency(pair.getKey(), pair.getValue());
            counter++;
        }
        Arrays.sort(pairs);
        return pairs[counter - 1].number;
    }

}
