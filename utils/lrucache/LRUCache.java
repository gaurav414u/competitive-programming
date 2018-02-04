package lrucache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class LRUCache {
    private int capacity;
    HashMap<Integer, Integer> masterMap;
    TreeMap<Long, Integer> sortedKeys;
    HashMap<Integer, Long> keyHelper;
    int k = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.sortedKeys = new TreeMap(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return Long.compare(o2, o1);
            }
        });
        this.keyHelper = new HashMap<>();
        this.masterMap = new HashMap<>(capacity);
    }

    public int get(int key) {
        if (!this.masterMap.containsKey(key)) {
            return -1;
        }
        // Invalidate this key
        this.invalidate(key);

        return masterMap.get(key);
    }

    private void invalidate(int key) {
        // Remove the key from the sortedKeys
        this.sortedKeys.remove(this.keyHelper.get(key));
        this.keyHelper.remove(key);

        long idx = k++;
        // Re-add
        sortedKeys.put(idx, key);
        keyHelper.put(key, idx);
    }

    private void remove(int key) {
        this.masterMap.remove(key);
        this.sortedKeys.remove(this.keyHelper.get(key));
        this.keyHelper.remove(key);
    }

    @Override
    public String toString() {
         return this.masterMap.toString() + "\n"
                 + this.sortedKeys.toString() + "\n"
                 + this.keyHelper.toString();
    }

    public void put(int key, int value) {
        if(masterMap.containsKey(key)) {
            // If already there, invalidate and update the masterMap
            this.invalidate(key);
            this.masterMap.put(key, value);
            return;
        }

        if (this.masterMap.size() == this.capacity) {
            // Get the key for the leastRecentlyUsed entry
            Map.Entry<Long, Integer> lastEntry =  sortedKeys.lastEntry();
            // And remove it from the cache
            this.remove(lastEntry.getValue());
        }
        this.masterMap.put(key, value);
        // Insert it at the end of the sortedKeys
        long otherKey = k++;
        sortedKeys.put(otherKey, key);
        keyHelper.put(key, otherKey);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        assert cache.get(2) == -1 : "get(2) should return -1";
        cache.put(2, 6);
        System.out.println(String.format("show %s",  cache));
        assert cache.get(1) == -1 : "get(1) should return -1";
        cache.put(1, 5);
        System.out.println(String.format("show %s",  cache));
        cache.put(1, 2);
        System.out.println(String.format("show %s",  cache));
        assert cache.get(1) == 2 : "get(1) should return 2";
        assert cache.get(2) == 6 : "get(1) should return 6";
    }
}

