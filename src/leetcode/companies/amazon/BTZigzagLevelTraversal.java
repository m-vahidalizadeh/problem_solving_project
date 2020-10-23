package leetcode.companies.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its zigzag level order traversal as:
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class BTZigzagLevelTraversal {

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
        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);
        List<TreeNode> next;
        List<List<Integer>> result = new ArrayList<>();
        while (!curr.isEmpty()) {
            next = new ArrayList<>();
            LinkedList<Integer> l = new LinkedList<>();
            for (TreeNode node : curr) {
                if (isLToR) l.add(node.val);
                else l.addFirst(node.val);
                if (node.left != null) next.add(node.left);
                if (node.right != null) next.add(node.right);
            }
            result.add(l);
            // Next
            curr = next;
            isLToR = !isLToR;
        }
        return result;
    }

}
