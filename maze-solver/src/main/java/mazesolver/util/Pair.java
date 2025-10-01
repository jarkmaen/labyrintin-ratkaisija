package mazesolver.util;

/**
 * Basic key-value data structure
 * 
 * @param <K> key type
 * @param <V> value type
 */
public class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
