package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MinFlipsToMakeAOrBEqualsCTest {

    MinFlipsToMakeAOrBEqualsC m;

    @BeforeEach
    public void main() {
        m = new MinFlipsToMakeAOrBEqualsC();
    }

    @Test
    public void testMinFlips() {
        Assertions.assertEquals(3, m.minFlips(2, 6, 5));
        Assertions.assertEquals(1, m.minFlips(4, 2, 7));
        Assertions.assertEquals(0, m.minFlips(1, 2, 3));
    }

}
