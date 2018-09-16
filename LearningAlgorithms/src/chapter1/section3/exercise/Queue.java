package chapter1.section3.exercise;

import java.util.Iterator;

/**
 * @Auther: yusiming
 * @Date: 2018/8/17 17:38
 * @Description: 使用环形链表实现Queue
 */
public class Queue<T> implements Iterable<T> {
    // 只使用last来实现进出队列
    private Node last = null;
    private int N = 0;

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

    public void enqueue(T t) {
        //  保存last
        Node oldLast = last;
        // 创建新的last结点
        last = new Node();
        last.t = t;
        // 若队列为空时，oldLast为null，新的last不为null，新的last应该指向队列的开头，也就是自己
        if (N == 0) {
            last.next = last;
        } else {
            // 若队列不为空，last应该指向队列的开头，也就是oldLast的next，
            last.next = oldLast.next;
            // oldLast应该指向last，
            oldLast.next = last;
        }
        N++;
    }

    public T dequeue() {
        // 获取队列开头结点的值，
        T t = last.next.t;
        // 若队列中只有一个元素，那么也就是last，将last置为空
        if (N == 1) {
            last = null;
        } else {
            // 若队列中不止有一个结点，那么删除头节点，
            last.next = last.next.next;
        }
        N--;
        return t;
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
}
