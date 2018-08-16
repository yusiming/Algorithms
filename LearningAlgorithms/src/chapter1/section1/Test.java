package chapter1.section1;

import edu.princeton.cs.algs4.In;

/**
 * @Auther: yusiming
 * @Date: 2018/8/16 11:03
 * @Description: 测试定容String栈
 */
public class Test {
    public static void main(String[] args) {
        /*test1();
        test2();*/
        //test3();
        test4();
    }

    private static void test1() {
        FixedCapcityStackOfString stackOfString = new FixedCapcityStackOfString(10);
        In in = new In("test.txt");
        while (!in.isEmpty()) {
            String s = in.readString();
            if (!"-".equals(s)) {
                stackOfString.push(s);
            } else {
                System.out.println(stackOfString.pop());
            }
        }
        System.out.println("剩余" + stackOfString.size() + "个元素");
    }

    private static void test2() {
        FixedCapcityStack<String> stack = new FixedCapcityStack<>(10);
        In in = new In("test.txt");
        while (!in.isEmpty()) {
            String s = in.readString();
            if (!"-".equals(s)) {
                stack.push(s);
            } else {
                System.out.print(stack.pop() + " ");
            }
        }
        System.out.println("剩余" + stack.size() + "个元素");
    }

    private static void test3() {
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>(1);
        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }
    }

    private static void test4() {
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>(10);
        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }
        for (int i : stack) {
            System.out.println(i);
        }
    }
}
