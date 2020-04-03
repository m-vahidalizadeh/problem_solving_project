import base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal {

    public static void main(String[] args) {
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node2 = new TreeNode(2);
        TreeNode node6 = new TreeNode(6);
        TreeNode node6_2 = new TreeNode(6);
        TreeNode node2_2 = new TreeNode(2);
        node4.left = node7;
        node4.right = node9;
        node7.left = node10;
        node7.right = node2;
        node9.right = node6;
        node2.right = node6_2;
        node6_2.left = node2_2;
        TreeTraversal treeTraversal = new TreeTraversal();
        System.out.println("BFS:");
        treeTraversal.bfs(node4);
        System.out.println("\nDFS:");
        treeTraversal.dfs(node4);
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        System.out.print(node.val + " ");
        dfs(node.right);
    }


    public void bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode currentNode = q.poll();
            System.out.print(currentNode.val + " ");
            TreeNode left = currentNode.left;
            if (left != null)
                q.add(currentNode.left);
            TreeNode right = currentNode.right;
            if (right != null)
                q.add(currentNode.right);
        }
    }

}
