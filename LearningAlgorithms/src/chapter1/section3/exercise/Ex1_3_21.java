package chapter1.section3.exercise;

/**
 * @Auther: yusiming
 * @Date: 2018/8/17 15:51
 * @Description:
 */
public class Ex1_3_21 {
    public static void main(String[] args) {
        Node node = new Node();
        for (int i = 0; i < 5; i++) {
            Node oldNode = node;
            node = new Node();
            node.t = i;
            node.next = oldNode;
        }
        System.out.println(find(node,5));
    }
    private static class Node<T> {
        T t;
        Node next;
    }

    public static boolean find(Node first, int k) {
        for (Node x = first; x.next != null; x = x.next) {
            if (x.t == Integer.valueOf(k)) {
                return true;
            }
        }
        return false;
    }
}
