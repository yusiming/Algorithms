package chapter1.section1.exercise;

import chapter1.section1.Stack;
import edu.princeton.cs.algs4.In;

/**
 * @Auther: yusiming
 * @Date: 2018/8/16 18:13
 * @Description: was best times of the was the ti
 */
public class Ex1_3_2 {
    public static void main(String[] args) {
        In in = new In("test.txt");
        Stack<String> stack = new Stack<>();
        while (!in.isEmpty()) {
            String string = in.readString();
            if (!"-".equals(string)) {
                stack.push(string);
            } else {
                System.out.print(stack.pop() + " ");
            }
        }
    }
}
