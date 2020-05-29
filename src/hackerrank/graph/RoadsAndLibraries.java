package hackerrank.graph;

import java.io.*;
import java.util.*;

public class RoadsAndLibraries {

    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        if (cities.length == 0) return n * c_lib;
        List<Set<Integer>> roads = getRoads(cities);
        if (c_lib <= c_road) {
            return c_lib * n;
        }
        int numOfClusters = roads.size();
        Set<Integer> citiesWithRoads = new HashSet<>();
        for (Set<Integer> cluster : roads) {
            citiesWithRoads.addAll(cluster);
        }
        return (numOfClusters + n - citiesWithRoads.size()) * c_lib + (citiesWithRoads.size() - roads.size()) * c_road;
    }

    private static List<Set<Integer>> getRoads(int[][] cities) {
        List<Set<Integer>> roads = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            boolean areAdded = false;
            for (Set<Integer> road : roads) {
                if (road.contains(cities[i][0]) || road.contains(cities[i][1])) {
                    road.add(cities[i][0]);
                    road.add(cities[i][1]);
                    areAdded = true;
                    break;
                }
            }
            if (!areAdded) {
                Set<Integer> newSet = new HashSet<>();
                newSet.add(cities[i][0]);
                newSet.add(cities[i][1]);
                roads.add(newSet);
            }
        }
        boolean tryMerge = true;
        while (tryMerge) {
            tryMerge = false;
            List<Set<Integer>> combinedRoads = new ArrayList<>();
            for (int i = 0; i < roads.size() - 1; i++) {
                if (!Collections.disjoint(roads.get(i), roads.get(i + 1))) {
                    tryMerge = true;
                    Set<Integer> newSet = new HashSet<>();
                    newSet.addAll(roads.get(i));
                    newSet.addAll(roads.get(i + 1));
                    combinedRoads.add(newSet);
                } else {
                    combinedRoads.add(roads.get(i));
                    combinedRoads.add(roads.get(i + 1));
                }
            }
            if (tryMerge) roads = combinedRoads;
        }
        return roads;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/ral.out")));
                Scanner scanner = new Scanner(System.in)
        ) {

            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int qItr = 0; qItr < q; qItr++) {
                String[] nmC_libC_road = scanner.nextLine().split(" ");

                int n = Integer.parseInt(nmC_libC_road[0]);

                int m = Integer.parseInt(nmC_libC_road[1]);

                int c_lib = Integer.parseInt(nmC_libC_road[2]);

                int c_road = Integer.parseInt(nmC_libC_road[3]);

                int[][] cities = new int[m][2];

                for (int i = 0; i < m; i++) {
                    String[] citiesRowItems = scanner.nextLine().split(" ");
                    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                    for (int j = 0; j < 2; j++) {
                        int citiesItem = Integer.parseInt(citiesRowItems[j]);
                        cities[i][j] = citiesItem;
                    }
                }

                long result = roadsAndLibraries(n, c_lib, c_road, cities);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }

}
