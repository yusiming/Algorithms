package chapter1.section4;

import chapter1.section3.BinarySearch;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

/**
 * @Auther: yusiming
 * @Date: 2018/8/18 12:14
 * @Description:
 */
public class TwoSum {
    public static void main(String[] args) {
        In in = new In("32Kints.txt");
        int[] ints = in.readAllInts();
        StopWatch stopWatch = new StopWatch();
        int count = countFast(ints);
        System.out.println(count + "  " + stopWatch.elapsedTime());
    }

    private static int countFast(int[] a) {
        int N = a.length;
        int count = 0;
        Arrays.sort(a);
        for (int i = 0; i < N; i++) {
            if (BinarySearch.rank(-a[i], a) > i) {
                count++;
            }
        }
        return count;
    }

    private static int count(int[] a) {
        int count = 0;
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (a[i] + a[j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}

