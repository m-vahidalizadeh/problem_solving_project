package fb;

import java.io.*;
import java.util.*;

/**
 * Nodes in a Subtree
 * You are given a tree that contains N nodes, each containing one lowercase letter of string s.
 * You are required to answer Q queries of type [u, c], where u is an integer and c is a lowercase letter. The query result is the number of nodes in the subtree of node u containing c.
 * Signature
 * int[] countOfNodes(base.Node root, ArrayList<Query> queries, String s)
 * Input
 * A pointer to the root node, an array list containing Q queries of type [u, c], and a string s
 * Constraints
 * N and Q are the integers between 1 and 1,000,000
 * u is an integer between 1 and N
 * s is of the length of N, containing only lowercase letters
 * c is a lowercase letter contained in string s
 * base.Node 1 is the root of the tree
 * Output
 * An integer array containing the response to each query
 * Example
 * 1(a)
 * /   \
 * 2(b)  3(a)
 * s = "aba"
 * RootNode = 1
 * query = [[1, 'a']]
 * output = [2]
 * Both base.Node 1 and base.Node 3 contain 'a', so the number of nodes within the subtree of base.Node 1 containing 'a' is 2.
 */
public class NodesInASubtree {

    // Tree base.Node
    class Node {
        public int val;
        public List<Node> children;

        public Node() { // constructor
            val = 0;
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class Query {
        int v;
        char c;

        Query(int v, char c) {
            this.v = v;
            this.c = c;
        }
    }

    private Map<Character, Integer> merge(Map<Character, Integer> currNodeFreq, Map<Character, Integer> frequencies) {
        if (currNodeFreq == null)
            return frequencies;
        for (Map.Entry<Character, Integer> f : frequencies.entrySet()) {
            if (currNodeFreq.containsKey(f.getKey())) {
                currNodeFreq.put(f.getKey(), f.getValue() + currNodeFreq.get(f.getKey()));
            } else {
                currNodeFreq.put(f.getKey(), f.getValue());
            }
        }
        return currNodeFreq;
    }

    int[] countOfNodes(Node root, ArrayList<Query> queries, String s) {
        Map<Node, Node> parents = new HashMap<>();
        Set<Node> leaves = new HashSet<>();
        Map<Node, Character> nodeChars = new HashMap<>();
        Map<Integer, Map<Character, Integer>> nodeToFreqmap = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        parents.put(root, null);
        while (!q.isEmpty()) {
            Node currentNode = q.poll();
            List<Node> children = currentNode.children;
            nodeChars.put(currentNode, s.charAt(currentNode.val - 1));
            if (children.isEmpty()) {
                leaves.add(currentNode);
            } else {
                for (Node c : children) {
                    parents.put(c, currentNode);
                    q.add(c);
                }
            }
        }
        Set<Node> counted = new HashSet<>();
        for (Node lNode : leaves) {
            Queue<Node> q2 = new LinkedList<>();
            q2.add(lNode);
            Map<Character, Integer> frequencies = new HashMap<>();
            while (!q2.isEmpty()) {
                Node currentNode = q2.poll();
                if (currentNode != null) {
                    char currNodeChar = nodeChars.get(currentNode);
                    if (!counted.contains(currentNode)) {
                        if (frequencies.containsKey(currNodeChar)) {
                            frequencies.put(currNodeChar, frequencies.get(currNodeChar) + 1);
                        } else {
                            frequencies.put(currNodeChar, 1);
                        }
                        counted.add(currentNode);
                    }
                    if (nodeToFreqmap.containsKey(currentNode.val)) {
                        nodeToFreqmap.put(currentNode.val, merge(new HashMap<>(nodeToFreqmap.get(currentNode.val)), frequencies));
                    } else {
                        nodeToFreqmap.put(currentNode.val, new HashMap<>(frequencies));
                    }
                    if (parents.containsKey(currentNode)) {
                        q2.add(parents.get(currentNode));
                    }
                }
            }

        }
        int n = queries.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            Query currQuery = queries.get(i);
            result[i] = nodeToFreqmap.get(currQuery.v).get(currQuery.c);
        }
        return result;
    }


    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(int[] expected, int[] output) {
        int expected_size = expected.length;
        int output_size = output.length;
        boolean result = true;
        if (expected_size != output_size) {
            result = false;
        }
        for (int i = 0; i < Math.min(expected_size, output_size); i++) {
            result &= (output[i] == expected[i]);
        }
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printIntegerArray(expected);
            System.out.print(" Your output: ");
            printIntegerArray(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printIntegerArray(int[] arr) {
        int len = arr.length;
        System.out.print("[");
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.print("]");
    }

    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);

        //Testcase 1
        int n_1 = 3, q_1 = 1;
        String s_1 = "aba";
        Node root_1 = new Node(1);
        root_1.children.add(new Node(2));
        root_1.children.add(new Node(3));
        ArrayList<Query> queries_1 = new ArrayList<>();
        queries_1.add(new Query(1, 'a'));
        int[] output_1 = countOfNodes(root_1, queries_1, s_1);
        int[] expected_1 = {2};
        check(expected_1, output_1);

        // Testcase 2
        int n_2 = 7, q_2 = 3;
        String s_2 = "abaacab";
        Node root_2 = new Node(1);
        root_2.children.add(new Node(2));
        root_2.children.add(new Node(3));
        root_2.children.add(new Node(7));
        root_2.children.get(0).children.add(new Node(4));
        root_2.children.get(0).children.add(new Node(5));
        root_2.children.get(1).children.add(new Node(6));
        ArrayList<Query> queries_2 = new ArrayList<>();
        queries_2.add(new Query(1, 'a'));
        queries_2.add(new Query(2, 'b'));
        queries_2.add(new Query(3, 'a'));
        int[] output_2 = countOfNodes(root_2, queries_2, s_2);
        int[] expected_2 = {4, 1, 2};
        check(expected_2, output_2);
    }

    public static void main(String[] args) throws IOException {
        new NodesInASubtree().run();
    }

}
