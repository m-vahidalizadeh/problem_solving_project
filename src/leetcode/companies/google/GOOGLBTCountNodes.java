package leetcode.companies.google;

import leetcode.base.TreeNode;

public class GOOGLBTCountNodes {

    private int counter = 0;

    public static void main(String[] args) {
/*
Example:
Input:
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
 */
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        GOOGLBTCountNodes googlbtCountNodes = new GOOGLBTCountNodes();
        System.out.println(googlbtCountNodes.countNodes(node1));
    }

    public int countNodes(TreeNode root) {
        traverse(root);
        return counter;
    }

    private void traverse(TreeNode node) {
        if (node == null)
            return;
        counter++;
        traverse(node.left);
        traverse(node.right);
    }

}
