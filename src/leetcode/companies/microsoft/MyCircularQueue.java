package leetcode.companies.microsoft;

/**
 * Design Circular Queue
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 * <p>
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 * <p>
 * Your implementation should support following operations:
 * <p>
 * MyCircularQueue(k): Constructor, set the size of the queue to be k.
 * Front: Get the front item from the queue. If the queue is empty, return -1.
 * Rear: Get the last item from the queue. If the queue is empty, return -1.
 * enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
 * deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
 * isEmpty(): Checks whether the circular queue is empty or not.
 * isFull(): Checks whether the circular queue is full or not.
 * <p>
 * Example:
 * <p>
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
 * circularQueue.enQueue(1);  // return true
 * circularQueue.enQueue(2);  // return true
 * circularQueue.enQueue(3);  // return true
 * circularQueue.enQueue(4);  // return false, the queue is full
 * circularQueue.Rear();  // return 3
 * circularQueue.isFull();  // return true
 * circularQueue.deQueue();  // return true
 * circularQueue.enQueue(4);  // return true
 * circularQueue.Rear();  // return 4
 * <p>
 * Note:
 * <p>
 * All values will be in the range of [0, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in Queue library.
 */
public class MyCircularQueue {

    Integer[] queue;
    int front;
    int rear;
    int size;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue(int k) {
        queue = new Integer[k];
        front = 0;
        rear = 0;
        size = k;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (queue[rear] != null) return false;
        queue[rear] = value;
        rear = indexPlusOne(rear);
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (front == rear && queue[front] == null) return false;
        queue[front] = null;
        front = indexPlusOne(front);
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (queue[front] == null) return -1;
        return queue[front];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        int lastElementIndex = indexMinusOne(rear);
        if (queue[lastElementIndex] == null) return -1;
        return queue[lastElementIndex];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return rear == front && queue[front] == null;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return front == rear && queue[rear] != null;
    }

    private int indexPlusOne(int x) {
        return (x + 1) % (size);
    }

    private int indexMinusOne(int x) {
        return x == 0 ? size - 1 : x - 1;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
        System.out.println(circularQueue.enQueue(1));  // return true
        System.out.println(circularQueue.enQueue(2));  // return true
        System.out.println(circularQueue.enQueue(3));  // return true
        System.out.println(circularQueue.enQueue(4));  // return false, the queue is full
        System.out.println(circularQueue.Rear());  // return 3
        System.out.println(circularQueue.isFull());  // return true
        System.out.println(circularQueue.deQueue());  // return true
        System.out.println(circularQueue.enQueue(4));  // return true
        System.out.println(circularQueue.Rear());  // return 4
    }

}
