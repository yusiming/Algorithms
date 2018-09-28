package chapter3.section1;

import chapter1.section3.Queue;

/**
 * 基于无序链表的符号表的实现
 *
 * @Auther: yusiming
 * @Date: 2018/9/22 16:43
 */
public class SequentialSearchST<Key, Value> {
    /**
     * 头节点
     */
    private Node first = null;
    /**
     * 符号表的大小
     */
    private int N = 0;

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public SequentialSearchST() {
    }

    /**
     * 根据key查询value,key不能为空
     *
     * @param key 要查询的键
     * @return 返回键对应的值
     * @throws IllegalArgumentException 键为空时，抛异常
     */
    public Value get(Key key) {
        // 当链表为空时，返回null
        if (N == 0) {
            return null;
        }
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                // 命中
                return x.value;
            }
        }
        // 未命中
        return null;
    }

    /**
     * 向符号表中插入键值对,键不能为空
     *
     * @param key   要插入的键
     * @param value 要插入的值
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
        // 防御性代码，若插入的值为null，我们直接删除key value
        if (value == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                // 命中
                x.value = value;
                N++;
            }
        }
        // 未命中,在表头插入新的结点
        first = new Node(key, value, first);
        N++;
    }

    /**
     * 返回符号表的大小
     *
     * @return 符号表的大小
     */
    public int size() {
        return N;
    }

    /**
     * 根据键删除值,键不能为空
     *
     * @param key 键
     * @throws IllegalArgumentException 键为空时，抛出异常
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
        /*
         * 若key等于first结点的key，两种情况
         * 1.链表只有一个结点
         * 2.链表有多个结点
         */
        if (key.equals(first.key)) {
            first = first.next;
            N--;
            return;
        }
        // 遍历结点，找到要删除的对象
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.next.key)) {
                x.next = x.next.next;
                N--;
            }
        }
    }

    /**
     * 判断指定的键是否在符号表中
     *
     * @param key 键
     * @return 是否存在于表中
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回所有键的集合
     *
     * @return 返回所有键的集合
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }
}
