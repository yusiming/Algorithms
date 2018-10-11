package chapter1.section1;

import chapter1.section3.Stack;

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

    /**
     * 返回整数n的二进制表示
     *
     * @param n 整数n
     * @return 整数n的二进制表示
     */
    public static String toBinaryString(int n) {
        Stack<Integer> stack = new Stack<>();
        boolean isNegative = false;
        if (n < 0) {
            n = -n;
            isNegative = true;
        }
        /*
         * Java中有关余数的定义是：(a / b) * b + a % b = a;
         * 所以 a % b = a - (a / b) * b;
         * (-14 % 3) = -14 - (-14 / 3) * 3 = -2
         * (14 % -3) = 14 - (14 / -3) * -3 = 2;
         */
        while (n > 0) {
            stack.push(n % 2);
            n = n / 2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (isNegative) {
            stringBuilder.append('-');
        }
        for (int i : stack) {
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.toBinaryString(-50));
    }
}
