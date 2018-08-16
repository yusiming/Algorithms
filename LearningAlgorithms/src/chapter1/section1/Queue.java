package chapter1.section1;

import java.util.Iterator;

/**
 * @Auther: yusiming
 * @Date: 2018/8/16 13:01
 * @Description:
 */
public class Queue<T> implements Iterable<T> {
    // 队列的长度
    private int N = 0;
    // 头部结点
    private Node first = null;
    // 尾部结点
    private Node last = null;

    private class Node {
        T t;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    // 向队列尾部添加元素
    public void enqueue(T t) {
        // 保存尾部结点last
        Node oldLast = last;
        // 创建新的尾部结点
        last = new Node();
        last.t = t;
        last.next = null;
        // 若队列为空，那么尾部结点和头部结点必须指向同一个元素，
        if (N == 0) {
            first = last;
        } else {
            // 若队列不为空，将原来的尾部节点即oldLast指向新的last
            oldLast.next = last;
        }
        N++;
    }

    // 删除队列头部的元素
    public T dequeue() {
        T t = first.t;
        // 若队列中只剩一个元素，first与last相等，first.next已经为空
        first = first.next;
        // 若只剩一个元素时，删除该元素，但last仍然指向最后一个元素，所以要将last置为null
        if (N == 0) {
            last = null;
        }
        N--;
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T t = current.t;
                current = current.next;
                return t;
            }
        };
    }

}
