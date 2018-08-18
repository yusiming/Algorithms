package chapter1.section3.exercise;

/**
 * @Auther: yusiming
 * @Date: 2018/8/17 13:12
 * @Description:
 */
public class Ex1_3_19<T> {
    private static class Node<T> {
        T t;
        Node next;
    }

    /**
     * @Description: 删除链表的最后一个结点
     * @auther: yusiming
     * @date: 13:45 2018/8/17
     * @param: [first]
     * @return: void
     */
    public static void deleteLastNode(Node first) {
        while (first.next.next != null) {
            first = first.next;
        }
        // first.next.next = null 时，first.next 为最后一个结点，将first.next置为null即可删除最后一个结点
        first.next = null;
    }
}
