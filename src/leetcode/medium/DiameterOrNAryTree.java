package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Diameter of N-Ary Tree
 * Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.
 * <p>
 * The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. This path may or may not pass through the root.
 * <p>
 * (Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: 3
 * Explanation: Diameter is shown in red color.
 * Example 2:
 * <p>
 * Input: root = [1,null,2,null,3,4,null,5,null,6]
 * Output: 4
 * Example 3:
 * <p>
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: 7
 * <p>
 * Constraints:
 * <p>
 * The depth of the n-ary tree is less than or equal to 1000.
 * The total number of nodes is between [0, 10^4].
 */
public class DiameterOrNAryTree {

    public int diameter(Node root) {
        Result result = new Result();
        getDepth(root, result);
        return result.max;
    }

    private int getDepth(Node node, Result result) {
        if (node == null) return 0;
        int h1 = 0, h2 = 0;
        for (Node c : node.children) {
            int d = getDepth(c, result);
            if (d > h1) {
                h2 = h1;
                h1 = d;
            } else if (d > h2) h2 = d;
        }
        result.max = Math.max(result.max, h1 + h2);
        return h1 + 1;
    }

    class Result {
        int max;

        Result() {
            max = 0;
        }
    }

    class Node {
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

}
