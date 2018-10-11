package chapter1.section3.exercise;

/**
 * 删除链表指定位置的元素
 *
 * @Auther yusiming
 * @Date 2018/10/11 16:42
 */
public class Ex1_3_20<T> {
    /**
     * 首结点
     */
    private Node first;

    private class Node {
        T t;
        Node next;
    }

    public Ex1_3_20() {
        first = null;
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
     * 删除指定位置的元素
     *
     * @param k 要删除的元素的位置
     * @throws IllegalArgumentException 当参数小于1时，抛出异常
     */
    public void delete(int k) {
        if (k < 1) {
            throw new IllegalArgumentException("参数必须大于等于1");
        }
        // 若要删除的元素是第一个元素，那么直接使用first
        if (k == 1) {
            first = first.next;
            return;
        }
        // 若要删除的元素不是第一个，那么不能使用first
        Node current = first;
        for (int i = 1; i < k - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
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
        Ex1_3_20<Integer> test = new Ex1_3_20<>();
        for (int i = 0; i < 10; i++) {
            test.addNode(i);
        }
        System.out.println(test.toString());
        test.delete(3);
        System.out.println(test.toString());
    }
}
