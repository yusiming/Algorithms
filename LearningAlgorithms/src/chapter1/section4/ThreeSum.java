package chapter1.section4;

import chapter1.section3.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * @Auther: yusiming
 * @Date: 2018/8/18 10:46
 * @Description:
 */
public class ThreeSum {
    public static void main(String[] args) {
        //0.016
        // 0.0
        // 0.094
        // 0.64
        // 4.764
        // 32.214
        double prev = timeTrial(150);
        for (int i = 250; true; i += i) {
            double timeTrial = timeTrial(i);
            StdOut.printf("%6d %7.1f", i, timeTrial);
            StdOut.printf("%5.1f\n",  timeTrial/prev);
        }
    }

    /**
     * @Description: 计算数组中三整数元组的和为0的数量
     * @auther: yusiming
     * @date: 10:49 2018/8/18
     * @param: [a]
     * @return: int
     */
    private static int count(int[] a) {
        int count = 0;
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * @Description: 快速算法，规模越大，显示出的速度比count越快
     * @auther: yusiming
     * @date: 12:52 2018/8/18
     * @param: [a]
     * @return: int
     */
    private static int countFast(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (BinarySearch.rank(-a[i] - a[j], a) > j) {
                    count++;
                }
            }
        }
        return count;
    }

    private static double timeTrial(int N) {
        int[] a = new int[N];
        int Max = 1000000;
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-Max, Max);
        }
        StopWatch stopWatch = new StopWatch();
        int count = count(a);
        return stopWatch.elapsedTime();
    }
}
