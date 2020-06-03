package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FindAllDuplicatesInAnArrayTest {

    FindAllDuplicatesInAnArray f;

    @BeforeEach
    public void initialize() {
        f = new FindAllDuplicatesInAnArray();
    }

    @Test
    public void testFindDuplicates() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        Assertions.assertEquals(List.of(2, 3), f.findDuplicates(nums));
    }

}
