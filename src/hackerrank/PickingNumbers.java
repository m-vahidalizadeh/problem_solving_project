package hackerrank;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    public static int pickingNumbers(List<Integer> a) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        Integer maxSize = Integer.MIN_VALUE;
        Iterator aIterator = a.iterator();
        while (aIterator.hasNext()) {
            Integer e = (Integer) aIterator.next();
            if (frequencies.containsKey(e)) {
                frequencies.put(e, frequencies.get(e) + 1);
                if (frequencies.get(e) > maxSize) {
                    maxSize = frequencies.get(e);
                }
            } else {
                frequencies.put(e, 1);
                if (1 > maxSize) {
                    maxSize = 1;
                }
            }
        }
        Iterator it = frequencies.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> e = (Map.Entry) it.next();
            int eKeyMinusOne = e.getKey() - 1;
            if (frequencies.containsKey(eKeyMinusOne)) {
                int tempSize = frequencies.get(e.getKey()) + frequencies.get(eKeyMinusOne);
                if (tempSize > maxSize) {
                    maxSize = tempSize;
                }
            }
            int eKeyPlusOne = e.getKey() + 1;
            if (frequencies.containsKey(eKeyPlusOne)) {
                int tempSize = frequencies.get(e.getKey()) + frequencies.get(eKeyPlusOne);
                if (tempSize > maxSize) {
                    maxSize = tempSize;
                }
            }
        }
        return maxSize;
    }

}

public class PickingNumbers {
    public static void main(String[] args) throws IOException {
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/pn.out")));
        ) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int result = Result.pickingNumbers(a);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
