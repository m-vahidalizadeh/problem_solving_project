package leetcode.companies.random;

import java.util.*;

/**
 * Flatten Nested List Iterator
 * Given a nested list of integers, implement an iterator to flatten it.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 * <p>
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,4,6].
 */
public class NestedIterator implements Iterator<Integer> {

    List<NestedInteger> curList;
    int i;
    int size;
    Stack<Integer> indexS;
    Stack<List<NestedInteger>> listS;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.curList = nestedList;
        i = 0;
        size = nestedList == null ? 0 : nestedList.size();
        this.indexS = new Stack<>();
        this.listS = new Stack<>();
    }

    @Override
    public Integer next() {
        return curList.get(i++).getInteger();
    }

    @Override
    public boolean hasNext() {
        if (i == size) {
            if (indexS.isEmpty()) return false;
            else {
                curList = listS.pop();
                i = indexS.pop();
                size = curList.size();
                return hasNext();
            }
        } else {
            NestedInteger curr = curList.get(i++);
            if (curr.isInteger()) {
                i--;
                return true;
            } else {
                indexS.push(i);
                listS.push(curList);
                curList = curr.getList();
                i = 0;
                size = curList.size();
                return hasNext();
            }
        }
    }

    public static void main(String[] args) {
        NestedInteger n1 = new NestedInteger(false, null,
                List.of(new NestedInteger(true, 1, null), new NestedInteger(true, 1, null)));
        NestedInteger n2 = new NestedInteger(true, 2, null);
        NestedInteger n3 = new NestedInteger(false, null,
                List.of(new NestedInteger(true, 1, null), new NestedInteger(true, 1, null)));
        List<NestedInteger> list = List.of(n1, n2, n3);
        NestedIterator it = new NestedIterator(list);
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
//        List<NestedInteger> list=new LinkedList<>();
//        list.add(new NestedInteger(false,null,null));
//        NestedIterator it=new NestedIterator(list);
//        while(it.hasNext()){
//            System.out.print(it.next()+" ");
//        }
    }

}

class NestedInteger {

    boolean isInteger;
    Integer num;
    List<NestedInteger> list;

    public NestedInteger(boolean isInteger, Integer num, List<NestedInteger> list) {
        this.isInteger = isInteger;
        this.num = num;
        this.list = list;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return isInteger;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return num;
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return list;
    }
}
