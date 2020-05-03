package hackerrank;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MigratoryBirds {

    static int migratoryBirds(List<Integer> arr) {
        Map<Integer, Integer> sightings = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (Integer element : arr) {
            int newValue = 1;
            if (sightings.containsKey(element)) {
                newValue += sightings.get(element);
                sightings.put(element, newValue);
            } else {
                sightings.put(element, newValue);
            }
            max = newValue > max ? newValue : max;
        }
        final int maximum = max;
        List<Integer> maximums = sightings.keySet().stream().filter(e -> sightings.get(e) == maximum).collect(toList());
        return maximums.stream().min(Integer::compareTo).get().intValue();
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/mb.out")));
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(System.in));
        ) {

            int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int result = migratoryBirds(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
