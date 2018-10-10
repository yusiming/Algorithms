package chapter1.section1;

/**
 * 欧几里得算法
 *
 * @Auther yusiming
 * @Date 2018/10/10 15:56
 */
public class GCD {
    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        return gcd(q, p % q);
    }
}
