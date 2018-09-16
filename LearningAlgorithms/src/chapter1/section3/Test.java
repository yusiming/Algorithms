package chapter1.section3;

/**
 * @Auther: yusiming
 * @Date: 2018/8/16 11:03
 * @Description: 测试定容String栈
 */
public class Test {
    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("yu");
        System.out.println(queue.first + "  " + queue.last);
        queue.dequeue();
        System.out.println(queue.first + "  " + queue.last);
    }
}
