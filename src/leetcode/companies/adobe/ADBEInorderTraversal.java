package leetcode.companies.adobe;

import leetcode.base.TreeNode;

import java.util.*;

public class ADBEInorderTraversal {

    public static void main(String[] args) {
    /*
Example:
Input: [1,null,2,3]
   1
    \
     2
    /
   3
Output: [1,3,2]
     */
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.left = node3;
        ADBEInorderTraversal adbeInorderTraversal = new ADBEInorderTraversal();
        List<Integer> result = adbeInorderTraversal.inorderTraversal(node1);
        for (Integer e : result) {
            System.out.println(e);
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Stack<TreeNode> s = new Stack<>();
        List<Integer> result = new LinkedList<>();
        s.push(root);
        TreeNode currentNode = root.left;
        while (!(s.isEmpty() && currentNode == null)) {
            if (currentNode != null) {
                s.push(currentNode);
                currentNode = currentNode.left;
            } else {
                TreeNode poppedNode = s.pop();
                result.add(poppedNode.val);
                currentNode = poppedNode.right;
            }
        }
        return result;
    }

}
