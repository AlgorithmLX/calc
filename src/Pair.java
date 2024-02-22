import java.util.Objects;

public class Pair<K, V> {
    private final K k;
    private V v;

    private Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getKey() {
        return this.k;
    }

    public V getValue() {
        return this.v;
    }

    public V setValue(V newValue) {
        V old = this.v;
        this.v = newValue;
        return old;
    }

    public final String toString() {
        return this.k + "=" + this.v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair<?, ?> pair)) return false;
        return Objects.equals(k, pair.getKey()) && Objects.equals(v, pair.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.k) ^ Objects.hashCode(this.v);
    }

    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<>(key, value);
    }
}
