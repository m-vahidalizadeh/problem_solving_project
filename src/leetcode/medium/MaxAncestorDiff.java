package leetcode.medium;

import leetcode.base.TreeNode;

/**
 * Maximum Difference Between Node and Ancestor
 * Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 * <p>
 * (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
 * <p>
 * Example 1:
 * <p>
 * Input: [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation:
 * We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 * <p>
 * Note:
 * <p>
 * The number of nodes in the tree is between 2 and 5000.
 * Each node will have value between 0 and 100000.
 */
public class MaxAncestorDiff {

    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;
        int rootVal = root.val;
        return maxAncChildRec(root, rootVal, rootVal, 0);
    }

    private int maxAncChildRec(TreeNode node, int a, int b, int existigDiff) {
        if (node == null) return 0;
        TreeNode l = node.left;
        TreeNode r = node.right;
        int nodeVal = node.val;
        int diff1 = Math.abs(nodeVal - a);
        int diff2 = Math.abs(nodeVal - b);
        int totalDiff = Math.max(Math.max(diff1, diff2), existigDiff);
        if (totalDiff == diff1) {
            b = nodeVal;
            existigDiff = totalDiff;
        } else if (totalDiff == diff2) {
            a = nodeVal;
            existigDiff = totalDiff;
        }
        if (l == null && r == null) {
            return existigDiff;
        }
        return Math.max(maxAncChildRec(node.left, a, b, existigDiff), maxAncChildRec(node.right, a, b, existigDiff));
    }

    public static void main(String[] args) {
        TreeNode node8 = new TreeNode(8);
        TreeNode node3 = new TreeNode(3);
        TreeNode node10 = new TreeNode(10);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node14 = new TreeNode(14);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node13 = new TreeNode(13);
        node8.left = node3;
        node8.right = node10;
        node3.left = node1;
        node3.right = node6;
        node6.left = node4;
        node6.right = node7;
        node10.right = node14;
        node14.left = node13;
        MaxAncestorDiff m = new MaxAncestorDiff();
        System.out.println(m.maxAncestorDiff(node8));
    }
}
