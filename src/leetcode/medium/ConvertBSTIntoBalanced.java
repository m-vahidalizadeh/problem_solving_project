package leetcode.medium;

import java.util.Vector;

import leetcode.base.TreeNode;

public class ConvertBSTIntoBalanced {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.right = node2;
        node2.right = node3;
        node3.right = node4;
        ConvertBSTIntoBalanced convertBSTIntoBalanced = new ConvertBSTIntoBalanced();
        TreeNode balancedBSTRoot = convertBSTIntoBalanced.balanceBST(node1);
    }

    private void saveNodes(TreeNode node, Vector<TreeNode> nodes) {
        if (node == null)
            return;
        saveNodes(node.left, nodes);
        nodes.add(node);
        saveNodes(node.right, nodes);
    }

    private TreeNode buildTree(Vector<TreeNode> nodes, int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        TreeNode midNode = nodes.get(mid);
        midNode.left = buildTree(nodes, start, mid - 1);
        midNode.right = buildTree(nodes, mid + 1, end);
        return midNode;
    }

    public TreeNode balanceBST(TreeNode root) {
        Vector<TreeNode> nodes = new Vector<>();
        saveNodes(root, nodes);
        return buildTree(nodes, 0, nodes.size() - 1);
    }

}
