package leetcode.companies.facebook;

import java.util.*;

/**
 * 1305. All Elements in Two Binary Search Trees
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.
 *
 * Example 1:
 *
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 * Example 2:
 *
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 *
 * Constraints:
 *
 * The number of nodes in each tree is in the range [0, 5000].
 * -10^5 <= Node.val <= 10^5
 */
public class GetAllElementsSorted {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Deque<TreeNode> stack1 = new ArrayDeque<>(), stack2 = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        while (root1 != null) {
            stack1.push(root1);
            root1 = root1.left;
        }
        while (root2 != null) {
            stack2.push(root2);
            root2 = root2.left;
        }
        while(!stack1.isEmpty()||!stack2.isEmpty()){
            if (!stack1.isEmpty() && !stack2.isEmpty()) {
                if (stack1.peek().val <= stack2.peek().val) {
                    TreeNode node = stack1.pop();
                    list.add(node.val);
                    if (node.right != null) {
                        root1 = node.right;
                        while (root1 != null) {
                            stack1.push(root1);
                            root1 = root1.left;
                        }
                    }
                } else {
                    TreeNode node = stack2.pop();
                    list.add(node.val);
                    if (node.right != null) {
                        root2 = node.right;
                        while (root2 != null) {
                            stack2.push(root2);
                            root2 = root2.left;
                        }
                    }
                }
            } else if (!stack1.isEmpty()) {
                TreeNode node = stack1.pollFirst();
                list.add(node.val);
                if (node.right != null) {
                    root1 = node.right;
                    while (root1 != null) {
                        stack1.push(root1);
                        root1 = root1.left;
                    }
                }
            } else { // Stack2 is not empty
                TreeNode node = stack2.pop();
                list.add(node.val);
                if (node.right != null) {
                    root2 = node.right;
                    while (root2 != null) {
                        stack2.push(root2);
                        root2 = root2.left;
                    }
                }
            }
        }
        return list;
    }

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

}
