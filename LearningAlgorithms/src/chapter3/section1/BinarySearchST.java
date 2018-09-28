package chapter3.section1;

import chapter1.section3.Queue;

/**
 * 基于二分查找的有序数组实现符号表
 * <p>
 * 使用两个平行的数组，一个存储key，一个存储value,
 * <p>
 * keys[i]对应的值values[i]
 * <p>
 * 键在数组中是有序的，使用实现了Comparable接口的数据类型当作key，
 * 使用接口中的compareTo方法来比较两个key之间的关系
 *
 * @Auther: yusiming
 * @Date: 2018/9/22 18:49
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int DEFAULT_SIZE = 2;
    /**
     * 存储键的数组
     */
    private Key[] keys;
    /**
     * 存储值的数组
     */
    private Value[] values;
    /**
     * 符号表的大小
     */
    private int N;

    public BinarySearchST(int size) {
        keys = (Key[]) new Comparable[size];
        values = (Value[]) new Object[size];
    }

    public BinarySearchST() {
        // 使用默认值初始化数组
        this(DEFAULT_SIZE);
    }

    /**
     * 返回表中键值对的个数
     *
     * @return 大小
     */
    public int size() {
        return N;
    }

    /**
     * 判断表是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 调整表的大小
     *
     * @param size 数组大小
     */
    private void resize(int size) {
        if (size > N) {
            Key[] tempKeys = (Key[]) new Comparable[size];
            Value[] tempValus = (Value[]) new Object[size];
            for (int i = 0; i < N; i++) {
                tempKeys[i] = keys[i];
                tempValus[i] = values[i];
            }
            keys = tempKeys;
            values = tempValus;
        }
    }

    /**
     * 根据指定的键，返回值
     *
     * @param key 键
     * @return Value值
     * @throws IllegalArgumentException 键为空时，抛出异常
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("键不能为空");
        }
        if (isEmpty()) {
            return null;
        }
        // 返回小于key 的key的个数,
        int rank = rank(key, 0, N - 1);
        // 若 a[key] 存在的话,rank 的值也就是key在数组中的位置了，
        if (rank < N && key.compareTo(keys[rank]) == 0) {
            return values[rank];
        }
        return null;
    }

    /**
     * 向符号表中添加键值对
     *
     * @param key   键
     * @param value 值
     * @throws IllegalArgumentException 键位空时，抛出异常
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("键不能为空");
        }
        // 若值为null，直接删除键值对
        if (value == null) {
            delete(key);
        }
        int rank = rank(key, 0, N - 1);
        // 若key在数组中存在，更新value的值
        if (rank < N && key.compareTo(keys[rank]) == 0) {
            values[rank] = value;
            return;
            // 下面的代码不应该再执行了
        }
        if (N == size()) {
            resize(N * 2);
        }
        // 若执行到这里证明key不存在于keys中，我们要将rank之后的数据整体往后挪动一位，
        for (int i = N; i > rank; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        // rank的位置已经为空了，直接插入数据即可
        keys[rank] = key;
        values[rank] = value;
        N++;
    }

    /**
     * 该方法返回小于指定key 的 key的个数,基于递归实现，
     *
     * @param key 键
     * @return 小于key的键的个数
     */
    public int rank(Key key, int start, int end) {
        /*
         * rank方法的实现原理：
         * 若key存在于数组keys中，直接返回数组的下标，下标就代表了有多少个键小于指定的key
         * 若key不存在于数组keys中，我们来看最后两次递归时的比较，即只剩两个元素 start和end
         * 此时 middle = start + (end - start) / 2 = start
         *
         * 如果key < keys[middle] ,end = middle - 1 < start,此时我们结束循环，返回start的值，
         * start的值就代表了有多少个键小于key
         *
         * 如果key > keys[middle], start = middle + 1 = end ,循环尚未结束，
         * 再次更新middle = start + (end - start) / 2 = start = end,再次比较
         * 若key < keys[middle] ,end = middle -1 < start ,此时我们结束循环，返回start的值，
         * start的值就代表了有多少个键小于key
         *
         * 若key > keys[middle] ,start = start + 1 > end ,循环也结束，此时返回start的值，也是合理的，因为
         * key > keys[middle]
         *
         * 总结起来，只要在递归时 start > end ，我们就返回start的值，总能保证返回的值是正确的
         */
        if (start > end) {
            return start;
        }
        int middle = start + (end - start) / 2;
        int comp = key.compareTo(keys[middle]);
        if (comp < 0) {
            return rank(key, start, middle - 1);
        } else if (comp > 0) {
            return rank(key, middle + 1, end);
        } else {
            return middle;
        }
    }

    // 返回最小的键
    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return keys[0];
    }

    public void deleteMin() {
        if (isEmpty()) {
            return;
        }
        delete(min());
    }

    // 返回最大的键
    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return keys[N - 1];
    }

    public void deleteMax() {
        if (isEmpty()) {
            return;
        }
        delete(max());
    }

    // 排名为k的键
    public Key select(int k) {
        if (k < 0 && k >= N) {
            throw new IllegalArgumentException("参数k的值必须大于零，小于表的大小");
        }
        return keys[k];
    }

    // 大于等于key的最小键
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("键不能为空");
        }
        int rank = rank(key, 0, N);
        // 若rank大于N证明所有的键都比他小，
        if (rank == N) {
            return null;
        }
        return keys[rank];
    }

    // 小于等于key的最大键，
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("键不能为空");
        }
        int rank = rank(key, 0, N);
        // 若rank为0，证明这个key是最小的，keys中没有键比他小
        if (rank == 0) {
            return null;
        }
        // 若key再数组中存在，返回对应的值
        if (rank < N && keys[rank] == key) {
            return keys[rank];
        } else {
            // 若不存在，返回rank左边的值
            return keys[rank - 1];
        }
    }

    /**
     * 判断key对于的键值对是否存在于表中
     *
     * @param key 键
     * @return 是否存在于表中
     * @throws IllegalArgumentException key不能为空
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("键不能为空");
        }
        return get(key) != null;
    }

    /**
     * 根据指定的key(如果存在的话)删除键值对
     *
     * @param key 键
     * @throws IllegalArgumentException 键不能为空
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("键不能为空");
        }
        if (isEmpty()) {
            return;
        }
        int rank = rank(key, 0, N);
        if (rank < N && key.compareTo(keys[rank]) == 0) {
            // rank之后的每一个元素向前移动一个位置
            for (int i = rank; i < N; i++) {
                keys[i] = keys[i + 1];
                values[i] = values[i + 1];
            }
        }
        N--;
        // 防止对象游离
        keys[N] = null;
        values[N] = null;
        // 若键值对的数量，不到表的四分之一，调整表的大小为二分之一
        if (N > 0 && N == keys.length / 4) {
            resize(keys.length / 2);
        }
    }

    /**
     * 返回所有键的集合
     *
     * @return 键的集合
     */
    public Iterable keys() {
        return keys(min(), max());
    }

    private Iterable keys(Key start, Key end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("参数不能为kong");
        }
        Queue<Key> queue = new Queue<>();
        if (start.compareTo(end) > 0) {
            return queue;
        }
        for (int i = rank(start, 0, N - 1); i < rank(end, 0, N - 1); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(end)) {
            queue.enqueue(keys[rank(end, 0, N - 1)]);
        }
        return queue;
    }
}

