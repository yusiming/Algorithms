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
    private boolean[] markend;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        int v = G.V();
        markend = new boolean[v];
        mst = new Queue<>();
        pq = new MinPQ<>();
        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge edge = pq.delMin();
            int e = edge.either();
            int w = edge.other(e);
            if (markend[e] && markend[w]) {
                continue;
            }
            mst.enqueue(edge);
            if (!markend[e]) {
                visit(G, e);
            }
            if (!markend[w]) {
                visit(G, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        markend[v] = true;
        for (Edge edge : G.adj(v)) {
            if (!markend[edge.other(v)]) {
                pq.insert(edge);
            }
        }
    }
}

