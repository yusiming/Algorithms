package chapter1.section3.exercise;

/**
 * 判断链表中是否存在指定元素t
 *
 * @Auther yusiming
 * @Date 2018/10/11 17:27
 */
public class Ex1_3_21<T> {
    /**
     * 首结点
     */
    private Node first;

    private class Node {
        T t;
        Node next;
    }

    /**
     * 添加元素到链表中
     *
     * @param t 元素的值
     */
    public void addNode(T t) {
        Node oldFirst = first;
        first = new Node();
        first.t = t;
        first.next = oldFirst;
    }

    /**
     * 判断链表中是否存在元素t
     *
     * @param node 链表
     * @param t    元素
     * @return 若存在，返回true，若不存在返回false
     */
    public boolean find(Node node, T t) {
        while (node != null) {
            if (node.t == t) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node x = first; x != null; x = x.next) {
            stringBuilder.append(x.t);
            stringBuilder.append(' ');
        }
        return stringBuilder.toString();
    }
}
