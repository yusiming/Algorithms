package chapter1.section3;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 使用链表实现队列数据结构
 *
 * @Auther yusiming
 * @Date 2018/8/16 13:01
 */
public class Queue<T> implements Iterable<T> {
    /**
     * 队列的长度
     */
    private int N;
    /**
     * 队列的头部结点
     */
    Node first;
    /**
     * 队列的尾部结点
     */
    Node last;

    /**
     * 链表数据结构
     */
    private class Node {
        T t;
        Node next;
    }

    /**
     * 初始化一个链表
     */
    public Queue() {
        N = 0;
        first = null;
        last = null;
    }

    /**
     * 判断队列是否为空
     *
     * @return 若队列为空，返回true，否则返回false
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 返回队列的长度或者说队列中元素的个数
     *
     * @return 队列中元素的个数
     */
    public int size() {
        return N;
    }

    /**
     * 向队列的尾部插入数据
     *
     * @param t 要插入的数据
     */
    public void enqueue(T t) {
        Node oldLast = last;
        last = new Node();
        last.t = t;
        last.next = null;
        // 如果队列为空，那么插入的结点即是头节点也是尾结点
        if (!isEmpty()) {
            oldLast.next = last;
        } else {
            first = last;
        }
        N++;
    }

    /**
     * 删除并返回最早添加到队列的元素
     *
     * @return 删除的头部元素
     * @throws NoSuchElementException 当队列为空时，调用这个方法抛出异常
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        T t = first.t;
        first = first.next;
        N--;
        // 如果删除元素之后，对列空了，那么尾部结点也要为null
        if (isEmpty()) {
            last = null;
        }
        return t;
    }

    /**
     * 返回最早添加到队列的元素，但是不删除此元素
     *
     * @return 最早添加到队列的元素
     * @throws NoSuchElementException 当队列为空时，调用此方法抛出异常
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return first.t;
    }

    /**
     * 返回队列中的所有元素的字符串表示
     *
     * @return 元素的字符串表示
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (T t : this) {
            stringBuilder.append(t);
            stringBuilder.append(' ');
        }
        return stringBuilder.toString();
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
                if (isEmpty()) {
                    throw new NoSuchElementException();
                }
                T t = current.t;
                current = current.next;
                return t;
            }
        };
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        In in = new In("test.txt");
        while (!in.isEmpty()) {
            String s = in.readString();
            if (!s.equals("-")) {
                queue.enqueue(s);
            } else {
                queue.dequeue();
            }
        }
        System.out.println("队列中还剩" + queue.size() + "个元素");
        System.out.println(queue.toString());
    }
}
