package chapter3.section3;

/**
 * 基于线性探测法的散列表的实现
 *
 * @Auther: yusiming
 * @Date: 2018/9/27 19:49
 */
public class LinearProbingHashST<Key, Value> {
    private static final int DEFAULT_SIZE = 4;
    private int n = 0;
    private int m = 0;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST(int m) {
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
        this.m = m;
    }

    public LinearProbingHashST() {
        this(DEFAULT_SIZE);
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
        if (value == null) {
            delete(key);
            return;
        }
        if (n >= m / 2) {
            resize(m * 2);
        }
        int i;
        for (i = hash(key); keys[i] != null; i++) {
            if (keys[i].equals(key)) {
                values[i] = value;
            }
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key不能空");
        }
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i] == key) {
                return values[i];
            }
        }
        return null;
    }

    private void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % m;
        while (!(keys[i] == null)) {
            Key oldKey = keys[i];
            Value oldValue = values[i];
            keys[i] = null;
            values[i] = null;
            n--;
            put(oldKey, oldValue);
            i = (i + 1) % m;
        }
        n--;
        if (n > 0 && n < m / 8) {
            resize(m / 2);
        }
    }

    private void resize(int size) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(size);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        m = temp.m;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
        return get(key) != null;
    }

}
