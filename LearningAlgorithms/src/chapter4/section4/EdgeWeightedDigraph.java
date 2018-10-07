package chapter4.section4;

import chapter1.section3.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 加权有向图数据结构的实现
 *
 * @Auther: yusiming
 * @Date: 2018/10/7 16:01
 */
public class EdgeWeightedDigraph {
    /**
     * 顶点的个数
     */
    private final int V;
    /**
     * 边的条数
     */
    private int E;
    /**
     * 存放从每个顶点指出的边
     */
    private Bag<DirectedEdge>[] adj;

    /**
     * 构造一个V大下的图
     *
     * @param V 图的大小
     */
    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    /**
     * 从流中构造一副图
     *
     * @param in 流
     */
    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; i++) {
            addEdge(new DirectedEdge(in.readInt(), in.readInt(), in.readInt()));
        }
    }

    /**
     * 向图中添加一条边
     *
     * @param edge 边
     */
    public void addEdge(DirectedEdge edge) {
        adj[edge.form()].add(edge);
        E++;
    }

    /**
     * 返回顶点的个数
     *
     * @return 个数
     */
    public int V() {
        return V;
    }

    /**
     * @return 边的条数
     */
    public int E() {
        return E;
    }

    /**
     * 从该顶点指出的边
     *
     * @param v 顶点
     * @return 边的集合
     */
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    /**
     * @return 图中所有的边
     */
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (DirectedEdge edge : adj[i]) {
                bag.add(edge);
            }
        }
        return bag;
    }
}
