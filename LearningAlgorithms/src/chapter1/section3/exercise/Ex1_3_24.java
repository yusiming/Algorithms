package chapter1.section3.exercise;

/**
 * @Auther: yusiming
 * @Date: 2018/8/17 16:24
 * @Description:
 */
public class Ex1_3_24 {
    public static void main(String[] args) {
        Node node = new Node();
        node.t = 1;
        Node node1 = new Node();
        node1.t = 2;
        node.next = node1;
        removeAfter(node);
        System.out.println(node.next);
    }
    private static class Node<T> {
        Node next;
        T t;
    }

    private static void removeAfter(Node first) {
        if (first != null && first.next != null) {
            first.next = null;
        }
    }
}
