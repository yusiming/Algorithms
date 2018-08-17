package chapter1.section1.exercise;

/**
 * @Auther: yusiming
 * @Date: 2018/8/17 16:49
 * @Description:
 */
public class Ex1_3_27 {
    public static void main(String[] args) {
        Node node = new Node();
        for (int i = 0; i < 10; i++) {
            Node oldNode = node;
            node = new Node();
            node.t = i;
            node.next = oldNode;
        }
        System.out.println(max(node));
    }

    private static class Node<T> {
        Node next;
        T t;
    }

    private static int max(Node first) {
        int max = 0;
        for (Node x = first; x.next != null; x = x.next) {
            if ((int) x.t > max) {
                max = (Integer) x.t;
            }
        }
        return max;
    }

    private static int max1(Node first) {
        if (first == null) {
            System.out.println("链表为空");
            return 0;
        } else if (first.next == null) {
            return (int) first.t;
        } else {
            if ((int)first.t > (int)first.next.t) {
                return max1(first);
            } else return max1(first.next);
        }
    }
}
