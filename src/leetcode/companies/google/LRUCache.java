package leetcode.companies.google;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU Cache
 * Medium
 * <p>
 * 6489
 * <p>
 * 275
 * <p>
 * Add to List
 * <p>
 * Share
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * <p>
 * The cache is initialized with a positive capacity.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * Example:
 * <p>
 * LRUCache cache = new LRUCache( 2  capacity  );
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * 1.	LinkedHashMap(): This is used to construct a default LinkedHashMap constructor.
 * 2.	LinkedHashMap(int capacity): It is used to initialize a particular LinkedHashMap with a specified capacity.
 * 3.	LinkedHashMap(Map map): It is used to initialize a particular LinkedHashMap with the elements of the specified map.
 * 4.	LinkedHashMap(int capacity, float fillRatio): It is used to initialize both the capacity and fill ratio for a LinkedHashMap. A fillRatio also called as loadFactor is a metric that determines when to increase the size of the LinkedHashMap automatically. By default, this value is 0.75 which means that the size of the map is increased when the map is 75% full.
 * 5.	LinkedHashMap(int capacity, float fillRatio, boolean Order): This constructor is also used to initialize both the capacity and fill ratio for a LinkedHashMap along with whether to follow the insertion order or not.
 * o	True is passed for last access order.
 * o	False is passed for insertion order.
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {

    int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
