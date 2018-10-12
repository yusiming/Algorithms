package chapter1.section3.exercise;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 使用环形链表实现队列
 *
 * @Auther yusiming
 * @Date 2018/8/17 17:38
 */
public class Queue<T> implements Iterable<T> {
    /**
     * 只使用一个尾结点来实现链表
     */
    private Node last;
    /**
     * 链表的长度
     */
    private int N;

    private class Node {
        T t;
        Node next;
    }

    /**
     * 初始化一个队列
     */
    public Queue() {
        this.last = null;
        this.N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 向队列的尾部添加元素
     *
     * @param t 被添加的元素
     */
    public void enqueue(T t) {
        /*
         * 注意：由于只使用了一个last来表示队列的尾部，所以我们将last.next表示为队列的头部，
         * 可以获得同样的效果，只是在插入第一个元素时，需要注意，last即是尾部也是头部，即last.next = next;
         */
        Node oldLast = last;
        last = new Node();
        last.t = t;
        if (isEmpty()) {
            last.next = last;
        } else {
            last.next = oldLast.next;
            oldLast.next = last;
        }
        N++;
    }

    /**
     * 删除并返回队列头部的元素
     *
     * @return 头部删除的元素
     * @throws NoSuchElementException 当队列为空时，调用此方法，抛出异常
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("队列为空了，不能删除元素");
        }
        T t = last.t;
        if (size() == 1) {
            // 当只剩一个元素时，last就是头部，直接删除last
            last = null;
            N--;
            return t;
        } else {
            last.next = last.next.next;
            N--;
            return t;
        }
    }

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
            Node current = last;

            @Override
            public boolean hasNext() {
                return current != null;

            }

            @Override
            public T next() {
                T t = current.next.t;
                if (current.next != current) {
                    current.next = current.next.next;
                } else {
                    current = null;
                }
                return t;
            }
        };
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        In in = new In("test.txt");
        String s;
        while (!in.isEmpty()) {
            s = in.readString();
            if (!s.equals("-")) {
                queue.enqueue(s);
            } else {
                queue.dequeue();
            }
        }
        System.out.println("队列中还剩" + queue.size() + "个元素");
        System.out.println(queue.toString());
        queue.dequeue();
        System.out.println("队列中还剩" + queue.size() + "个元素");
        queue.dequeue();
        System.out.println("队列中还剩" + queue.size() + "个元素");
    }
}
