package chapter4.section3;

/**
 * 带权重的边的数据结构
 *
 * @Auther: yusiming
 * @Date: 2018/10/5 16:38
 */
public class Edge implements Comparable<Edge> {
    /**
     * 边的一个顶点
     */
    private int v;
    /**
     * 另一个给顶点
     */
    private int w;
    /**
     * 边的权值
     */
    private double weight;

    /**
     * 构造一个新的边
     *
     * @param v      顶点
     * @param w      顶点
     * @param weight 权值
     */
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 返回边的权值
     *
     * @return 权值
     */
    public double weight() {
        return weight;
    }

    /**
     * 返回边的一个顶点
     *
     * @return 顶点
     */
    public int either() {
        return v;
    }

    /**
     * 返回vertex相对的那个顶点
     *
     * @param vertex 顶点
     * @return 相对的顶点
     */
    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return w;
        } else {
            throw new IllegalArgumentException("顶点不在这条边中");
        }
    }

    /**
     * 比较两个边的权值
     *
     * @param that 另一条边
     * @return 比较结果
     */
    @Override
    public int compareTo(Edge that) {
        if (this.weight > that.weight) {
            return 1;
        } else if (this.weight < that.weight) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 边的字符串表示
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
