package leetcode.companies.amazon;

import java.util.*;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 * <p>
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 */
public class LCAOfBT {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    TreeNode[] path;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findPath(root, p, new LinkedList<>());
        TreeNode[] pathP = path;
        findPath(root, q, new LinkedList<>());
        TreeNode[] pathQ = path;
        Set<TreeNode> pCheck = new HashSet<>(Arrays.asList(pathP));
        for (int i = pathQ.length - 1; i >= 0; i--) {
            if (pCheck.contains(pathQ[i])) return pathQ[i];
        }
        return root;
    }

    private void findPath(TreeNode node, TreeNode x, LinkedList<TreeNode> currPath) {
        if (node == null) return;
        currPath.addLast(node);
        if (node.equals(x)) {
            path = new TreeNode[currPath.size()];
            int i = 0;
            for (TreeNode n : currPath) path[i++] = n;
        } else {
            findPath(node.left, x, currPath);
            findPath(node.right, x, currPath);
        }
        currPath.removeLast();
    }

    public static void main(String[] args) {
        LCAOfBT l = new LCAOfBT();
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node0.left = node1;
        node0.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        System.out.println(l.lowestCommonAncestor(node0, node5, node3).val);
    }

}
