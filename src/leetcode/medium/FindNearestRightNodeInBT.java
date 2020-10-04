package leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1602. Find Nearest Right Node in Binary Tree
 * Given the root of a binary tree and a node u in the tree, return the nearest node on the same level that is to the right of u, or return null if u is the rightmost node in its level.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,null,4,5,6], u = 4
 * Output: 5
 * Explanation: The nearest node on the same level to the right of node 4 is node 5.
 * Example 2:
 * <p>
 * Input: root = [3,null,4,2], u = 2
 * Output: null
 * Explanation: There are no nodes to the right of 2.
 * Example 3:
 * <p>
 * Input: root = [1], u = 1
 * Output: null
 * Example 4:
 * <p>
 * Input: root = [3,4,2,null,null,null,1], u = 4
 * Output: 2
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 105].
 * 1 <= Node.val <= 105
 * All values in the tree are distinct.
 * u is a node in the binary tree rooted at root.
 */
public class FindNearestRightNodeInBT {

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

    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        Queue<TreeNode> currLevel = new LinkedList<>();
        currLevel.add(root);
        while (!currLevel.isEmpty()) {
            Queue<TreeNode> nextLevel = new LinkedList<>();
            while (!currLevel.isEmpty()) {
                TreeNode currNode = currLevel.poll();
                if (currNode.equals(u)) {
                    if (!currLevel.isEmpty()) return currLevel.poll();
                    else return null;
                }
                if (currNode.left != null) nextLevel.add(currNode.left);
                if (currNode.right != null) nextLevel.add(currNode.right);
            }
            currLevel = nextLevel;
        }
        return null;
    }

    public static void main(String[] args) {
        FindNearestRightNodeInBT f = new FindNearestRightNodeInBT();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.left = node5;
        node3.right = node6;
        System.out.println(f.findNearestRightNode(node1, node4).val);
    }

}
