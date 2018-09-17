package chapter2.section1;

/**
 * @Auther: yusiming
 * @Date: 2018/8/23 16:44
 * @Description: 希尔排序，使数组每隔h个元素是有序的，
 * h不断较小，最后为1，保证了数组最后一定是有序的，
 */
public class Shell {
    // 任何实现了Comparable 接口的数据类型，都可以使用compareTo 方法进行比较
    public static boolean less(Comparable a ,Comparable b) {
        return a.compareTo(b) < 0;
    }
    // 交换数据
    public static void exch(Comparable[ ] a,int i, int j) {
        Comparable t  =  a[i];
        a[i] = a[j];
        a[j] = t;
    }
    // 将数组a 按升序排序
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while(h < N/3) {
            h = h * 3 + 1;
        }
        while(h >= 1) {
            for(int i=h;i < N;i++) {
                for(int j = i; j >= h && less(a[j],a[j-h]) ;j -=h) {
                    exch(a,j,j-h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Comparable[] a = new Comparable[10];
        for (int i = 0; i < 10; i++) {
            a[i] = (int) (Math.random() * 100);
        }
        sort(a);
        for (int i = 0; i < 10; i++) {
            System.out.println(a[i]);
        }
    }
}
