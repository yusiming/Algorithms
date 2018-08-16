package chapter1.section1;

import java.util.Iterator;

/**
 * @Auther: yusiming
 * @Date: 2018/8/16 12:30
 * @Description: 使用链表实现栈
 */
public class Stack<T> implements Iterable<T> {
    // 栈的长度
    private int N = 0;
    // 栈顶
    private Node top = null;

    // 结点类，一种数据结构，链表
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

    // 添加元素到栈顶
    public void push(T t) {
        // 保存栈顶结点的引用
        Node oldTop = top;
        // 创建新栈顶
        top = new Node();
        top.t = t;
        // 将新的栈顶赋值给原来的栈顶
        top.next = oldTop;
        // 每添加一个元素，栈的大小加一
        N++;
    }

    // 删除栈顶的元素
    public T pop() {
        T t = top.t;
        // 将栈顶的next赋值给栈顶
        top = top.next;
        N--;
        return t;
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
                // 于pop方法类似
                T t = current.t;
                current = current.next;
                return t;
            }
        };
    }
}
