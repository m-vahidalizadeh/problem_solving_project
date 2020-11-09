package leetcode.companies.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class ZigZagBT {

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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        boolean isLToR = true;
        List<TreeNode> currL = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        currL.add(root);
        while (!currL.isEmpty()) {
            List<TreeNode> nextL = new ArrayList<>();
            LinkedList<Integer> res = new LinkedList<>();
            for (TreeNode n : currL) {
                if (isLToR) res.addLast(n.val);
                else res.addFirst(n.val);
                if (n.left != null) nextL.add(n.left);
                if (n.right != null) nextL.add(n.right);
            }
            currL = nextL;
            result.add(res);
            isLToR = !isLToR;
        }
        return result;
    }

}
