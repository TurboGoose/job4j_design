package references.cache;

public interface Cache <K, V> {
    V load(K key, V value);

    V get(K key);
}
