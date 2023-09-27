import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {
    // Space Complexity: O(capacity); Runtime Complexity: O(1)
    private int capacity;
    private Map<Integer, Integer> cache; // key-value LinkedHashMap

    public LRUCache(int capacity) {
        this.capacity = capacity;
        // loadFactor - when the LinkedHashMap will resize itself
        // accessOrder: true - maintain the order of elements based on their access 
        //              most recently accessed to the end; least recently accessed to the front
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if (cache.size() >= capacity) {
            // Remove the oldest entry (LRU) if at capacity
            Iterator<Map.Entry<Integer, Integer>> it = cache.entrySet().iterator();
            it.next(); // first element = front = least recently accessed
            it.remove();
        }
        cache.put(key, value); // maintain order
    }
}
