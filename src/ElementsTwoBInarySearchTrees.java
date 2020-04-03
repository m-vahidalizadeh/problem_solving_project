import base.TreeNode;

import java.util.*;

public class ElementsTwoBInarySearchTrees {

    Queue<Integer>[] queues = new LinkedList[2];

    public static void main(String[] args) {
        ElementsTwoBInarySearchTrees elementsTwoBInarySearchTrees = new ElementsTwoBInarySearchTrees();
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        TreeNode node1_2 = new TreeNode(1);
        TreeNode node0 = new TreeNode(0);
        TreeNode node3 = new TreeNode(3);
        node2.left = node1;
        node2.right = node4;
        node1_2.left = node0;
        node1_2.right = node3;
        List<Integer> result = elementsTwoBInarySearchTrees.getAllElements(node2, node1_2);
        for (Integer e : result) {
            System.out.print(e + " ");
        }
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        queues[0] = new LinkedList<>();
        dfs(root1, 0);
        queues[1] = new LinkedList<>();
        dfs(root2, 1);
        List<Integer> result = new ArrayList<>();
        while (!queues[0].isEmpty() || !queues[1].isEmpty()) {
            if (queues[0].isEmpty()) {
                result.add(queues[1].poll());
            } else if (queues[1].isEmpty()) {
                result.add(queues[0].poll());
            } else {
                if (queues[0].peek() < queues[1].peek()) {
                    result.add(queues[0].poll());
                } else {
                    result.add(queues[1].poll());
                }
            }
        }
        return result;
    }

    public void dfs(TreeNode node, int sNumber) {
        if (node == null) {
            return;
        }
        dfs(node.left, sNumber);
        queues[sNumber].add(node.val);
        dfs(node.right, sNumber);
    }

}
