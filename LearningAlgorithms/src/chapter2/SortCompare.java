package chapter2;

import chapter1.section4.StopWatch;
import chapter2.section1.Selection;
import chapter2.section1.Shell;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @Auther: yusiming
 * @Date: 2018/8/23 15:39
 * @Description:
 */
public class SortCompare {
    /**
     * @Description: 使用sortMethod参数指定的方法来对数组a进行排序，返回排序所需的时间
     * @auther: yusiming
     * @date: 15:40 2018/8/23
     * @param: [sortMethod, a]
     * @return: double
     */
    private static double time(String sortMethod, Comparable[] a) {
        StopWatch stopWatch = null;
        if (sortMethod.equals("Selection")) {
            stopWatch = new StopWatch();
            Selection.sort(a);
        } else if (sortMethod.equals("Insertion")) {
            stopWatch = new StopWatch();
            Selection.sort(a);
        } else if (sortMethod.equals("Shell")) {
            stopWatch = new StopWatch();
            Shell.sort(a);
        }
        assert stopWatch != null;
        return stopWatch.elapsedTime();
    }

    /**
     * @Description: 使用参数 sortMethod指定的方法，对T个长度为N的随机数组排序，返回所需的时间
     * @auther: yusiming
     * @date: 15:47 2018/8/23
     * @param: [sortMethod, N, T]
     * @return: double
     */
    private static double timeRadomInput(String sortMethod, int N, int T) {
        double totalTime = 0.0;
        Double[] a = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                a[j] = StdRandom.uniform();
            }
            totalTime += time(sortMethod,a);
        }
        return totalTime;
    }

    public static void main(String[] args) {
        double sum = 0.0;
        for (int i = 0; i < 10; i++) {
            double selectionTime = timeRadomInput("Insertion", 1600, 1000);
            double InsertionTIme = timeRadomInput("Shell", 1600, 1000);
            System.out.println(selectionTime/InsertionTIme);
            sum += selectionTime/InsertionTIme;
        }
        System.out.println("平均时间：" + sum/10);
    }
}
