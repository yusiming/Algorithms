package chapter4.section3;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * Prim算法的及时实现
 *
 * @Auther: yusiming
 * @Date: 2018/10/6 17:04
 */
public class PrimMST {
    /**
     * 数组的索引表示顶点，值表示距离最小生成树最近的边，即权重最小的边
     */
    private Edge[] edgeTo;
    /**
     * 数组的索引表示顶点，值表示距离最小生成树最近的边的权值
     */
    private double[] distTo;
    /**
     * 数组的索引表示顶点，值代表顶点是否已经在树中了
     */
    private boolean[] marked;
    /**
     * 存放横切边的优先队列,注意每个值有一个索引，即顶点的值
     */
    private IndexMinPQ<Double> pq;

    /**
     * 寻找图G的最小生成树
     *
     * @param G 图
     */
    public PrimMST(EdgeWeightedGraph G, int s) {
        // 初始化各个变量
        int v = G.V();
        edgeTo = new Edge[v];
        distTo = new double[v];
        marked = new boolean[v];
        pq = new IndexMinPQ<>(v);
        // 初始化距离最小生成树最近的边的权值
        for (int i = 0; i < v; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        // 因为顶点已经确定在树中了，所以权值为0.0
        distTo[s] = 0.0;
        // 将顶点0加入优先队列
        pq.insert(s, 0.0);
        // 当优先队列为空时，最小生成树的构造也就完成了
        while (!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }

    /**
     * 将顶点加入树中，并更新与顶点直接相连的顶点edgeTo[]和distTo[]
     *
     * @param G 图
     * @param v 顶点
     */
    private void visit(EdgeWeightedGraph G, int v) {
        // 将v加入最小生成树
        marked[v] = true;
        // 对于每一个v相邻的顶点所形成的边
        for (Edge e : G.adj(v)) {
            // w为相邻的顶点
            int w = e.other(v);
            // 若w已经在树中了，跳出循环
            if (marked[w]) {
                continue;
            }
            // w跟顶点v之间的边的权值小于w原来的距离最小生成树最近的边的权值，更新边和权值
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) {
                    // 若w存在于最小生成树中，更新权值
                    pq.changeKey(w, distTo[w]);
                } else {
                    // 若不存在，加入之
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    /**
     * 返回最小生成树的权重
     *
     * @return 权重
     */
    public double wieght() {
        int sum = 0;
        for (double d : distTo) {
            sum += d;
        }
        return sum;
    }

    /**
     * 返回最小生成树的所有边
     *
     * @return 所有边
     */
    public Iterable<Edge> edges() {
        Queue<Edge> queue = new Queue<>();
        for (Edge edge : edgeTo) {
            queue.enqueue(edge);
        }
        return queue;
    }
}
