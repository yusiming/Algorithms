package chapter1.section1;

/**
 * 对字符的反转
 *
 * @Auther yusiming
 * @Date 2018/10/9 17:33
 */
public class ReverseString {
    /**
     * 反转字符串
     * <p>
     * 如传入一个参数"ABCD"将返回一个新的字符串"DCBA"
     *
     * @param s 被反转的字符串
     * @return 返回一个与s字符序列完全相反的字符串
     */
    public static String reverse(String s) {
        int N = s.length();
        if (N <= 1) return s;
        String a = s.substring(0, N / 2);
        String b = s.substring(N / 2, N);
        return reverse(b) + reverse(a);
    }
}
