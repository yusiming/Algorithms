package chapter4.section3;

import chapter1.section3.Queue;
import chapter1.section5.UF;
import edu.princeton.cs.algs4.MinPQ;

/**
 * Kruskal算法求最小生成树
 *
 * @Auther: yusiming
 * @Date: 2018/10/7 15:14
 */
public class KruskalMST {
    /**
     * 保存最小生成树的边
     */
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        int v = G.V();
        MinPQ<Edge> pq = new MinPQ<>(v);
        // 将所有的加入到优先队列中
        for (Edge edge : G.edges()) {
            pq.insert(edge);
        }
        UF uf = new UF(v);
        while (!pq.isEmpty() && mst.size() < v) {
            Edge edge = pq.delMin();
            int s = edge.either();
            int w = edge.other(v);
            // 忽略失效的边
            if (uf.connected(s, w)) {
                continue;
            }
            // 若这条边
            uf.union(s, w);
            mst.enqueue(edge);
        }
    }
}
