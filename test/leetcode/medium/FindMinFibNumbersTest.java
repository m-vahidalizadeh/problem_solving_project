package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FindMinFibNumbersTest {

    FindMinFibNumbers f;

    @BeforeEach
    public void initialize() {
        f = new FindMinFibNumbers();
    }

    @Test
    public void testFindMinFibonacciNumbers() {
        Assertions.assertEquals(2, f.findMinFibonacciNumbers(7));
        Assertions.assertEquals(2, f.findMinFibonacciNumbers(10));
        Assertions.assertEquals(3, f.findMinFibonacciNumbers(19));
    }

}
