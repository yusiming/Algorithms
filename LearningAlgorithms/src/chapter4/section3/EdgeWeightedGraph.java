package chapter4.section3;

import chapter1.section3.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 加权无向图的数据结构的实现
 *
 * @Auther: yusiming
 * @Date: 2018/10/5 16:52
 */
public class EdgeWeightedGraph {
    /**
     * 图中顶点的个数
     */
    private int V;
    /**
     * 边的个数
     */
    private int E;
    /**
     * 背包数组，用来存储与顶点相关的边
     */
    private Bag<Edge>[] adj;

    /**
     * 构造一副含有V个顶点的加权无向图
     *
     * @param V 图的大小
     */
    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    /**
     * 从指定的流中构造一副图
     *
     * @param in 流
     */
    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; i++) {
            addEdge(new Edge(in.readInt(), in.readInt(), in.readInt()));
        }
    }

    /**
     * 为图中添加一条边，注意要为边的两个顶点都添加边
     *
     * @param edge 边
     */
    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    /**
     * 返回与顶点v相邻的边的集合
     *
     * @param v 顶点v
     * @return 边的集合
     */
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    /**
     * 返回图中所有的边，注意不能有重复的边
     *
     * @return 所有的边
     */
    public Iterable<Edge> edges() {
        Bag<Edge> bag = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (Edge edge : adj(i)) {
                // 只返回和值大于该顶点的顶点所形成的边，保证了没有重复的边
                if (edge.other(i) > i) {
                    bag.add(edge);
                }
            }
        }
        return bag;
    }

    /**
     * @return 顶点的个数
     */
    public int V() {
        return V;
    }
}
