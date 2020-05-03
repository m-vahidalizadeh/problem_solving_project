package leetcode.medium;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Fizz Buzz Multithreaded
 * <p>
 * Write a program that outputs the string representation of numbers from 1 to n, however:
 * <p>
 * If the number is divisible by 3, output "fizz".
 * If the number is divisible by 5, output "buzz".
 * If the number is divisible by both 3 and 5, output "fizzbuzz".
 * For example, for n = 15, we output: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.
 * <p>
 * Suppose you are given the following code:
 * <p>
 * class FizzBuzz {
 * public FizzBuzz(int n) { ... }               // constructor
 * public void fizz(printFizz) { ... }          // only output "fizz"
 * public void buzz(printBuzz) { ... }          // only output "buzz"
 * public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 * public void number(printNumber) { ... }      // only output the numbers
 * }
 * Implement a multithreaded version of FizzBuzz with four threads. The same instance of FizzBuzz will be passed to four different threads:
 * <p>
 * Thread A will call fizz() to check for divisibility of 3 and outputs fizz.
 * Thread B will call buzz() to check for divisibility of 5 and outputs buzz.
 * Thread C will call fizzbuzz() to check for divisibility of 3 and 5 and outputs fizzbuzz.
 * Thread D will call number() which should only output the numbers.
 */
public class FizzBuzzMultithreaded {

    private int n;
    private int current;
    private Semaphore fizzS = new Semaphore(1);
    private Semaphore buzzS = new Semaphore(1);
    private Semaphore fizzBuzzS = new Semaphore(1);
    private Semaphore numberS = new Semaphore(1);

    public FizzBuzzMultithreaded(int n) throws InterruptedException {
        this.n = n;
        this.current = 1;
        fizzS = new Semaphore(0);
        buzzS = new Semaphore(0);
        fizzBuzzS = new Semaphore(0);
        numberS = new Semaphore(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                fizzS.acquire();
                printFizz.run();
                current++;
                releaseS();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 != 0) {
                buzzS.acquire();
                printBuzz.run();
                current++;
                releaseS();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                fizzBuzzS.acquire();
                printFizzBuzz.run();
                current++;
                releaseS();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 5 != 0 && i % 3 != 0) {
                numberS.acquire();
                printNumber.accept(current);
                current++;
                releaseS();
            }
        }
    }

    // Releases the semaphores in order
    private void releaseS() {
        if (this.current % 15 == 0) {
            fizzBuzzS.release();
        } else if (this.current % 5 == 0) {
            buzzS.release();
        } else if (this.current % 3 == 0) {
            fizzS.release();
        } else {
            numberS.release();
        }
    }

}
