package leetcode.companies.google;

import java.util.*;

/**
 * Insert Delete GetRandom O(1)
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * <p>
 * Example:
 * <p>
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 * <p>
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * <p>
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 * <p>
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * <p>
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 * <p>
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * <p>
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 * <p>
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 */
public class RandomizedSet {

    Map<Integer, Integer> map;
    List<Integer> list;
    int size;
    Random random;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        size = 0;
        random = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, size);
        list.add(size, val);
        size++;
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int sizeMinusOne = size - 1;
        int last = list.get(sizeMinusOne);
        int index = map.get(val);
        list.set(index, last);
        map.put(last, index);
        list.remove(sizeMinusOne);
        map.remove(val);
        size = sizeMinusOne;
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(random.nextInt(size));
    }

}
