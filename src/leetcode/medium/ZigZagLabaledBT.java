package leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Path In Zigzag Labelled Binary Tree
 * In an infinite binary tree where every node has two children, the nodes are labelled in row order.
 * In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even
 * numbered rows (second, fourth, sixth,...), the labelling is right to left.
 * Given the label of a node in this tree, return the labels in the path from the root of the tree to the node
 * with that label.
 * Example 1:
 * Input: label = 14
 * Output: [1,3,4,14]
 * Example 2:
 * Input: label = 26
 * Output: [1,2,6,10,26]
 */
public class ZigZagLabaledBT {

    public static void main(String[] args) {
        ZigZagLabaledBT zigZagLabaledBT = new ZigZagLabaledBT();
        List<Integer> result = zigZagLabaledBT.pathInZigZagTree(14);
    }

    public List<Integer> pathInZigZagTree(int label) {
        if (label == 1) {
            return List.of(1);
        }
        Result result = buildTree(label);
        int index = result.index;
        int[] tree = result.tree;
        List<Integer> res = new ArrayList<>();
        IntStream.range(0, result.level).forEach(res::add);
        res.set((result.level - 1), tree[index]);
        index = (index - 1) / 2;
        for (int i = result.level - 2; i >= 0; i--) {
            res.set(i, tree[index]);
            index = (index - 1) / 2;
        }
        return res;
    }

    private Result buildTree(int label) {
        int counter = 1;
        int level = 1;
        int[] tree = new int[10_000_000];
        int base = 0;
        boolean isEven = false;
        while (true) {
            int k = (int) Math.pow(2, level - 1);
            if (isEven) {
                for (int i = (base + k - 1); i >= base; i--) {
                    tree[i] = counter;
                    if (counter == label) {
                        return new Result(tree, i, level);
                    }
                    counter++;
                }
            } else {
                for (int i = base; i <= (base + k - 1); i++) {
                    tree[i] = counter;
                    if (counter == label) {
                        return new Result(tree, i, level);
                    }
                    counter++;
                }
            }
            base += k;
            level++;
            isEven = !isEven;
        }
    }

    static class Result {
        int[] tree;
        int index;
        int level;

        Result(int[] tree, int index, int level) {
            this.tree = tree;
            this.index = index;
            this.level = level;
        }
    }

}
