package leetcode.companies.facebook;

import leetcode.base.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class leetcode.base.TreeNode {
 * int val;
 * leetcode.base.TreeNode left;
 * leetcode.base.TreeNode right;
 * leetcode.base.TreeNode(int x) { val = x; }
 * }
 */
public class FBBTVerticalTraverse {

    Map<TreeNode, TreeNode> parents = new HashMap<>();
    Map<TreeNode, Integer> numbers = new HashMap<>();
    Map<Integer, List<Integer>> resultMap = new HashMap<>();
    int minNumber = Integer.MAX_VALUE;
    int maxNumber = Integer.MIN_VALUE;
    TreeNode mainRoot;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        mainRoot = root;
        List<List<Integer>> result = new LinkedList<>();
        if (root == null)
            return result;
        parents.put(root, null);
        numbers.put(root, 0);
        traverse(root);
        for (int i = minNumber; i <= maxNumber; i++) {
            if (resultMap.containsKey(i)) {
                List<Integer> tempList = resultMap.get(i);
                result.add(tempList);
            }
        }
        return result;
    }

    public void traverse(TreeNode node) {
        int number;
        if (node == mainRoot) {
            number = 0;
            List<Integer> tempList = new LinkedList<>();
            tempList.add(node.val);
            resultMap.put(0, tempList);
        } else {
            number = numbers.get(node);
        }
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (left != null) {
            int childNumber = number - 1;
            if (childNumber < minNumber)
                minNumber = childNumber;
            parents.put(left, node);
            numbers.put(left, childNumber);
            if (resultMap.containsKey(childNumber)) {
                List<Integer> tempList = resultMap.get(childNumber);
                tempList.add(left.val);
            } else {
                List<Integer> tempList = new LinkedList<>();
                tempList.add(left.val);
                resultMap.put(childNumber, tempList);
            }
            traverse(left);
        }
        if (right != null) {
            int childNumber = number + 1;
            if (childNumber > maxNumber)
                maxNumber = childNumber;
            parents.put(right, node);
            numbers.put(right, childNumber);
            if (resultMap.containsKey(childNumber)) {
                List<Integer> tempList = resultMap.get(childNumber);
                tempList.add(right.val);
            } else {
                List<Integer> tempList = new LinkedList<>();
                tempList.add(right.val);
                resultMap.put(childNumber, tempList);
            }
            traverse(right);
        }
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        node3.left = node9;
        node3.right = node20;
        node20.left = node15;
        node20.right = node7;
        FBBTVerticalTraverse fbbtVerticalTraverse = new FBBTVerticalTraverse();
        List<List<Integer>> result = fbbtVerticalTraverse.verticalOrder(node3);
        for (List<Integer> l : result) {
            for (Integer e : l) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
        /*
Examples 1:
Input: [3,9,20,null,null,15,7]
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
Output:
[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:
Input: [3,9,8,4,0,1,7]
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
Output:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:
Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
Output:
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
         */
    }

}
