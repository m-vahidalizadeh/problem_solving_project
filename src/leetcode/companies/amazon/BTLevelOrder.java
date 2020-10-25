package leetcode.companies.amazon;

import java.util.*;

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
public class BTLevelOrder {

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
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> currL = new LinkedList<>();
        currL.add(root);
        while (!currL.isEmpty()) {
            Queue<TreeNode> nextL = new LinkedList<>();
            List<Integer> resL = new ArrayList<>();
            while (!currL.isEmpty()) {
                TreeNode currN = currL.poll();
                resL.add(currN.val);
                if (currN.left != null) nextL.add(currN.left);
                if (currN.right != null) nextL.add(currN.right);
            }
            currL = nextL;
            result.add(resL);
        }
        return result;
    }

}
