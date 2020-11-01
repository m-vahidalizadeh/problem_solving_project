package leetcode.companies.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 637. Average of Levels in Binary Tree
 * Easy
 *
 * 1508
 *
 * 173
 *
 * Add to List
 *
 * Share
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * Example 1:
 *
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 * Note:
 *
 * The range of node's value is in the range of 32-bit signed integer.
 */
public class LevelAVGBT {

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

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Double> result = new ArrayList<>();
        List<TreeNode> currL = new ArrayList<>();
        currL.add(root);
        while (!currL.isEmpty()) {
            List<TreeNode> nextL = new ArrayList<>();
            int count = 0;
            double sum = 0;
            for (TreeNode node : currL) {
                if (node.left != null) nextL.add(node.left);
                if (node.right != null) nextL.add(node.right);
                count++;
                sum += node.val;
            }
            result.add(sum / count);
            currL = nextL;
        }
        return result;
    }

}
