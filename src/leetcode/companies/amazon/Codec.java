package leetcode.companies.amazon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public String serialize(TreeNode root) {
        return ser_rec(root, "");
    }

    private String ser_rec(TreeNode node, String str) {
        if (node == null) {
            str += "null,";
        } else {
            str += node.val + ",";
            str = ser_rec(node.left, str);
            str = ser_rec(node.right, str);
        }
        return str;
    }

    public TreeNode deserialize(String data) {
        return des_rec(new LinkedList<String>(Arrays.asList(data.split(","))));
    }

    private TreeNode des_rec(List<String> list) {
        if ("null".equals(list.get(0))) {
            list.remove(0);
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
            list.remove(0);
            root.left = des_rec(list);
            root.right = des_rec(list);
            return root;
        }
    }

}
