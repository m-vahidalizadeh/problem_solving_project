package leetcode.companies.facebook;

import leetcode.base.TreeNode;

import java.util.*;

public class FBCheckCompletenessOfBT {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        FBCheckCompletenessOfBT fbCheckCompletenessOfBT = new FBCheckCompletenessOfBT();
        System.out.println(fbCheckCompletenessOfBT.isCompleteTree(node1));
    }

    int h = 0;
    Map<Integer, List<TreeNode>> levels = new HashMap<>();
    Map<TreeNode, TreeNode> parents = new HashMap<>();
    Map<TreeNode, Integer> heights = new HashMap<>();

    public boolean isCompleteTree(TreeNode root) {
        if (root == null)
            return true;
        explore(root);
        for (int i = 0; i <= h - 1; i++) {
            if (i <= h - 2) {
                int requiredHeight = (int) Math.pow(2, i);
                if (levels.get(i).size() != requiredHeight) {
                    return false;
                }
            } else {
                int requiredHeight = (int) Math.pow(2, i);
                if (levels.get(i).size() != requiredHeight) {
                    return false;
                }
                List<TreeNode> hMinusOneList = levels.get(i);
                int n = hMinusOneList.size();
                boolean noMoreChild = false;
                for (int j = 0; j < n; j++) {
                    TreeNode currentNode = hMinusOneList.get(j);
                    TreeNode l = currentNode.left;
                    TreeNode r = currentNode.right;
                    if ((l != null || r != null)
                            && noMoreChild) {
                        return false;
                    }
                    if (r != null && l == null) {
                        return false;
                    }
                    if (l != null && r == null) {
                        noMoreChild = true;
                    }
                    if (l == null && r == null) {
                        noMoreChild = true;
                    }
                }
            }
        }
        return true;
    }

    private void explore(TreeNode root) {
        heights.put(root, 0);
        levels.put(0, List.of(root));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode currentNode = q.poll();
            int newHeight = heights.get(currentNode) + 1;
            TreeNode l = currentNode.left;
            if (l != null) {
                q.add(l);
                parents.put(l, currentNode);
                heights.put(l, newHeight);
                if (levels.containsKey(newHeight)) {
                    List<TreeNode> currentS = levels.get(newHeight);
                    currentS.add(l);
                    levels.put(newHeight, currentS);
                } else {
                    List<TreeNode> currentS = new ArrayList<>();
                    currentS.add(l);
                    levels.put(newHeight, currentS);
                }
            }
            TreeNode r = currentNode.right;
            if (r != null) {
                q.add(r);
                parents.put(r, currentNode);
                heights.put(r, newHeight);
                if (levels.containsKey(newHeight)) {
                    List<TreeNode> currentS = levels.get(newHeight);
                    currentS.add(r);
                    levels.put(newHeight, currentS);
                } else {
                    List<TreeNode> currentS = new ArrayList<>();
                    currentS.add(r);
                    levels.put(newHeight, currentS);
                }
            }
            if (l != null || r != null) {
                if (h < newHeight) {
                    h = newHeight;
                }
            }
        }
    }

}
