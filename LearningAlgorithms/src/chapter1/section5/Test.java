package chapter1.section5;

import chapter1.section4.StopWatch;
import edu.princeton.cs.algs4.In;

/**
 * @Auther: yusiming
 * @Date: 2018/8/20 16:10
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        In in = new In("largeUF.txt");
        int N = in.readInt();
        WeightedQuickUnionUF UF = new WeightedQuickUnionUF(N);
        StopWatch stopWatch = new StopWatch();
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (UF.connected(p, q)) {
                continue;
            }
            UF.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(stopWatch.elapsedTime());
        System.out.println(UF.count() + " 个分量");
    }
}
