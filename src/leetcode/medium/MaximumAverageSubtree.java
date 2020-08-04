package leetcode.medium;

/**
 * Maximum Average Subtree
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.
 * <p>
 * (A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values, divided by the number of nodes.)
 * <p>
 * Example 1:
 * <p>
 * Input: [5,6,1]
 * Output: 6.00000
 * Explanation:
 * For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
 * For the node with value = 6 we have an average of 6 / 1 = 6.
 * For the node with value = 1 we have an average of 1 / 1 = 1.
 * So the answer is 6 which is the maximum.
 * <p>
 * Note:
 * <p>
 * The number of nodes in the tree is between 1 and 5000.
 * Each node will have a value between 0 and 100000.
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 */
public class MaximumAverageSubtree {

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

    public class AVG {
        double sum;
        double count;

        public AVG() {
            sum = 0;
            count = 0;
        }

        public AVG(double sum, double count) {
            this.sum = sum;
            this.count = count;
        }
    }

    Double maxSum;
    Double maxCount;

    public double maximumAverageSubtree(TreeNode root) {
        getMaxAVG(root);
        return maxSum / maxCount;
    }

    private AVG getMaxAVG(TreeNode node) {
        AVG lAVG = node.left == null ? new AVG(0, 0) : getMaxAVG(node.left);
        AVG rAVG = node.right == null ? new AVG(0, 0) : getMaxAVG(node.right);
        AVG curr = new AVG(lAVG.sum + rAVG.sum + node.val, lAVG.count + rAVG.count + 1);
        if (maxSum == null || maxSum * curr.count < curr.sum * maxCount) {
            maxSum = curr.sum;
            maxCount = curr.count;
        }
        return curr;
    }

    public static void main(String[] args) {
        MaximumAverageSubtree m = new MaximumAverageSubtree();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(6);
        root.right = new TreeNode(1);
        System.out.println(m.maximumAverageSubtree(root));
    }

}
