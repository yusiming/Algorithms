package chapter4.section3;

import chapter1.section3.Queue;
import edu.princeton.cs.algs4.MinPQ;

/**
 * 最小生成树的Prim算法的延时实现,延时实现将会在队列中保留无效边
 *
 * @Auther: yusiming
 * @Date: 2018/10/5 17:53
 */
public class LazyPrimMST {
    /**
     * 标记顶点是否已经加入树中
     */
    private boolean[] marked;
    /**
     * 存放最小生成树的所有边
     */
    private Queue<Edge> mst;
    /**
     * 优先队列存放所有的横切边
     */
    private MinPQ<Edge> pq;

    /**
     * 寻找图中的最小生成树
     *
     * @param G 图
     */
    public LazyPrimMST(EdgeWeightedGraph G) {
        // 初始化操作
        int v = G.V();
        marked = new boolean[v];
        mst = new Queue<>();
        pq = new MinPQ<>();
        visit(G, 0);
        while (!pq.isEmpty()) {
            // 删除优先队列中的最小边，实际上是选出最小生成树中的边
            Edge edge = pq.delMin();
            int e = edge.either();
            int w = edge.other(e);
            // 若两个顶点都被访问了，那么这两个顶点都在树中，忽略这条边
            if (marked[e] && marked[w]) {
                continue;
            }
            // 将边加入队列中，
            mst.enqueue(edge);
            if (!marked[e]) {
                visit(G, e);
            }
            if (!marked[w]) {
                visit(G, w);
            }
        }
    }

    /**
     * 标记顶点v，并将顶点v相邻的所有未被标记的顶点加入优先队列中
     *
     * @param G 图
     * @param v 顶点
     */
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge edge : G.adj(v)) {
            if (!marked[edge.other(v)]) {
                pq.insert(edge);
            }
        }
    }

    /**
     * 返回最小生成树的权重
     *
     * @return 权重
     */
    public double weight() {
        int sum = 0;
        for (Edge edge : mst) {
            sum += edge.weight();
        }
        return sum;
    }

    /**
     * 返回最小生成树的所有的边的集合
     *
     * @return 边的集合
     */
    public Iterable<Edge> edges() {
        return mst;
    }
}

