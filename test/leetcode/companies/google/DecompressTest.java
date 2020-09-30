package leetcode.companies.google;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DecompressTest {

    Decompress d;

    @BeforeEach
    public void initialize() {
        d = new Decompress();
    }

    @Test
    public void testFindMinFibonacciNumbers() {
        Assertions.assertEquals("abcabcabcababababc", d.decompress("3[abc]4[ab]c"));
        Assertions.assertEquals("aaaaaaaaaa", d.decompress("10[a]"));
        Assertions.assertEquals("aaabaaab", d.decompress("2[3[a]b]"));
    }

}
