package chapter3.section3;

import chapter3.section1.SequentialSearchST;

/**
 * 基于拉链法的散列表的实现
 *
 * @Auther: yusiming
 * @Date: 2018/9/27 17:25
 */
public class SeparateChainingHashST<Key, Value> {

    private static final int DEFAULT_SIZE = 4;
    /**
     * 散列表中键值对的个数
     */
    private int n = 0;
    /**
     * 散列表的大小
     */
    private int m = 0;
    /**
     * 数组中每一个位置存放一个链表
     */
    private SequentialSearchST<Key, Value>[] st;

    /**
     * 构造方法，创一个指定大小的散列表
     *
     * @param m 要创建的散列表的大小
     */
    @SuppressWarnings("unchecked")
    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    public SeparateChainingHashST() {
        this(DEFAULT_SIZE);
    }

    /**
     * 向散列表中插入键值对
     *
     * @param key   需要插入的键
     * @param value 需要插入的值
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
        if (value == null) {
            delete(key);
            return;
        }
        // 若每一个链表平均包含大于等于10个结点，调整散列表大小
        if (n >= m * 10) {
            resize(m * 2);
        }
        if (!contains(key)) {
            n++;
        }
        st[hash(key)].put(key, value);
    }

    /**
     * 根据key查找相应的value
     *
     * @param key key
     * @return key对应的value
     * @throws IllegalArgumentException 若key为null，抛出异常
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
        int hash = hash(key);
        return st[hash].get(key);
    }

    /**
     * 返回散列表中键值对的数量
     *
     * @return 键值对的数量
     */
    public int size() {
        return n;
    }

    /**
     * 判断key是否在散列表中
     *
     * @param key 需要判断的key
     * @return 一个boolean值
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * 判断散列表是否为空
     *
     * @return boolean值
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @param key 要删除的键值对的key
     * @throws IllegalArgumentException 若key为null，抛出异常
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
        int hash = hash(key);
        if (contains(key)) {
            n--;
        }
        st[hash].delete(key);
        // 若每个链表平均包含小于等于2个链接，调整数组大小为原来的一半
        if (m > DEFAULT_SIZE && n <= m * 2) {
            resize(m / 2);
        }
    }

    /**
     * 将key转换为数组索引， 范围是0到 m-1之间
     *
     * @param key 需要转换的key
     * @return 转换后的数组索引
     */
    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /**
     * 调整散列表的大小
     *
     * @param size 调整之后后散列表的大小
     */
    private void resize(int size) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(size);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.n = temp.n;
        this.m = temp.m;
        this.st = temp.st;
    }

}
