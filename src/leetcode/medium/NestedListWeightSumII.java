package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Nested List Weight Sum II
 * Medium
 * <p>
 * 573
 * <p>
 * 162
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],2,[1,1]]
 * Output: 8
 * Explanation: Four 1's at depth 1, one 2 at depth 2.
 * Example 2:
 * <p>
 * Input: [1,[4,[6]]]
 * Output: 17
 * Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
 */
public class NestedListWeightSumII {

    public static class NestedInteger {

        Integer integer;
        List<NestedInteger> list;

        public NestedInteger(Integer integer) {
            this.integer = integer;
        }

        public NestedInteger(List<NestedInteger> list) {
            this.list = list;
        }

        public boolean isInteger() {
            return integer != null;
        }

        public Integer getInteger() {
            return integer;
        }

        public void setInteger(int value) {
            this.integer = value;
        }

        public void add(NestedInteger ni) {
            this.list.add(ni);
        }

        public List<NestedInteger> getList() {
            return list;
        }
    }

    public static class Node {
        int val;
        int depth;

        public Node(int val, int depth) {
            this.val = val;
            this.depth = depth;
        }
    }

    public static class Result {
        int maxDepth;
        List<Node> nodes;

        public Result() {
            this.maxDepth = 0;
            this.nodes = new ArrayList<>();
        }
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        Result result = new Result();
        getNodes(nestedList, 0, 0, result);
        int sum = 0;
        for (Node node : result.nodes) {
            int maxDepth = result.maxDepth + 1;
            sum += node.val * (maxDepth - node.depth);
        }
        return sum;
    }

    private void getNodes(List<NestedInteger> nestedList, int currIndex, int currDepth, Result result) {
        if (currIndex == nestedList.size()) return;
        NestedInteger nestedInteger = nestedList.get(currIndex);
        if (nestedInteger.isInteger()) {
            result.maxDepth = Math.max(result.maxDepth, currDepth);
            result.nodes.add(new Node(nestedInteger.getInteger(), currDepth));
            getNodes(nestedList, currIndex + 1, currDepth, result);
        } else {
            getNodes(nestedInteger.getList(), 0, currDepth + 1, result);
            getNodes(nestedList, currIndex + 1, currDepth, result);
        }
    }

    public static void main(String[] args) {
        NestedListWeightSumII n = new NestedListWeightSumII();
        List<NestedInteger> list1 = new ArrayList<>();
        list1.add(new NestedInteger(1));
        list1.add(new NestedInteger(1));
        List<NestedInteger> list2 = new ArrayList<>();
        list2.add(new NestedInteger(1));
        list2.add(new NestedInteger(1));
        List<NestedInteger> nestedList = new ArrayList<>();
        nestedList.add(new NestedInteger(list1));
        nestedList.add(new NestedInteger(2));
        nestedList.add(new NestedInteger(list2));
        n.depthSumInverse(nestedList);
    }

}
