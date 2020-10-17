package leetcode.companies.amazon;

/**
 * 98. Validate Binary Search Tree
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 *
 *     2
 *    / \
 *   1   3
 *
 * Input: [2,1,3]
 * Output: true
 * Example 2:
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 *
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class IsValidBST {
    boolean isBST;

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

    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);
    }

    private boolean dfs(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        int newMax = root.val;
        if (max != null) newMax = Math.min(max, newMax);
        int newMin = root.val;
        if (min != null) newMin = Math.max(min, newMin);
        return dfs(root.left, min, newMax) &&
                dfs(root.right, newMin, max);
    }

    public static void main(String[] args) {
        IsValidBST i = new IsValidBST();
        // Case 1
//        TreeNode node1=new TreeNode(1);
//        TreeNode node3=new TreeNode(3);
//        TreeNode node4=new TreeNode(4);
//        TreeNode node5=new TreeNode(5);
//        TreeNode node6=new TreeNode(6);
//        node5.left=node1;
//        node5.right=node4;
//        node4.left=node3;
//        node4.right=node6;
        // Case 2
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        node2.left=node1;
//        node2.right=node3;
        // Case 3
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node5.left = node1;
        node5.right = node4;
        node4.left = node3;
        node4.right = node6;
        System.out.println(i.isValidBST(node5));
    }

}
