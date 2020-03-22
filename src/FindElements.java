import java.util.*;

public class FindElements {

    Set<Integer> existingNodes = new HashSet<>();

    public FindElements(TreeNode root) {
        root.val = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode currentNode = q.poll();
            TreeNode left = currentNode.left;
            if (left != null) {
                int newVal = currentNode.val * 2 + 1;
                existingNodes.add(newVal);
                left.val = newVal;
                q.add(left);
            }
            TreeNode right = currentNode.right;
            if (right != null) {
                int newVal = currentNode.val * 2 + 2;
                existingNodes.add(newVal);
                right.val = newVal;
                q.add(right);
            }
        }
    }

    public boolean find(int target) {
        return existingNodes.contains(target);
    }

}
