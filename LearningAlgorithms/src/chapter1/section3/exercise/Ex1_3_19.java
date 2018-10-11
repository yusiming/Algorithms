package chapter1.section3.exercise;

/**
 * 删除链表的尾结点
 *
 * @Auther yusiming
 * @Date 2018/10/11 17:15
 */
public class Ex1_3_19<T> {
    private Node first;

    private class Node {
        T t;
        Node next;
    }

    public Ex1_3_19() {
        first = null;
    }

    /**
     * 添加元素到链表中
     *
     * @param t 被添加的元素
     */
    public void addNode(T t) {
        Node oldFirst = first;
        first = new Node();
        first.t = t;
        first.next = oldFirst;
    }

    /**
     * 删除链表尾结点
     */
    public void deleteLastNode() {
        if (first.next == null) {
            first = null;
            return;
        }
        Node current = first;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
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

    public static void main(String[] args) {
        Ex1_3_19<Integer> test = new Ex1_3_19<>();
        for (int i = 0; i < 3; i++) {
            test.addNode(i);
        }
        System.out.println(test.toString());
        test.deleteLastNode();
        test.deleteLastNode();
        System.out.println(test.toString());
    }
}
