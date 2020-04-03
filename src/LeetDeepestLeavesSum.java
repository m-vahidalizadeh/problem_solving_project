import base.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetDeepestLeavesSum {

    Map<TreeNode, Integer> depths = new HashMap<>();
    Set<TreeNode> leaves = new HashSet<>();
    int maxDepth = 0;

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node7;
        node3.right = node6;
        node6.right = node8;
        LeetDeepestLeavesSum leetDeepestLeavesSum = new LeetDeepestLeavesSum();
        System.out.println(leetDeepestLeavesSum.deepestLeavesSum(node1));
    }

    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        depths.put(root, 0);
        dfs(root);
        int sum = 0;
        for (TreeNode leaf : leaves) {
            if (depths.get(leaf) == maxDepth) {
                sum += leaf.val;
            }
        }
        return sum;
    }

    public void dfs(TreeNode node) {
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (right == null && left == null) {
            if (depths.get(node) >= maxDepth) {
                leaves.add(node);
            }
        } else {
            int childDepth = depths.get(node) + 1;
            if (childDepth > maxDepth) {
                maxDepth = childDepth;
            }
            if (left != null) {
                depths.put(left, childDepth);
                dfs(left);
            }
            if (right != null) {
                depths.put(right, childDepth);
                dfs(right);
            }
        }
    }

}
