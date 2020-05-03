package leetcode.amazon;

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
public class AMZNBTLevelTraversalLTR {

    Map<TreeNode, Integer> depths = new HashMap<>();
    Map<TreeNode, TreeNode> parents = new HashMap<>();
    Map<Integer, List<Integer>> levels = new HashMap<>();
    int maxDepth = 0;

    public static void main(String[] args) {
    /*
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
     */
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        node3.left = node9;
        node3.right = node20;
        node20.right = node7;
        node20.left = node15;
        AMZNBTLevelTraversalLTR amznbtLevelTraversalLTR = new AMZNBTLevelTraversalLTR();
        List<List<Integer>> result = amznbtLevelTraversalLTR.levelOrder(node3);
        for (List<Integer> list : result) {
            for (Integer e : list) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        depths.put(root, 0);
        levels.put(0, List.of(root.val));
        traverse(root);
        for (int i = 0; i <= maxDepth; i++) {
            List<Integer> tempList = levels.get(i);
            if (tempList != null) {
                result.add(tempList);
            }
        }
        return result;
    }

    public void traverse(TreeNode node) {
        if (node == null)
            return;
        int nodeDepth = depths.get(node);
        TreeNode left = node.left;
        TreeNode right = node.right;
        int childDepth = nodeDepth + 1;
        if (childDepth > maxDepth) {
            maxDepth = childDepth;
        }
        if (left != null) {
            depths.put(left, childDepth);
            parents.put(left, node);
            traverse(left);
            if (levels.containsKey(childDepth)) {
                List<Integer> tempList = levels.get(childDepth);
                tempList.add(left.val);
            } else {
                List<Integer> tempList = new ArrayList<>();
                tempList.add(left.val);
                levels.put(childDepth, tempList);
            }
        }
        if (right != null) {
            depths.put(right, childDepth);
            parents.put(right, node);
            traverse(right);
            if (levels.containsKey(childDepth)) {
                List<Integer> tempList = levels.get(childDepth);
                tempList.add(right.val);
            } else {
                List<Integer> tempList = new ArrayList<>();
                tempList.add(right.val);
                levels.put(childDepth, tempList);
            }
        }
    }

}
