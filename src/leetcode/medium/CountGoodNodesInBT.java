package leetcode.medium;

import leetcode.base.TreeNode;

/**
 * Count Good Nodes in Binary Tree
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 * <p>
 * Return the number of good nodes in the binary tree.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 * Example 2:
 * <p>
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 * Example 3:
 * <p>
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the binary tree is in the range [1, 10^5].
 * Each node's value is between [-10^4, 10^4].
 */
public class CountGoodNodesInBT {

    public class GoodNodesCount {
        public int numOfGoodNodes;
    }

    public int goodNodes(TreeNode root) {
        if (root == null) return 0;
        GoodNodesCount goodNodesCount = new GoodNodesCount();
        countGoodNodes(root, goodNodesCount, Integer.MIN_VALUE);
        return goodNodesCount.numOfGoodNodes;
    }

    private void countGoodNodes(TreeNode node, GoodNodesCount goodNodesCount, int max) {
        if (node == null) return;
        int currentNodeVal = node.val;
        if (currentNodeVal > max) {
            max = currentNodeVal;
            goodNodesCount.numOfGoodNodes++;
        } else if (currentNodeVal == max) {
            goodNodesCount.numOfGoodNodes++;
        }
        countGoodNodes(node.left, goodNodesCount, max);
        countGoodNodes(node.right, goodNodesCount, max);
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3_2 = new TreeNode(3);
        TreeNode node1_2 = new TreeNode(1);
        TreeNode node5 = new TreeNode(5);
        node3.left = node1;
        node3.right = node4;
        node1.left = node3_2;
        node4.left = node1_2;
        node4.right = node5;
        CountGoodNodesInBT c = new CountGoodNodesInBT();
        System.out.println(c.goodNodes(node3));
    }

}
