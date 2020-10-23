package leetcode.companies.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 102. Binary Tree Level Order Traversal
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class BTLevelOrderTraversal {

    public class TreeNode {
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);
        List<TreeNode> next;
        List<List<Integer>> result = new ArrayList<>();
        while (!curr.isEmpty()) {
            next = new ArrayList<>();
            List<Integer> l = new ArrayList<>();
            for (TreeNode node : curr) {
                l.add(node.val);
                if (node.left != null) next.add(node.left);
                if (node.right != null) next.add(node.right);
            }
            result.add(l);
            // Next
            curr = next;
        }
        return result;
    }

}
