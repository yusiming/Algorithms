package chapter1.section1;

/**
 * @Auther yusiming
 * @Date 2018/10/9 17:31
 */
public class TestString {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = s1;
        s1 = "world";
        // world ,s1的引用改变了
        System.out.println(s1);
        // hello，s2的引用没有改变
        System.out.println(s2);
    }
}
