package chapter1.section1;

/**
 * 对字符的反转
 *
 * @Auther yusiming
 * @Date 2018/10/9 17:33
 */
public class StringUtils {
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

    /**
     * 检测字符串是否为另一个字符串的换回变位
     *
     * @param a 字符串
     * @param b 字符串
     * @return 若a是的b的换回变位返回true，否则返回false
     */
    public static boolean isCirlularRotationFun1(String a, String b) {
        if (a == null || b == null) {
            return false;
        }
        if (a.length() != b.length()) {
            return false;
        }
        int N = a.length();
        for (int i = 1; i < N; i++) {
            if ((a.substring(i, N) + a.substring(0, i)).equals(b)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 简便方法判断两个字符串是否为回环字符串，
     * 将字符串分为AB两个部分，若Ab + AB 包含了另一字符串，那么这个字符串就是它的环回变位
     *
     * @param a 字符串a
     * @param b 字符串b
     * @return 若a是的b的换回变位返回true，否则返回false
     */
    public static boolean isCirlularRotationFun2(String a, String b) {
        return (a.length() == b.length() && b.concat(b).contains(a));
    }

}
