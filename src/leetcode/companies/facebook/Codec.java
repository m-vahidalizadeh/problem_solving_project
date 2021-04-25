package leetcode.companies.facebook;

/**
 * 297. Serialize and Deserialize Binary Tree
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [1]
 * Output: [1]
 * Example 4:
 *
 * Input: root = [1,2]
 * Output: [1,2]
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 */
public class Codec {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        return root.val + "[" + serialize(root.left) + "]" + "[" + serialize(root.right) + "]";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data)) return null;
        int firstBracketIndex = data.indexOf('[');
        TreeNode root = new TreeNode(Integer.valueOf(data.substring(0, firstBracketIndex)));
        int i = firstBracketIndex;
        int open = 1;
        int end = firstBracketIndex;
        while (open != 0) {
            end++;
            if (data.charAt(end) == '[') open++;
            else if (data.charAt(end) == ']') open--;
        }
        TreeNode left = deserialize(data.substring(i + 1, end));
        i = end + 1;
        end = i;
        open = 1;
        while (open != 0) {
            end++;
            if (data.charAt(end) == '[') open++;
            else if (data.charAt(end) == ']') open--;
        }
        TreeNode right = deserialize(data.substring(i + 1, end));
        root.left = left;
        root.right = right;
        return root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        Codec c = new Codec();
        String res = c.serialize(node1);
        TreeNode root = c.deserialize(res);
        System.out.println();
    }

}
