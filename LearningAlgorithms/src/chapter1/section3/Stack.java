package chapter1.section3;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 使用链表实现下压栈
 *
 * @Auther yusiming
 * @Date 2018/8/16 12:30
 */
public class Stack<T> implements Iterable<T> {
    /**
     * 栈中元素的个数
     */
    private int N;
    /**
     * 栈顶
     */
    private Node top;

    /**
     * 链表数据结构
     */
    private class Node {
        T t;
        Node next;
    }

    /**
     * 初始化栈
     */
    public Stack() {
        this.N = 0;
        top = null;
    }

    /**
     * 检测栈是否为空
     *
     * @return 当栈为空时，返回true，否则返回false
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 返回栈中元素的个数
     *
     * @return 元素的个数
     */
    public int size() {
        return N;
    }

    /**
     * 向栈中添加元素，每次都是添加到栈顶
     *
     * @param t 被添加的元素
     */
    public void push(T t) {
        Node oldTop = top;
        top = new Node();
        top.t = t;
        top.next = oldTop;
        N++;
    }

    /**
     * 删除并返回栈顶的元素
     *
     * @return 栈顶的元素
     * @throws NoSuchElementException 当栈为空时，调用这个方法抛出此异常
     */
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        T t = top.t;
        top = top.next;
        N--;
        return t;
    }

    /**
     * 返回栈顶的元素，但不删除此元素
     *
     * @return 元素
     * @throws NoSuchElementException 当栈为空时，抛出此异常
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return top.t;
    }

    /**
     * 返回栈中的元素的字符串表示
     *
     * @return 元素的字符串表示
     */
    @Override
    public String toString() {
        // 使用StringBuilder来连接字符串，加快效率
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
            private Node current = top;

            @Override
            public boolean hasNext() {
                // 当栈顶不为空时，则还有元素
                return current != null;
            }

            @Override
            public T next() {
                if (isEmpty()) {
                    throw new NoSuchElementException();
                }
                // 于pop方法类似
                T t = current.t;
                current = current.next;
                return t;
            }
        };
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        In in = new In("test.txt");
        while (!in.isEmpty()) {
            String s = in.readString();
            if (!s.equals("-")) {
                stack.push(s);
            } else {
                stack.pop();
            }
        }
        System.out.println("栈中还有" + stack.size() + "个字符串");
        System.out.println(stack.toString());
    }
}
