package hackerrank;

import java.io.*;
import java.util.*;

public class BFSFirstSearchShortestReach {

    static int[] bfs(int n, int m, int[][] edges, int s) {
        Map<Integer, Set<Integer>> connectedTo = getConnectedTo(m, edges);
        Map<Integer, Integer> shortestPaths = new HashMap<>();
        Queue<Integer> vertices = new LinkedList<>();
        vertices.add(s);

        for (int i = 1; i <= n; i++) {
            if (i == s) {
                shortestPaths.put(i, 0);
            } else {
                shortestPaths.put(i, -1);
            }
        }

        while (!vertices.isEmpty()) {
            int currentVertex = vertices.poll();
            if (connectedTo.containsKey(currentVertex)) {
                int currentVertexCost = shortestPaths.get(currentVertex);
                int newCost = currentVertexCost + 6;
                Set<Integer> connectedToNodes = connectedTo.get(currentVertex);
                vertices.addAll(connectedToNodes);
                for (Integer node : connectedToNodes) {
                    if (shortestPaths.get(node) == -1 || shortestPaths.get(node) > newCost) {
                        shortestPaths.put(node, newCost);
                    }
                }
            }
        }

        int[] result = new int[n - 1];
        int index = 0;
        for (int i = 1; i <= n; i++) {
            if (i != s) {
                result[index] = shortestPaths.get(i);
                index++;
            }
        }

        return result;
    }

    private static Map<Integer, Set<Integer>> getConnectedTo(int m, int[][] edges) {
        Map<Integer, Set<Integer>> connectedTo = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int currentFrom = edges[i][0];
            int currentTo = edges[i][1];
            if (connectedTo.containsKey(currentFrom)) {
                Set<Integer> connectedToSet = connectedTo.get(currentFrom);
                connectedToSet.add(currentTo);
                connectedTo.put(currentFrom, connectedToSet);
            } else {
                Set<Integer> connectedToSet = new HashSet<>();
                connectedToSet.add(currentTo);
                connectedTo.put(currentFrom, connectedToSet);
            }
        }
        return connectedTo;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/bfssr.out")));
             Scanner scanner = new Scanner(System.in)) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int qItr = 0; qItr < q; qItr++) {
                String[] nm = scanner.nextLine().split(" ");

                int n = Integer.parseInt(nm[0]);

                int m = Integer.parseInt(nm[1]);

                int[][] edges = new int[m][2];

                for (int i = 0; i < m; i++) {
                    String[] edgesRowItems = scanner.nextLine().split(" ");
                    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                    for (int j = 0; j < 2; j++) {
                        int edgesItem = Integer.parseInt(edgesRowItems[j]);
                        edges[i][j] = edgesItem;
                    }
                }

                int s = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                int[] result = bfs(n, m, edges, s);

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