package leetcode.base;

public class ArrayReader {

    private int[] array;
    private int size;

    public ArrayReader(int[] array) {
        this.array = array;
        this.size = array.length;
    }

    public int get(int index) {
        if (index >= size) return 2147483647;
        return array[index];
    }

}
