package chapter1.section4;

/**
 * @Auther: yusiming
 * @Date: 2018/8/18 11:08
 * @Description:
 */
public class StopWatch {
    private final long start;

    public StopWatch() {
        start = System.currentTimeMillis();
    }

    //返回自创建StopWatch对象到调用该方法的时间
    public double elapsedTime() {
        return (System.currentTimeMillis() - start) / 1000.0;
    }
}
