package chapter3.section1;

import java.util.Iterator;

/**
 * 基于无序链表的符号表的实现
 *
 * @Auther: yusiming
 * @Date: 2018/9/22 16:43
 * @Description:
 */
public class SequentialSearchST<Key, Value> {
    // 头节点
    private Node first = null;
    // 符号表的大小
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

    /**
     * 根据key查询value
     *
     * @auther: yusiming
     * @date: 16:51 2018/9/22
     * @param: [key]
     * @return: Value
     */
    public Value get(Key key) {
        // 当链表为空时，返回null
        if (N == 0) {
            return null;
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
     * 向符号表中插入 key 和 value
     *
     * @auther: yusiming
     * @date: 16:52 2018/9/22
     * @param: [key, value]
     * @return: void
     */
    public void put(Key key, Value value) {
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
        // 未命中,创建新的结点，
        first = new Node(key, value, first);
        N++;
    }

    /**
     * 返回符号表的大小
     *
     * @auther: yusiming
     * @date: 16:58 2018/9/22
     * @param: []
     * @return: int
     */
    public int size() {
        return N;
    }

    /**
     * 返回所有键的集合
     *
     * @auther: yusiming
     * @date: 17:30 2018/9/22
     * @param: []
     * @return: java.lang.Iterable<Key>
     */
    public Iterable<Key> keys() {
        return this::iterator;
    }

    private Iterator<Key> iterator() {
        return new Iterator<Key>() {
            Node x = first;

            @Override
            public boolean hasNext() {
                return x != null;
            }

            @Override
            public Key next() {
                Key key = x.key;
                x = x.next;
                return key;
            }
        };
    }

    /**
     * 根据键删除值
     *
     * @auther: yusiming
     * @date: 17:31 2018/9/22
     * @param: []
     * @return: void
     */
    public void delete(Key key) {
        // 如果删除位置为first，将first = first.next
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
     * @auther: yusiming
     * @date: 17:51 2018/9/22
     * @param: [key]
     * @return: boolean
     */
    public boolean contains(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return true;
            }
        }
        return false;
    }
}
