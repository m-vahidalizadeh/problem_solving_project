import java.util.*;

public class CompleteTree {

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
        CompleteTree completeTree = new CompleteTree();
        System.out.println(completeTree.isCompleteTree(node1));
    }

    public boolean isCompleteTree(TreeNode root) {
        Map<Integer, Queue<TreeNode>> levels = new HashMap<>();
        if (root == null) {
            return true;
        }
        Queue<TreeNode> children = new LinkedList<>();
        Queue<TreeNode> currLevel = new LinkedList<>();
        currLevel.add(root);
        Queue<TreeNode> ll = new LinkedList<>();
        ll.add(root);
        levels.put(0, ll);
        boolean done = false;
        int currentH = 0;
        while (!done) {
            if (currLevel.isEmpty()) {
                if (children.isEmpty()) {
                    done = true;
                } else {
                    currLevel = new LinkedList<>();
                    currLevel.addAll(children);
                    children = new LinkedList<>();
                    currentH++;
                    levels.put(currentH, new LinkedList<>(currLevel));
                }
            } else {
                TreeNode currentNode = currLevel.poll();
                TreeNode l = currentNode.left;
                if (l != null) {
                    children.add(l);
                }
                TreeNode r = currentNode.right;
                if (r != null) {
                    children.add(r);
                }
            }
        }

        for (int i = 0; i < currentH; i++) {
            boolean isLevelComplete;
            if (i < currentH - 1) {
                isLevelComplete = isLevelComplete(i, levels.get(i), false);
            } else {
                isLevelComplete = isLevelComplete(i, levels.get(i), true);
            }
            if (!isLevelComplete) {
                return false;
            }
        }

        return true;
    }

    private boolean isLevelComplete(int h, Queue<TreeNode> level, boolean isLevelHMinusOne) {
        if (isLevelHMinusOne) {
            if (level.size() != (int) Math.pow(2, h)) {
                return false;
            }
            int n = level.size();
            boolean noMoreChild = false;
            for (int i = 0; i < n; i++) {
                TreeNode c = level.poll();
                TreeNode l = c.left;
                TreeNode r = c.right;
                if (noMoreChild && (l != null || r != null)) {
                    return false;
                }
                if (l == null && r != null) {
                    return false;
                }
                if ((l != null && r == null)
                        || (l == null && r == null)) {
                    noMoreChild = true;
                }
            }
            return true;
        } else {
            return level.size() == (int) Math.pow(2, h);
        }
    }


}
