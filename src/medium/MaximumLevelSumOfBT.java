package medium;

import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Maximum Level Sum of a Binary Tree
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * <p>
 * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 * Example 1:
 * Input: [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 * <p>
 * Note:
 * <p>
 * The number of nodes in the given tree is between 1 and 10^4.
 * -10^5 <= node.val <= 10^5
 */
public class MaximumLevelSumOfBT {

    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node7 = new TreeNode(7);
        TreeNode node7_2 = new TreeNode(7);
        TreeNode nodeMinusEight = new TreeNode(-8);
        node1.left = node7;
        node1.right = node0;
        node7.left = node7_2;
        node7.right = nodeMinusEight;
        MaximumLevelSumOfBT maximumLevelSumOfBT = new MaximumLevelSumOfBT();
        System.out.println(maximumLevelSumOfBT.maxLevelSum(node1));
    }

    public int maxLevelSum(TreeNode root) {
        Map<Integer, Integer> levelSummations = new HashMap<>();
        traverseLevels(root, 1, levelSummations);
        int max = Integer.MIN_VALUE;
        int maxLevel = 1;
        for (Map.Entry<Integer, Integer> lSum : levelSummations.entrySet()) {
            int lSumKey = lSum.getKey();
            int lSumValue = lSum.getValue();
            if (lSumValue > max) {
                max = lSumValue;
                maxLevel = lSumKey;
            }
        }
        return maxLevel;
    }

    private void traverseLevels(TreeNode node, int level, Map<Integer, Integer> levelSummations) {
        if (node == null)
            return;
        addNodeToMap(node, level, levelSummations);
        int childLevel = level + 1;
        traverseLevels(node.left, childLevel, levelSummations);
        traverseLevels(node.right, childLevel, levelSummations);
    }

    private void addNodeToMap(TreeNode node, int level, Map<Integer, Integer> levelSummations) {
        if (levelSummations.containsKey(level)) {
            levelSummations.put(level, levelSummations.get(level) + node.val);
        } else {
            levelSummations.put(level, node.val);
        }
    }

}
