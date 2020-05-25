package leetcode.companies.facebook;

import leetcode.base.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class leetcode.base.TreeNode {
 * int val;
 * leetcode.base.TreeNode left;
 * leetcode.base.TreeNode right;
 * leetcode.base.TreeNode(int x) { val = x; }
 * }
 */

public class FBFindDeepestSubtree {

    static Map<TreeNode, TreeNode> parents = new HashMap<>();
    static Map<TreeNode, Integer> depths = new HashMap<>();
    static Map<Integer, List<TreeNode>> deepestNodes = new HashMap<>();

    public static void main(String[] args) {
        /*
Example 1:
Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation:
We return the node with value 2, colored in yellow in the diagram.
The nodes colored in blue are the deepest nodes of the tree.
The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
Both the input and output have leetcode.base.TreeNode type.
         */
//        leetcode.base.TreeNode node3 = new leetcode.base.TreeNode(3);
//        leetcode.base.TreeNode node5 = new leetcode.base.TreeNode(5);
//        leetcode.base.TreeNode node1 = new leetcode.base.TreeNode(1);
//        leetcode.base.TreeNode node6 = new leetcode.base.TreeNode(6);
//        leetcode.base.TreeNode node2 = new leetcode.base.TreeNode(2);
//        leetcode.base.TreeNode node0 = new leetcode.base.TreeNode(0);
//        leetcode.base.TreeNode node8 = new leetcode.base.TreeNode(8);
//        leetcode.base.TreeNode node7 = new leetcode.base.TreeNode(7);
//        leetcode.base.TreeNode node4 = new leetcode.base.TreeNode(4);
//        node3.left = node5;
//        node3.right = node1;
//        node1.right = node8;
//        node1.left = node0;
//        node5.left = node6;
//        node5.right = node2;
//        node2.left = node7;
//        node2.right = node4;
//        System.out.println(subtreeWithAllDeepest(node3).val);
        TreeNode node0 = new TreeNode(0);
        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5);
        node0.left = node3;
        node0.right = node1;
        node3.right = node2;
        node3.left = node4;
        node2.right = node5;
        node4.right = node6;
        System.out.println(subtreeWithAllDeepest(node0).val);
    }

    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        int depth = 0;
        int maxDepth = 0;
        TreeNode maxParent = root;
        TreeNode maxChild = root;
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            if (!depths.containsKey(node)) {
                depths.put(node, depth);
            } else {
                depth = depths.get(node);
            }
            if (depth > maxDepth) {
                maxDepth = depth;
                maxParent = parents.get(node);
                maxChild = node;
            }
            if (deepestNodes.containsKey(depth)) {
                deepestNodes.get(depth).add(node);
            } else {
                List<TreeNode> tempList = new ArrayList<>();
                tempList.add(node);
                deepestNodes.put(depth, tempList);
            }
            TreeNode leftChild = node.left;
            if (leftChild != null) {
                parents.put(leftChild, node);
                nodes.add(leftChild);
                depths.put(leftChild, depth + 1);
            }
            TreeNode rightChild = node.right;
            if (rightChild != null) {
                parents.put(rightChild, node);
                nodes.add(rightChild);
                depths.put(rightChild, depth + 1);
            }
            depth++;
        }
        if (deepestNodes.get(maxDepth).size() == 1) {
            if (maxParent.left != null && maxParent.right != null) {
                return maxParent;
            }
            return maxChild;
        }
        List<TreeNode> deepestNodesFound = deepestNodes.get(maxDepth);
        TreeNode a = deepestNodesFound.get(0);
        TreeNode b = deepestNodesFound.get(1);
        TreeNode parentA = parents.get(a);
        TreeNode parentB = parents.get(b);
        while (parentA != parentB) {
            parentA = parents.get(parentA);
            parentB = parents.get(parentB);
        }
        return parentA;
    }

}
