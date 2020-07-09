package leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Find Root of N-Ary Tree
 * Given all the nodes of an N-ary tree as an array  Node[] tree where each node has a unique value.
 * <p>
 * Find and return the root of the N-ary tree.
 * <p>
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 * <p>
 * Follow up: Can you find the root of the tree with O(1) additional memory space?
 * <p>
 * Notes:
 * <p>
 * The following input is only given to testing purposes.
 * You will receive as input a list of all nodes of the n-ary tree in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,null,3,2,4,null,5,6]
 * Output: [1,null,3,2,4,null,5,6]
 * Example 2:
 * <p>
 * Input: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * <p>
 * Constraints:
 * <p>
 * The total number of nodes is between [1, 5*10^4].
 * Each node has a unique value.
 */
public class FindNAryTreeRoot {

    public class Node {
        public int val;
        public List<Node> children;


        public Node() {
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

    public Node findRoot(List<Node> tree) {
        Set<Node> children = new HashSet<>();
        for (Node n : tree) {
            children.addAll(n.children);
        }
        for (Node n : tree) {
            if (!children.contains(n)) return n;
        }
        return null;
    }

}
