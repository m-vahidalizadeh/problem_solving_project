package leetcode.medium;

import leetcode.base.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MaxAncestorDiffTest {

    MaxAncestorDiff m;
    TreeNode rootOfTestTree;

    @BeforeEach
    public void initialize() {
        m = new MaxAncestorDiff();
        rootOfTestTree = getRootOfTestTree();
    }

    @Test
    public void testMaxAncestorDiff() {
        Assertions.assertEquals(7, m.maxAncestorDiff(rootOfTestTree));
    }

    private TreeNode getRootOfTestTree() {
        TreeNode node8 = new TreeNode(8);
        TreeNode node3 = new TreeNode(3);
        TreeNode node10 = new TreeNode(10);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node14 = new TreeNode(14);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node13 = new TreeNode(13);
        node8.left = node3;
        node8.right = node10;
        node3.left = node1;
        node3.right = node6;
        node6.left = node4;
        node6.right = node7;
        node10.right = node14;
        node14.left = node13;
        return node8;
    }

}
