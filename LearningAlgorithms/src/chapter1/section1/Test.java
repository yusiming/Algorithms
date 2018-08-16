package chapter1.section1;

import edu.princeton.cs.algs4.In;

/**
 * @Auther: yusiming
 * @Date: 2018/8/16 11:03
 * @Description: 测试定容String栈
 */
public class Test {
    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        In in = new In("test.txt");
        while (!in.isEmpty()) {
            String s = in.readString();
            if (!"-".equals(s)) {
                queue.enqueue(s);
            } else {
                System.out.println(queue.dequeue());
            }
        }
        System.out.println("剩余" + queue.size() + "个元素");
        for (String s : queue) {
            System.out.println(s);
        }
    }
}
