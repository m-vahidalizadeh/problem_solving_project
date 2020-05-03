package hackerrank;

import java.io.*;
import java.util.*;

public class Dijkstra {

    static class DijkstraNode implements Comparable<DijkstraNode> {
        public int id;
        public Integer shortestPath;
        public boolean visited = false;

        DijkstraNode(int id, int shortestPath) {
            this.id = id;
            this.shortestPath = shortestPath;
        }

        DijkstraNode(int id) {
            this.id = id;
            this.shortestPath = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(DijkstraNode dijkstraNode) {
            return this.shortestPath.compareTo(dijkstraNode.shortestPath);
        }
    }

    static int[] bfs(int n, int m, int[][] edges, int s) {
        Map<Integer, Set<Integer>> edgesMap = buildEdgesMap(m, edges);
        // Initialization
        Map<Integer, DijkstraNode> dijkstraNodeMap = Initialization(n, s);
        // Dijkstra
        PriorityQueue<DijkstraNode> nodesToVisit = new PriorityQueue<>();
        nodesToVisit.add(dijkstraNodeMap.get(s));
        while (!nodesToVisit.isEmpty()) {
            DijkstraNode currentNode = nodesToVisit.poll();
            if (!currentNode.visited) {
                currentNode.visited = true;
                int currentCost = currentNode.shortestPath;
                int newCost = currentCost + 6;
                Set<Integer> connectedTo = edgesMap.get(currentNode.id);
                if (connectedTo != null) {
                    for (Integer node : connectedTo) {
                        DijkstraNode dijkstraNode = dijkstraNodeMap.get(node);
                        if (!dijkstraNode.visited) {
                            int nodeCost = dijkstraNode.shortestPath;
                            if (nodeCost > newCost) {
                                dijkstraNode.shortestPath = newCost;
                                dijkstraNodeMap.put(node, dijkstraNode);
                            }
                            nodesToVisit.remove(node);
                            nodesToVisit.add(dijkstraNode);
                        }
                    }
                }
            }
        }
        int[] result = new int[n - 1];
        int index = 0;
        // conclusion
        for (int i = 1; i <= n; i++) {
            if (i != s) {
                result[index] = dijkstraNodeMap.get(i).shortestPath == Integer.MAX_VALUE ? -1 : dijkstraNodeMap.get(i).shortestPath;
                index++;
            }
        }
        return result;
    }

    private static Map<Integer, DijkstraNode> Initialization(int n, int s) {
        Map<Integer, DijkstraNode> shortestPaths = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            if (i != s) {
                shortestPaths.put(i, new DijkstraNode(i));
            } else {
                shortestPaths.put(i, new DijkstraNode(i, 0));
            }
        }
        return shortestPaths;
    }

    private static Map<Integer, Set<Integer>> buildEdgesMap(int m, int[][] edges) {
        Map<Integer, Set<Integer>> edgesMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            if (edgesMap.containsKey(edges[i][0])) {
                Set<Integer> currentSet = edgesMap.get(edges[i][0]);
                currentSet.add(edges[i][1]);
                edgesMap.put(edges[i][0], currentSet);
            } else {
                Set<Integer> newSet = new HashSet<>();
                newSet.add(edges[i][1]);
                edgesMap.put(edges[i][0], newSet);
            }
        }
        return edgesMap;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/dijkstra.out")));
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