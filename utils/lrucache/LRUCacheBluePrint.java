package lrucache;

interface LRUCacheBluePrint<T> {
    public T get(int key) throws Exception;
    public void put(int key, T value);
}
