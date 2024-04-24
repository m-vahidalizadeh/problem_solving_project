package leetcode.hard;

import javafx.util.Pair;

import java.util.*;

/**
 * 3123. Find Edges in Shortest Paths
 * Hard
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * You are given an undirected weighted graph of n nodes numbered from 0 to n - 1. The graph consists of m edges represented by a 2D array edges, where edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai and bi with weight wi.
 *
 * Consider all the shortest paths from node 0 to node n - 1 in the graph. You need to find a boolean array answer where answer[i] is true if the edge edges[i] is part of at least one shortest path. Otherwise, answer[i] is false.
 *
 * Return the array answer.
 *
 * Note that the graph may not be connected.
 *
 * Example 1:
 *
 * Input: n = 6, edges = [[0,1,4],[0,2,1],[1,3,2],[1,4,3],[1,5,1],[2,3,1],[3,5,3],[4,5,2]]
 *
 * Output: [true,true,true,false,true,true,true,false]
 *
 * Explanation:
 *
 * The following are all the shortest paths between nodes 0 and 5:
 *
 * The path 0 -> 1 -> 5: The sum of weights is 4 + 1 = 5.
 * The path 0 -> 2 -> 3 -> 5: The sum of weights is 1 + 1 + 3 = 5.
 * The path 0 -> 2 -> 3 -> 1 -> 5: The sum of weights is 1 + 1 + 2 + 1 = 5.
 * Example 2:
 *
 * Input: n = 4, edges = [[2,0,1],[0,1,1],[0,3,4],[3,2,2]]
 *
 * Output: [true,false,false,true]
 *
 * Explanation:
 *
 * There is one shortest path between nodes 0 and 3, which is the path 0 -> 2 -> 3 with the sum of weights 1 + 2 = 3.
 *
 * Constraints:
 *
 * 2 <= n <= 5 * 10^4
 * m == edges.length
 * 1 <= m <= min(5 * 104, n * (n - 1) / 2)
 * 0 <= ai, bi < n
 * ai != bi
 * 1 <= wi <= 10^5
 * There are no repeated edges.
 */
public class FindEdgesInShortestPaths {

    public boolean[] findAnswer(int n, int[][] edges) {
        Map<Integer,List<int[]>> graph=new HashMap<>();
        for(int i=0;i<n;i++) graph.put(i,new ArrayList<>());
        for(int[] edge:edges){
            graph.get(edge[0]).add(new int[]{edge[1],edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0],edge[2]});
        }
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0]=0;
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.offer(new int[]{0,0});
        while(!pq.isEmpty()){
            int curr[]=pq.poll(),v=curr[0],w=curr[1];
            if(w>dist[v]) continue;
            for(int[] nei:graph.get(v)){
                if(dist[v]+nei[1]<dist[nei[0]]){
                    dist[nei[0]]=dist[v]+nei[1];
                    pq.offer(new int[]{nei[0],dist[nei[0]]});
                }
            }
        }
        Set<Pair<Integer,Integer>> shortestPathEdges=new HashSet<>();
        Queue<Integer> q=new LinkedList<>();
        q.offer(n-1);
        while(!q.isEmpty()){
            int v=q.poll();
            for(int[] nei:graph.get(v)){
                if(dist[nei[0]]==dist[v]-nei[1]){
                    shortestPathEdges.add(new Pair<>(v,nei[0]));
                    shortestPathEdges.add(new Pair<>(nei[0],v));
                    q.add(nei[0]);
                }
            }
        }
        boolean[] ans=new boolean[edges.length];
        for(int i=0;i<edges.length;i++) ans[i]=shortestPathEdges.contains(new Pair<>(edges[i][0],edges[i][1]));
        return ans;
    }

}
