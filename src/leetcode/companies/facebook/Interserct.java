package leetcode.companies.facebook;

import java.util.*;

public class Interserct {

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> s1 = new HashMap<>(), s2 = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int num : nums1) s1.put(num, s1.getOrDefault(num, 0) + 1);
        for (int num : nums2) s2.put(num, s2.getOrDefault(num, 0) + 1);
        for (Map.Entry<Integer, Integer> entry : s1.entrySet()) {
            if (s2.containsKey(entry.getKey())) {
                for (int i = 0; i < Math.min(entry.getValue(), s2.get(entry.getKey())); i++)
                    res.add(entry.getKey());
            }
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

}
