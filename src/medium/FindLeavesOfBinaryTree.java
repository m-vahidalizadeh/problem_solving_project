package medium;

import base.TreeNode;

import java.util.*;

/**
 * Find Leaves of Binary Tree
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3,4,5]
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * Output: [[4,5,3],[2],[1]]
 * <p>
 * Explanation:
 * <p>
 * 1. Removing the leaves [4,5,3] would result in this tree:
 * <p>
 * 1
 * /
 * 2
 * <p>
 * 2. Now removing the leaf [2] would result in this tree:
 * <p>
 * 1
 * <p>
 * 3. Now removing the leaf [1] would result in the empty tree:
 * <p>
 * []
 */
public class FindLeavesOfBinaryTree {

    private void exploreTree(TreeNode node, Map<TreeNode, TreeNode> parents, List<TreeNode> leaves) {
        if (node == null)
            return;
        TreeNode l = node.left;
        TreeNode r = node.right;
        boolean isRNull = r == null;
        boolean isLNull = l == null;
        if (isRNull && isLNull) {
            leaves.add(node);
        } else {
            if (!isLNull) {
                parents.put(l, node);
                exploreTree(l, parents, leaves);
            }
            if (!isRNull) {
                parents.put(r, node);
                exploreTree(r, parents, leaves);
            }
        }
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        List<TreeNode> leaves = new ArrayList<>();
        exploreTree(root, parents, leaves);
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.addAll(leaves);
        while (!nodes.isEmpty()) {
            Queue<TreeNode> newNodes = new LinkedList<>();
            List<Integer> tempList = new ArrayList<>();
            while (!nodes.isEmpty()) {
                TreeNode n = nodes.poll();
                tempList.add(n.val);
                TreeNode parent = parents.get(n);
                if (parent != null) {
                    if (parent.left == n) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                    if (parent.left == null && parent.right == null) {
                        newNodes.add(parent);
                    }
                }
            }
            result.add(tempList);
            // Build next gen of leaves
            nodes.addAll(newNodes);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        FindLeavesOfBinaryTree findLeavesOfBinaryTree = new FindLeavesOfBinaryTree();
        findLeavesOfBinaryTree.findLeaves(node1);
    }

}
