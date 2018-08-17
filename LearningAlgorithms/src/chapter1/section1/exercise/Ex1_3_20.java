package chapter1.section1.exercise;

/**
 * @Auther: yusiming
 * @Date: 2018/8/17 13:46
 * @Description:
 */
public class Ex1_3_20 {
    public static void main(String[] args) {
        Node node = new Node();
        for (int i = 0; i < 4; i++) {
            Node oldNode = node;
            node = new Node();
            node.t = i;
            node.next = oldNode;
        }
        for (Node x = node; x.next != null; x = x.next) {
            System.out.print(x.t + " ");
        }
        System.out.println("");
        delete(1,node);
        for (Node x = node; x.next != null; x = x.next) {
            System.out.print(x.t + " ");
        }
    }

    private static class Node<T> {
        T t;
        Node next;
    }

    /**
     * @Description: 删除链表的第k个结点, 如果存在的话，要删除第k个元素，需要遍历到第k-1个元素
     * @auther: yusiming
     * @date: 13:48 2018/8/17
     * @param: [k]
     * @return: void
     */
    public static void delete(int k, Node first) {
        if (k == 1) {
            first.next = null;
        } else {
            // 记录遍历的位置
            int index = 1;
            for (Node current = first; current.next != null; current = current.next) {
                // 若index + 1 = k ，此时的current.next = 第k个结点
                if (index + 1 == k) {
                    current.next = current.next.next;
                }
                index++;
            }
        }
    }
}

