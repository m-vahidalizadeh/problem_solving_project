package leetcode.companies.uber;

import leetcode.base.TreeNode;

/**
 * Validate Binary Search Tree
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
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
public class ValidBST {

    public boolean isValidBST(TreeNode root) {
        return isValidRec(root, null, null);
    }

    private boolean isValidRec(TreeNode node, Integer max, Integer min) {
        if (node == null) return true;
        TreeNode l = node.left;
        TreeNode r = node.right;
        if (min != null && node.val <= min) return false;
        if (max != null && node.val >= max) return false;
        if (l != null && l.val >= node.val) return false;
        if (r != null && r.val <= node.val) return false;
        int newMin = min == null ? node.val : Math.min(node.val, min);
        int newMax = max == null ? node.val : Math.max(node.val, max);
        return isValidRec(l, newMax, min) && isValidRec(r, max, newMin);
    }

}
