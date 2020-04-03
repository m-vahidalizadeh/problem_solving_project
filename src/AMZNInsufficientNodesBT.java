import base.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AMZNInsufficientNodesBT {

    Map<TreeNode, TreeNode> parents = new HashMap<>();
    Set<TreeNode> insufficientNodes = new HashSet<>();
    Map<TreeNode, Integer> sums = new HashMap<>();
    TreeNode root;
    Set<TreeNode> leafs = new HashSet<>();

    public static void main(String[] args) {
/*
Example 1:
Input: root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
Output: [1,2,3,4,null,null,7,8,9,null,14]
Example 2:
Input: root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
Output: [5,4,8,11,null,17,4,7,null,null,null,5]
Example 3:
Input: root = [1,2,-3,-5,null,4,null], limit = -1
Output: [1,null,-3,4]
 */
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode nodeM3 = new TreeNode(-3);
        TreeNode nodeM5 = new TreeNode(-5);
        TreeNode node4 = new TreeNode(4);
        node1.left = node2;
        node1.right = nodeM3;
        node2.left = nodeM5;
        nodeM3.left = node4;
        AMZNInsufficientNodesBT amznInsufficientNodesBT = new AMZNInsufficientNodesBT();
        amznInsufficientNodesBT.sufficientSubset(node1, -1);
        System.out.println();
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        this.root = root;
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            if (root.val > limit) {
                return root;
            } else {
                return null;
            }
        }
        parents.put(root, null);
        sums.put(root, root.val);
        exploreTree(root, root.val, limit);
        for (TreeNode n : leafs) {
            deleteInsufficientNodes(n);
        }
        return this.root;
    }

    public void deleteInsufficientNodes(TreeNode node) {
        if (node == null)
            return;
        if (parents.containsKey(node)) {
            TreeNode left = node.left;
            TreeNode right = node.right;
            TreeNode parent = parents.get(node);
            if ((left == null || insufficientNodes.contains(left)) && (right == null || insufficientNodes.contains(right))) {
                parents.remove(node);
                if (parent.left == node)
                    parent.left = null;
                if (parent.right == node)
                    parent.right = null;
            }
            if (insufficientNodes.contains(node)) {
                if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleteInsufficientNodes(parent);
            }
        }
    }

    public void exploreTree(TreeNode node, int sum, int limit) {
        if (node == null) {
            return;
        }
        if (sum < limit)
            insufficientNodes.add(node);
        TreeNode left = node.left;
        if (left != null) {
            parents.put(node, left);
            sums.put(left, sum + left.val);
            exploreTree(left, sum + left.val, limit);
        }
        TreeNode right = node.right;
        if (right != null) {
            parents.put(node, right);
            sums.put(right, sum + right.val);
            exploreTree(right, sum + right.val, limit);
        }
        if (left == null && right == null)
            leafs.add(node);
    }

}
