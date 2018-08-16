package chapter1.section1;

import edu.princeton.cs.algs4.In;

/**
 * @Auther: yusiming
 * @Date: 2018/8/16 11:03
 * @Description: 测试定容String栈
 */
public class Test {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        In in = new In("test.txt");
        while (!in.isEmpty()) {
            String s = in.readString();
            if (!"-".equals(s)) {
                stack.push(s);
            } else {
                System.out.println(stack.pop());
            }
        }
        System.out.println("剩余" + stack.size() + "个元素");
        for (String s : stack) {
            System.out.println(s);
        }
    }
}
