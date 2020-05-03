package leetcode.mixed;

import java.util.LinkedList;

public class ImmutableListNodeImpl implements ImmutableListNode {

    Integer value;
    LinkedList<Integer> list;

    ImmutableListNodeImpl(LinkedList<Integer> list) {
        this.list = list;
        this.value = list.peek();
    }

    @Override
    public void printValue() {
        if (value != null) {
            System.out.println(this.value);
        }
    }

    @Override
    public ImmutableListNode getNext() {
        if (value == null || list.isEmpty()) {
            return null;
        }
        list.pollFirst();
        return new ImmutableListNodeImpl(list);
    }
}
