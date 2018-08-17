package chapter1.section1;

/**
 * @Auther: yusiming
 * @Date: 2018/8/16 10:40
 * @Description: 使用数组实现定容String栈
 */
public class FixedCapacityStackOfStrings {
    // 使用一个数组来保存元素
    private String[] a;
    // N为数组中元素的个数，即栈的长度
    private int N = 0;

    public FixedCapacityStackOfStrings() {
    }

    // 初始化数组的大小
    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    // 判断栈是否已满
    public boolean isFull() {
        return N == a.length;
    }

    // 返回栈的大小
    public int size() {
        return N;
    }

    // 向栈中添加元素
    public void push(String item) {
        // 将item添加到数组a末尾，同时将栈的长度加一
        a[N++] = item;
    }

    // 弹出栈中的元素
    public String pop() {
        // 弹出元素,同时将栈的大小减一
        return a[--N];
    }
}
