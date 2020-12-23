package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1697. Checking Existence of Edge Length Limited Paths
 * An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.
 *
 * Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .
 *
 * Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.
 *
 * Example 1:
 *
 * Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
 * Output: [false,true]
 * Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
 * For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
 * For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.
 * Example 2:
 *
 * Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
 * Output: [true,false]
 * Exaplanation: The above figure shows the given graph.
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * 1 <= edgeList.length, queries.length <= 105
 * edgeList[i].length == 3
 * queries[j].length == 3
 * 0 <= ui, vi, pj, qj <= n - 1
 * ui != vi
 * pj != qj
 * 1 <= disi, limitj <= 109
 * There may be multiple edges between two nodes.
 */
public class FindDistanceLimitPaths {

    public class Query {
        int index;
        int u;
        int v;
        int limit;

        public Query(int index, int u, int v, int limit) {
            this.index = index;
            this.u = u;
            this.v = v;
            this.limit = limit;
        }
    }

    public class UF {
        int[] parents;

        public UF(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) parents[i] = i;
        }

        public int find(int x) {
            while (parents[x] != x) x = parents[x];
            return x;
        }

        public void union(int x, int y) {
            int r1 = find(x);
            int r2 = find(y);
            if (r1 != r2) parents[r2] = r1;
        }
    }

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        UF uf = new UF(n);
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        List<Query> queryList = new ArrayList<>();
        int queriesLength = queries.length;
        for (int i = 0; i < queriesLength; i++)
            queryList.add(new Query(i, queries[i][0], queries[i][1], queries[i][2]));
        queryList.sort((a, b) -> a.limit - b.limit);
        int edgeIndex = 0;
        int edgeSize = edgeList.length;
        boolean[] result = new boolean[queriesLength];
        for (Query query : queryList) {
            while (edgeIndex < edgeSize && edgeList[edgeIndex][2] < query.limit) {
                uf.union(edgeList[edgeIndex][0], edgeList[edgeIndex][1]);
                edgeIndex++;
            }
            result[query.index] = uf.find(query.u) == uf.find(query.v);
        }
        return result;
    }

}
