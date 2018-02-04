package lrucache;

import java.util.HashMap;

class LRUCacheOptimized {
    private int capacity;
    DoublyLinkedListImpl dll;
    HashMap<Integer, DoublyLinkedListImpl.Node> map;

    public LRUCacheOptimized(int capacity) {
        this.capacity = capacity;
        dll = new DoublyLinkedListImpl();
        map = new HashMap<>();
    }

    public void print(String message) {
        System.out.println(message);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        //invalidate the map.get(key) node in linked list
        this.invalidate(key);

        return map.get(key).element;
    }

    private void invalidate(int key) {
        DoublyLinkedListImpl.Node node = map.get(key);
        dll.remove(node);
        dll.iterateForward();
        dll.addFirst(node);
    }

    public void printMap() {
        System.out.println(map.toString());
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            //invalidate the map.get(key) node in linked list
            this.invalidate(key);
            //update the value of map.get(key)
            map.get(key).element = value;
            return;
        }
        if (map.size() == capacity) {
            //remove the last value
            DoublyLinkedListImpl.Node node = dll.removeLast();
            //need the key for the last value to clean up the map
            map.remove(node.key);
        }

        DoublyLinkedListImpl.Node node  = dll.addFirst(value, key);
        map.put(key, node);
    }

    public static void main(String[] args) {
        LRUCacheOptimized cache = new LRUCacheOptimized(2);
        cache.get(2);
        cache.put(2, 6);
        cache.get(1);
        cache.put(1, 5);
        cache.put(1, 2);

        cache.get(1);

        cache.print("  ------    ");
        cache.get(2);
        cache.dll.iterateForward();
        cache.printMap();
    }
}
