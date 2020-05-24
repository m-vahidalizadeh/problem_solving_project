package leetcode.medium;

import leetcode.base.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FlipEquivalentBTTest {

    @Test
    public void testFlipEquiv() {
        // Build tree 1
        TreeNode node1_1 = new TreeNode(1);
        TreeNode node1_2 = new TreeNode(2);
        TreeNode node1_3 = new TreeNode(3);
        TreeNode node1_4 = new TreeNode(4);
        TreeNode node1_5 = new TreeNode(5);
        TreeNode node1_6 = new TreeNode(6);
        TreeNode node1_7 = new TreeNode(7);
        TreeNode node1_8 = new TreeNode(8);
        node1_1.left = node1_2;
        node1_1.right = node1_3;
        node1_2.left = node1_4;
        node1_2.right = node1_5;
        node1_5.left = node1_7;
        node1_5.right = node1_8;
        node1_3.left = node1_6;
        // Build tree 2
        TreeNode node2_1 = new TreeNode(1);
        TreeNode node2_2 = new TreeNode(2);
        TreeNode node2_3 = new TreeNode(3);
        TreeNode node2_4 = new TreeNode(4);
        TreeNode node2_5 = new TreeNode(5);
        TreeNode node2_6 = new TreeNode(6);
        TreeNode node2_7 = new TreeNode(7);
        TreeNode node2_8 = new TreeNode(8);
        node2_1.left = node2_3;
        node2_1.right = node2_2;
        node2_2.left = node2_4;
        node2_2.right = node2_5;
        node2_5.left = node2_8;
        node2_5.right = node2_7;
        node2_3.right = node2_6;
        FlipEquivalentBT f = new FlipEquivalentBT();
        Assertions.assertTrue(f.flipEquiv(node1_1, node2_1));
    }
}
