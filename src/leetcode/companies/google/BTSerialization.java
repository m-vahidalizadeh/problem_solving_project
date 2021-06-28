package leetcode.companies.google;

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
public class BTSerialization {

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
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("()");
            return;
        }
        sb.append('(').append(root.val);
        serialize(root.left, sb);
        serialize(root.right, sb);
        sb.append(')');
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("()".equals(data)) return null;
        data = data.substring(1, data.length() - 1); // Unwrap the two parenthesis (root(left)(right))
        int i = 0;
        while (data.charAt(i) != '(') i++;
        TreeNode root = new TreeNode(Integer.parseInt(data.substring(0, i)));
        data = data.substring(i);
        i = -1;
        int open = 0;
        while (true) { // Find left child- starts with parenthesis
            i++;
            if (data.charAt(i) == '(') open++;
            else if (data.charAt(i) == ')') {
                open--;
                if (open == 0) break;
            }
        }
        root.left = deserialize(data.substring(0, i + 1));
        data = data.substring(i + 1); // The rest is right child- starts with parenthesis
        root.right = deserialize(data);
        return root;
    }

}
