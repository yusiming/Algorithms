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
    private int V;
    private int E;
    private Bag<Edge>[] adj;

    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; i++) {
            addEdge(new Edge(in.readInt(), in.readInt(), in.readInt()));
        }
    }

    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

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

    public int V() {
        return V;
    }
}
