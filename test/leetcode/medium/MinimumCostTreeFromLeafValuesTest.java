package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinimumCostTreeFromLeafValuesTest {

    @Test
    public void testMctFromLeafValues() {
        MinimumCostTreeFromLeafValues m = new MinimumCostTreeFromLeafValues();
        int[] arr = {6, 2, 4};
        Assertions.assertEquals(32, m.mctFromLeafValues(arr));
    }

}
