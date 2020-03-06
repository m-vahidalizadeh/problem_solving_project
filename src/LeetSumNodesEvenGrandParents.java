import java.util.HashMap;
import java.util.Map;

public class LeetSumNodesEvenGrandParents {

    Map<TreeNode, TreeNode> parents = new HashMap<>();
    int sum = 0;

    public static void main(String[] args) {
        /*
Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
         */
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node2 = new TreeNode(2);
        TreeNode node7_2 = new TreeNode(7);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node1_2 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node6.left = node7;
        node6.right = node8;
        node7.left = node2;
        node7.right = node7_2;
        node2.left = node9;
        node7_2.left = node1_2;
        node7_2.right = node4;
        node8.left = node1;
        node8.right = node3;
        node3.right = node5;
        LeetSumNodesEvenGrandParents leetSumNodesEvenGrandParents = new LeetSumNodesEvenGrandParents();
        System.out.println(leetSumNodesEvenGrandParents.sumEvenGrandparent(node6));
    }

    public int sumEvenGrandparent(TreeNode root) {
        if (root == null)
            return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (right == null && left == null) {
            return 0;
        }
        traverseTree(root);
        findEvenGrandParents(root);
        return sum;
    }

    public void findEvenGrandParents(TreeNode node) {
        if (parents.containsKey(node)) {
            TreeNode nodeParent = parents.get(node);
            if (parents.containsKey(nodeParent)) {
                TreeNode nodeGrandParent = parents.get(nodeParent);
                if (nodeGrandParent.val % 2 == 0) {
                    sum += node.val;
                }
            }
        }
        TreeNode left = node.left;
        if (left != null)
            findEvenGrandParents(left);
        TreeNode right = node.right;
        if (right != null)
            findEvenGrandParents(right);
    }

    public void traverseTree(TreeNode node) {
        TreeNode left = node.left;
        if (left != null) {
            parents.put(left, node);
            traverseTree(left);
        }
        TreeNode right = node.right;
        if (right != null) {
            parents.put(right, node);
            traverseTree(right);
        }
    }

}
