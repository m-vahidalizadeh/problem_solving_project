package leetcode.companies.random;

import java.util.*;

/**
 * Clone Graph
 * Given a reference of a node in a connected undirected graph.
 * <p>
 * Return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * Test case format:
 * <p>
 * For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.
 * <p>
 * Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 * <p>
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 * <p>
 * Example 1:
 * <p>
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * Example 2:
 * <p>
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * Example 3:
 * <p>
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 * Example 4:
 * <p>
 * Input: adjList = [[2],[1]]
 * Output: [[2],[1]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * Number of Nodes will not exceed 100.
 * There is no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 */
public class DeepCopyOfGraph {

    Map<Node, Node> clonedNodes = new HashMap<>();
    Set<Node> clonedSet = new HashSet<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Node clone = cloneNode(node);
        Queue<Node> q = new LinkedList<>();
        for (Node n : node.neighbors) {
            if (!clonedSet.contains(n)) q.add(n);
            Node nClone = cloneNode(n);
            clone.neighbors.add(nClone);
        }
        clonedSet.add(node);
        while (!q.isEmpty()) {
            Node qTop = q.poll();
            if (!clonedSet.contains(qTop)) cloneGraph(qTop);
        }
        return clone;
    }

    private Node cloneNode(Node n) {
        if (n == null) return null;
        if (clonedNodes.containsKey(n)) return clonedNodes.get(n);
        Node clone = new Node(n.val);
        clonedNodes.put(n, clone);
        return clone;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

}
