package chapter4.section4;

/**
 * 加权有向边的数据结构的实现
 *
 * @Auther: yusiming
 * @Date: 2018/10/7 15:56
 */
public class DirectedEdge {
    /**
     * 指出这条边的顶点
     */
    private final int v;
    /**
     * 这条边指向的顶点
     */
    private final int w;
    /**
     * 边的权重
     */
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int form() {
        return v;
    }

    public int to() {
        return w;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }
}
