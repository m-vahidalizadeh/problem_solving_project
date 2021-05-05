package leetcode.companies.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * 1305. All Elements in Two Binary Search Trees
 * Given two binary search trees root1 and root2.
 *
 * Return a list containing all the integers from both trees sorted in ascending order.
 *
 * Example 1:
 *
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 * Example 2:
 *
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * Output: [-10,0,0,1,2,5,7,10]
 * Example 3:
 *
 * Input: root1 = [], root2 = [5,1,7,0,2]
 * Output: [0,1,2,5,7]
 * Example 4:
 *
 * Input: root1 = [0,-10,10], root2 = []
 * Output: [-10,0,10]
 * Example 5:
 *
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 *
 * Constraints:
 *
 * Each tree has at most 5000 nodes.
 * Each node's value is between [-10^5, 10^5].
 */
public class MergeBSTs {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        getList(root1, list1);
        List<Integer> list2 = new ArrayList<>();
        getList(root2, list2);
        return mergeLists(list1, list2);
    }

    private List<Integer> mergeLists(List<Integer> list1, List<Integer> list2) {
        int i1 = 0;
        int i2 = 0;
        List<Integer> res = new ArrayList<>();
        while (i1 < list1.size() && i2 < list2.size()) {
            if (list1.get(i1) <= list2.get(i2)) res.add(list1.get(i1++));
            else res.add(list2.get(i2++));
        }
        while (i1 < list1.size()) {
            res.add(list1.get(i1++));
        }
        while (i2 < list2.size()) {
            res.add(list2.get(i2++));
        }
        return res;
    }

    private void getList(TreeNode node, List<Integer> list) {
        if (node == null) return;
        getList(node.left, list);
        list.add(node.val);
        getList(node.right, list);
    }

}
