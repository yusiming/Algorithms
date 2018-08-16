package chapter1.section1;

/**
 * @Auther: yusiming
 * @Date: 2018/8/16 11:03
 * @Description: 测试定容String栈
 */
public class Test {
    public static void main(String[] args) {
        Bag<Integer> bag = new Bag<>();
        for (int i = 0; i < 10; i++) {
            bag.add(i);
        }
        for (int i : bag) {
            System.out.println(i);
        }
    }
}
