package leetcode.companies.bloomberg;

import leetcode.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BLBTCousins {
    /**
     * Definition for a binary tree node.
     * public class leetcode.base.TreeNode {
     * int val;
     * leetcode.base.TreeNode left;
     * leetcode.base.TreeNode right;
     * leetcode.base.TreeNode(int x) { val = x; }
     * }
     */

    public static Map<Integer, Integer> depths = new HashMap<>();
    public static Map<Integer, TreeNode> parents = new HashMap<>();

    public static void main(String[] args) {
        /*
Example 1:
Input: root = [1,2,3,4], x = 4, y = 3
Output: false

Example 2:
Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true

Example 3:
Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false
         */
        BLBTCousins blbtCousins = new BLBTCousins();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        // Example 1
//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        System.out.println(blbtCousins.isCousins(node1, 4, 3));
        // Example 2
//        node1.left = node2;
//        node1.right = node3;
//        node3.right = node5;
//        node2.right = node4;
//        System.out.println(blbtCousins.isCousins(node1, 5, 4));
        // Example 3
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        System.out.println(blbtCousins.isCousins(node1, 2, 3));
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        parents.put(root.val, null);
        traverseTree(root);
        TreeNode parentX = parents.get(x);
        TreeNode parentY = parents.get(y);
        int depthX = depths.get(x);
        int depthY = depths.get(y);
        return depthX == depthY && parentX != parentY;
    }

    public void traverseTree(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode parentNode = parents.get(node.val);
        if (parentNode == null) {
            // Root
            depths.put(node.val, 0);
        } else {
            int parentDepth = depths.get(parentNode.val);
            depths.put(node.val, parentDepth + 1);
        }
        TreeNode leftChild = node.left;
        TreeNode rightChild = node.right;
        if (leftChild != null) {
            parents.put(leftChild.val, node);
            traverseTree(leftChild);
        }
        if (rightChild != null) {
            parents.put(rightChild.val, node);
            traverseTree(rightChild);
        }
    }

}
