package leetcode.companies.google;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Maximum Level Sum of a Binary Tree
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * <p>
 * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 * <p>
 * Example 1:
 * <p>
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
public class MaxLevelSum {

    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> currLevel = new LinkedList<>();
        Queue<TreeNode> nextLevel = new LinkedList<>();
        currLevel.add(root);
        long max = Long.MIN_VALUE;
        int maxLevel = 0;
        int level = 0;
        long sum = 0;
        while (!currLevel.isEmpty()) {
            level++;
            while (!currLevel.isEmpty()) {
                TreeNode currNode = currLevel.poll();
                sum += currNode.val;
                TreeNode l = currNode.left;
                if (l != null) nextLevel.add(l);
                TreeNode r = currNode.right;
                if (r != null) nextLevel.add(r);
            }
            if (sum > max) {
                max = sum;
                maxLevel = level;
            }
            sum = 0;
            currLevel = nextLevel;
            nextLevel = new LinkedList<>();
        }
        return maxLevel;
    }

    int maxLevel;
    Map<Integer, Long> levelSums;

    public int maxLevelSum2(TreeNode root) {
        maxLevel = 0;
        levelSums = new HashMap<>();
        rec(root, 1);
        long sum = 0L;
        long resSum = 0L;
        int resLevel = 0;
        for (int i = 1; i <= maxLevel; i++) {
            sum = levelSums.get(i);
            if (sum > resSum) {
                resSum = sum;
                resLevel = i;
            }
        }
        return resLevel;
    }

    private void rec(TreeNode node, int level) {
        if (node == null) return;
        else {
            maxLevel = Math.max(level, maxLevel);
            levelSums.put(level, levelSums.getOrDefault(level, 0L) + node.val);
            int newLevel = level + 1;
            rec(node.left, newLevel);
            rec(node.right, newLevel);
        }
    }

    public static void main(String[] args) {
        MaxLevelSum m = new MaxLevelSum();
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node7 = new TreeNode(7);
//        TreeNode node0 = new TreeNode(0);
//        TreeNode node7_2 = new TreeNode(7);
//        TreeNode nodeN8 = new TreeNode(-8);
//        node1.left = node7;
//        node1.right = node0;
//        node7.left = node7_2;
//        node7.right = nodeN8;
        TreeNode nodeA = new TreeNode(989);
        TreeNode nodeB = new TreeNode(10250);
        TreeNode nodeC = new TreeNode(98693);
        TreeNode nodeD = new TreeNode(-89388);
        TreeNode nodeE = new TreeNode(-32127);
        nodeA.right = nodeB;
        nodeB.left = nodeC;
        nodeB.right = nodeD;
        nodeD.right = nodeE;
        System.out.println(m.maxLevelSum2(nodeA));
    }

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

}
