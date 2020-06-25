package leetcode.medium;

import java.util.*;

/**
 * N-ary Tree Level Order Traversal
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 * <p>
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [[1],[3,2,4],[5,6]]
 * Example 2:
 * <p>
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 * <p>
 * Constraints:
 * <p>
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 10^4]
 */
public class TraverseNAryTree {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        List<Node> prevLevel = new ArrayList<>();
        List<Node> currLevel = new ArrayList<>();
        List<Integer> currVals = new ArrayList<>();
        prevLevel.add(root);
        while (!prevLevel.isEmpty()) {
            for (Node prev : prevLevel) {
                currVals.add(prev.val);
                currLevel.addAll(prev.children);
            }
            result.add(currVals);
            prevLevel = currLevel;
            currVals = new ArrayList<>();
            currLevel = new ArrayList<>();
        }
        return result;
    }

}
