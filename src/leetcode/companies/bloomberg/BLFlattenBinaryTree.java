package leetcode.companies.bloomberg;

import leetcode.base.TreeNode;

import java.util.Stack;

public class BLFlattenBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        root.right = node5;
        root.left = node2;
        node2.left = node3;
        node2.right = node4;
        node5.right = node6;
        node5.left = null;
        flatten(root);
    }

    public static void flatten(TreeNode root) {
        TreeNode flatTreeCurrentNode = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode currentNode = stack.pop();
            if (currentNode != null && currentNode.equals(root)) {
                if (currentNode.right != null)
                    stack.push(currentNode.right);
                if (currentNode.left != null)
                    stack.push(currentNode.left);
                currentNode.left = null;
                currentNode.right = null;
                flatTreeCurrentNode = root;
            } else if (currentNode != null) {
                if (currentNode.right != null)
                    stack.push(currentNode.right);
                if (currentNode.left != null)
                    stack.push(currentNode.left);
                flatTreeCurrentNode.right = currentNode;
                flatTreeCurrentNode.left = null;
                flatTreeCurrentNode = flatTreeCurrentNode.right;
            }
        }
    }

}
