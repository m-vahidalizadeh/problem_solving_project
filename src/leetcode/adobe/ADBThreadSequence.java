package leetcode.adobe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ADBThreadSequence {

    /*
Example 1:
Input: [1,2,3]
Output: "firstsecondthird"
Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(),
thread B calls second(), and thread C calls third(). "firstsecondthird" is the correct output.

Example 2:
Input: [1,3,2]
Output: "firstsecondthird"
Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second().
"firstsecondthird" is the correct output.
     */

    AtomicBoolean firstRun;
    CountDownLatch firstCompleteLatch;
    AtomicBoolean secondRun;
    CountDownLatch secondCompleteLatch;

    public static void main(String[] args) throws InterruptedException {
        ADBThreadSequence adbThreadSequence = new ADBThreadSequence();
        ExecutorService service = Executors.newScheduledThreadPool(3);
        Runnable runnable1 = () -> System.out.println("first");
        Runnable runnable2 = () -> System.out.println("second");
        Runnable runnable3 = () -> System.out.println("third");
        service.submit(runnable1);
        service.submit(runnable2);
        service.submit(runnable3);
        adbThreadSequence.third(runnable3);
        adbThreadSequence.second(runnable2);
        adbThreadSequence.first(runnable1);
    }

    public ADBThreadSequence() {
        firstRun = new AtomicBoolean(false);
        firstCompleteLatch = new CountDownLatch(1);
        secondRun = new AtomicBoolean(false);
        secondCompleteLatch = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstRun.getAndSet(true);
        firstCompleteLatch.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        if (!firstRun.get()) {
            firstCompleteLatch.await();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondRun.getAndSet(true);
        secondCompleteLatch.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        if (!secondRun.get()) {
            secondCompleteLatch.await();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

}
