package leetcode.base;

import leetcode.medium.NestedListWeightSumII;

import java.util.List;

public class NestedInteger {
    Integer integer;
    List<NestedListWeightSumII.NestedInteger> list;

    public NestedInteger(Integer integer) {
        this.integer = integer;
    }

    public NestedInteger(List<NestedListWeightSumII.NestedInteger> list) {
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

    public void add(NestedListWeightSumII.NestedInteger ni) {
        this.list.add(ni);
    }

    public List<NestedListWeightSumII.NestedInteger> getList() {
        return list;
    }
}
