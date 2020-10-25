package leetcode.companies.amazon;

/**
 * 543. Diameter of Binary Tree
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBT {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int max;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        max = 0;
        rec(root);
        return max - 1;
    }

    private int rec(TreeNode node) {
        if (node == null) return 0;
        int lRec = rec(node.left);
        int rRec = rec(node.right);
        max = Math.max(max, 1 + lRec + rRec);
        return 1 + Math.max(lRec, rRec);
    }

    public static void main(String[] args) {
        DiameterOfBT d = new DiameterOfBT();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        System.out.println(d.diameterOfBinaryTree(node1));
    }

}
