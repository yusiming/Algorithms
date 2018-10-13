package chapter1.section3.exercise;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 双向链表
 *
 * @Auther yusiming
 * @Date 2018/10/12 22:51
 */
public class DoubleLinkedList<T> implements Iterable<T> {
    /**
     * 链表的头结点
     */
    private DoubleNode first;
    /**
     * 链表的尾结点
     */
    private DoubleNode last;
    /**
     * 链表的长度
     */
    private int N;

    private class DoubleNode {
        DoubleNode before;
        DoubleNode after;
        T t;
    }

    /**
     * 初始化一个链表
     */
    public DoubleLinkedList() {
        this.first = null;
        this.last = null;
        this.N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 在链表头部插入结点
     *
     * @param t 要插入的元素
     */
    public void addNodeAtFirst(T t) {
        DoubleNode oldFirst = first;
        first = new DoubleNode();
        first.t = t;
        // 头部结点的before为null
        first.before = null;
        // 如果链表不空，插入新的头结点
        if (!isEmpty()) {
            first.after = oldFirst;
            oldFirst.before = first;
        } else {
            // 如果链表为空，链表的头部也是尾部
            first.after = null;
            last = first;
        }
        N++;
    }

    /**
     * 在链表尾部插入结点
     *
     * @param t 要插入的元素
     */
    public void addNodeAtLast(T t) {
        DoubleNode oldLast = last;
        last = new DoubleNode();
        last.t = t;
        // 尾部结点的after为null
        last.after = null;
        // 如果链表非空，插入新的尾结点
        if (!isEmpty()) {
            oldLast.after = last;
            last.before = oldLast;
        } else {
            // 如果链表为空，尾部结点也是头部结点
            last.before = null;
            first = last;
        }
        N++;
    }

    /**
     * 从链表的头部删除结点
     *
     * @return 返回删除的结点中的元素的值
     * @throws NoSuchElementException 当链表为空时，调用此方法，抛出异常
     */
    public T deleteFromFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("链表为空，不能删除元素");
        }
        T t = first.t;
        // 如果只剩一个元素，那么first已经为null了，再次调用first.before将会导致空指针异常
        first = first.after;
        // N--必须要放在isEmpty()检测之前
        N--;
        if (!isEmpty()) {
            // 如果链表不为空，才能设置before为null，否则会导致空指针异常
            first.before = null;
        } else {
            // 如果链表为空，last也为null
            last = null;
        }
        return t;
    }

    /**
     * 从链表的尾部删除结点
     *
     * @return 返回删除的结点中的元素的值
     * @throws NoSuchElementException 当链表为空时，调用此方法，抛出异常
     */
    public T deleteFromLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("链表为空，不能删除元素");
        }
        T t = last.t;
        last = last.before;
        N--;
        // 只有当链表不为空时，才能设置last.after为null。否则空指针异常
        if (!isEmpty()) {
            last.after = null;
        } else {
            // 如果从尾部删除结点之后，链表为空，那么将first也置为null
            first = null;
        }
        return t;
    }

    /**
     * 在指定位置的结点之前插入新结点
     *
     * @param n 需要插入的新的结点在链表中的哪个位置的前面
     * @param t 需要插入的新的结点中元素的值
     * @throws IllegalArgumentException 当要插入的位置小于1，或者要插入的位置大于链表的长度时，抛出异常
     */
    public void insertBefore(int n, T t) {
        if (n < 1 || n > N) {
            throw new IllegalArgumentException("参数不合法");
        }
        // 当位置为1时，其实就是在表头插入新的结点
        if (n == 1) {
            addNodeAtFirst(t);
            // N++，注意这个如果加上N++，会导致错误，因为addNodeAtFirst(t)中，已经N++了
            return;
        }
        // 当位置不为1时，需要遍历链表，找到结点，但是不能使用first来遍历
        DoubleNode x = first;
        for (int i = 1; i < n; i++) {
            x = x.after;
        }
        // 创建新的结点
        DoubleNode doubleNode = new DoubleNode();
        doubleNode.t = t;
        doubleNode.after = x;
        doubleNode.before = x.before;
        x.before.after = doubleNode;
        x.before = doubleNode;
        N++;
    }

    /**
     * 在指定位置的结点之后插入新结点
     *
     * @param n 需要插入的新的结点在链表中的哪个位置的后面
     * @param t 需要插入的新的结点中元素的值
     * @throws IllegalArgumentException 当要插入的位置小于1，或者要插入的位置大于链表的长度时，抛出异常
     */
    public void insertAfter(int n, T t) {
        if (n < 1 || n > N) {
            throw new IllegalArgumentException("参数不合法");
        }
        // 当位置为N时，其实就是在表尾插入新的结点
        if (n == N) {
            addNodeAtLast(t);
            // N++; 注意这个如果加上N++，会导致错误，因为addNodeAtLast(t)中，已经N++了
            return;
        }
        // 当位置不为1时，需要遍历链表，找到结点，但是不能使用first来遍历
        DoubleNode x = first;
        for (int i = 1; i < n; i++) {
            x = x.after;
        }
        // 创建新的结点
        DoubleNode doubleNode = new DoubleNode();
        doubleNode.t = t;
        doubleNode.before = x;
        doubleNode.after = x.after;
        x.after.before = doubleNode;
        x.after = doubleNode;
        N++;
    }

    /**
     * 在指定的位置删除结点
     *
     * @param n 需要删除的结点的位置
     * @throws IllegalArgumentException 当要删除的位置小于1，或者要删除的位置大于链表的长度时，抛出异常
     */
    public void delete(int n) {
        if (n < 1 || n > N) {
            throw new IllegalArgumentException("参数不合法");
        }
        // 当要删除的位置为1时，其实就是从表头删除结点
        if (n == 1) {
            deleteFromFirst();
            // 这里要直接返回
            return;
        }
        // 当要删除的位置为N时，其实就是从表尾删除结点
        if (n == N) {
            deleteFromLast();
            // 这里要直接返回
            return;
        }
        // 遍历链表，找到要删除的结点
        DoubleNode x = first;
        for (int i = 1; i < n; i++) {
            x = x.after;
        }
        x.before.after = x.after;
        x.after.before = x.before;
        x = null;
        N--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            DoubleNode current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (isEmpty()) {
                    throw new NoSuchElementException();
                }
                T t = current.t;
                current = current.after;
                return t;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (T t : this) {
            stringBuilder.append(t);
            stringBuilder.append(' ');
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addNodeAtFirst("A");
        list.addNodeAtFirst("B");
        list.insertBefore(1, "E");
        list.insertBefore(2, "C");
        list.insertAfter(3, "T");
        list.insertAfter(5, "G");
        list.deleteFromFirst();
        list.deleteFromLast();
        list.delete(3);
        list.delete(1);
        list.delete(1);
        list.delete(1);
        /*
         * 链表的更新过程：
         * A
         * B A
         * E B A
         * E C B A
         * E C B T A
         * E C B T A G
         * C B T A G
         * C B T A
         * C B A
         * B A
         * A
         */
    }
}
