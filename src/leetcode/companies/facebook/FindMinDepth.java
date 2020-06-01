package leetcode.companies.facebook;

import leetcode.base.TreeNode;

/**
 * Minimum Depth of Binary Tree
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its minimum depth = 2.
 */
public class FindMinDepth {

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return minDepthRec(root, 1);
    }

    private int minDepthRec(TreeNode node, int num) {
        TreeNode l = node.left;
        TreeNode r = node.right;
        if (r == null && l == null) return num;
        else if (r == null) return minDepthRec(l, num + 1);
        else if (l == null) return minDepthRec(r, num + 1);
        return Math.min(minDepthRec(l, num + 1), minDepthRec(r, num + 1));
    }

    public static void main(String[] args) {
        FindMinDepth f = new FindMinDepth();

//        TreeNode node3 = new TreeNode(3);
//        TreeNode node9 = new TreeNode(9);
//        TreeNode node20 = new TreeNode(20);
//        TreeNode node15 = new TreeNode(15);
//        TreeNode node7 = new TreeNode(7);
//        node3.left = node9;
//        node3.right = node20;
//        node20.left = node15;
//        node20.right = node7;
//        System.out.println(f.minDepth(node3));

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.left = node2;
        System.out.println(f.minDepth(node1));
    }

}
