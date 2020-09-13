package leetcode.companies.google;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Serialize and Deserialize Binary Tree
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Example:
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeAndDeserializeBT {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public String serialize(TreeNode root) {
        if (root == null) return "null";
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        List<String> nodeList = new LinkedList<String>(Arrays.asList(nodes.clone()));
        return buildTree(nodeList);
    }

    private TreeNode buildTree(List<String> nodeList) {
        if ("null".equals(nodeList.get(0))) {
            nodeList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodeList.get(0)));
        nodeList.remove(0);
        root.left = buildTree(nodeList);
        root.right = buildTree(nodeList);
        return root;
    }

    public static void main(String[] args) {
        SerializeAndDeserializeBT s = new SerializeAndDeserializeBT();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node5;
        node2.left = node3;
        node2.right = node4;
        System.out.println(s.serialize(node1));
    }

}
