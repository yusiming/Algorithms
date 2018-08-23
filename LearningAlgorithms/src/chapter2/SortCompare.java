package chapter2;

import chapter1.section4.StopWatch;
import chapter2.section1.Selection;
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
    public static double time(String sortMethod, Comparable[] a) {
        StopWatch stopWatch = null;
        if (sortMethod.equals("Selection")) {
            stopWatch = new StopWatch();
            Selection.sort(a);
        } else if (sortMethod.equals("Insertion")) {
            stopWatch = new StopWatch();
            Selection.sort(a);
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
    public static double timeRadomInput(String sortMethod, int N, int T) {
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
        for (int i = 0; i < 10; i++) {
            double selectionTime = timeRadomInput("Selection", 1000, 1000);
            double InsertionTIme = timeRadomInput("Insertion", 1000, 1000);
            System.out.println(selectionTime/InsertionTIme);
        }
        //1.9980256663376086
        // 0.9575871819038642
        // 0.9863680623174296
        // 1.0009881422924902
        // 0.9557788944723618
        // 0.9989816700610998
        // 1.0706751054852321
        // 0.9851632047477745
        // 1.0274111675126902
        // 1.014807502467917
    }
}
