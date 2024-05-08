package leetcode.companies.facebook;

import java.util.HashMap;
import java.util.Map;

public class CanBeEqualReverse {

    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Integer> tf = new HashMap<>();
        Map<Integer, Integer> af = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            tf.put(target[i], tf.getOrDefault(target[i], 0) + 1);
            af.put(arr[i], af.getOrDefault(arr[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : tf.entrySet()) {
            int aFreq = af.getOrDefault(entry.getKey(), 0);
            if (entry.getValue() != aFreq) return false;
        }
        return true;
    }

}
