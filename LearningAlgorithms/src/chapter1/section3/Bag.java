package chapter1.section3;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 使用链表实现背包数据结构
 *
 * @Auther yusiming
 * @Date 2018/8/16 13:50
 */
public class Bag<T> implements Iterable<T> {

    /**
     * 背包的大小
     */
    private int N;
    /**
     * 顶
     */
    private Node top;

    private class Node {
        T t;
        Node next;
    }

    public Bag() {
        N = 0;
        top = null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 添加元素到背包中
     *
     * @param t 被添加的元素
     */
    public void add(T t) {
        // 与栈的push方法相似
        Node oldTop = top;
        top = new Node();
        top.t = t;
        top.next = oldTop;
    }

    /**
     * 返回背包中所有元素的字符串表示
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
        return new Iterator<T>() {
            Node current = top;

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

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        In in = new In("test.txt");
        while (!in.isEmpty()) {
            String s = in.readString();
            bag.add(s);
        }
        System.out.println(bag.toString());
    }
}
