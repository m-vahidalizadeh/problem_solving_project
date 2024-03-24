package leetcode.companies.facebook;

import java.util.HashSet;
import java.util.Set;

/**
 * 1650. Lowest Common Ancestor of a Binary Tree III
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 *
 * Each node will have a reference to its parent node. The definition for Node is below:
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 *
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 10^5].
 * -109 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q exist in the tree.
 */
public class BTLowestCommonAncestor {

    Set<Node> paSet = new HashSet<>();

    public Node lowestCommonAncestor(Node p, Node q) {
        visitP(p);
        while (!paSet.contains(q)) q = q.parent;
        return q;
    }

    private void visitP(Node node) {
        if (node == null) return;
        paSet.add(node);
        visitP(node.parent);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

}
