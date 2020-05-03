package hackerrank;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FrequencyQueries {

    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> result = new LinkedList<>();
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (List<Integer> query : queries) {
            Integer queryFirstSection = query.get(0);
            if (queryFirstSection == 1) {
                Integer elementToInsert = query.get(1);
                if (frequencies.containsKey(elementToInsert)) {
                    frequencies.put(elementToInsert, frequencies.get(elementToInsert) + 1);
                } else {
                    frequencies.put(elementToInsert, 1);
                }
            } else if (queryFirstSection == 2) {
                Integer elementToDelete = query.get(1);
                if (frequencies.containsKey(elementToDelete)) {
                    Integer existingFrequency = frequencies.get(elementToDelete);
                    if (existingFrequency > 0) {
                        frequencies.put(elementToDelete, existingFrequency - 1);
                    }
                    if (existingFrequency - 1 == 0) {
                        frequencies.remove(elementToDelete);
                    }
                }
            } else if (queryFirstSection == 3) {
                Integer expectedFrequency = query.get(1);
                Iterator it = frequencies.entrySet().iterator();
                boolean found = false;
                while (it.hasNext() && !found) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (pair.getValue() == expectedFrequency) {
                        result.add(1);
                        found = true;
                    }
                }
                if (!found) {
                    result.add(0);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/fq.out")));){
            int q = Integer.parseInt(bufferedReader.readLine().trim());

            List<List<Integer>> queries = new ArrayList<>();

            IntStream.range(0, q).forEach(i -> {
                try {
                    queries.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            List<Integer> ans = freqQuery(queries);

            bufferedWriter.write(
                    ans.stream()
                            .map(Object::toString)
                            .collect(joining("\n"))
                            + "\n"
            );
        }
    }

}
