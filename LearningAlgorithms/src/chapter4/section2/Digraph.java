package chapter4.section2;

import chapter1.section3.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 有向图的数据结构的实现
 * 基本与无向图类似，或者说比无向图更加容易表示
 *
 * @Auther: yusiming
 * @Date: 2018/10/3 15:02
 */
public class Digraph {
    /**
     * 顶点的个数
     */
    private final int V;
    /**
     * 边的数量
     */
    private int E;
    /**
     * 存储数据的背包数组
     */
    private Bag<Integer>[] adj;

    /**
     * 构造方法，创建一个V个顶点的图
     *
     * @param V 顶点的个数
     */
    @SuppressWarnings("unchecked")
    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    /**
     * 从流中读取一副有向图
     *
     * @param in 流
     */
    public Digraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            // 连接各个顶点
            addEdge(in.readInt(), in.readInt());
        }
    }

    /**
     * 添加一条由v-->w的边
     *
     * @param v 顶点v
     * @param w 顶点w
     */
    private void addEdge(int v, int w) {
        adj[v].add(w);
        // 有向边的数量加一
        E++;
    }

    /**
     * @return 顶点的个数
     */
    public int V() {
        return this.V;
    }

    /**
     * @return 有向边的条数
     */
    public int E() {
        return this.E;
    }

    /**
     * 返回由顶点v指出的边所连接的顶点
     *
     * @param v 顶点
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 方法返回一个所有边的方向与原图相反的图
     *
     * @return 反向图
     */
    public Digraph reverse() {
        Digraph digraph = new Digraph(this.V);
        for (int i = 0; i < V; i++) {
            for (int w : adj[i]) {
                digraph.addEdge(w, i);
            }
        }
        return digraph;
    }

    /**
     * 打印与每个顶点的相邻的能够到达的顶点
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(V + "个顶点，" + E + "条边\n");
        for (int i = 0; i < V; i++) {
            builder.append("第").append(i).append("个顶点：");
            for (int w : adj[i]) {
                builder.append(w).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        In in = new In("tinyDG.txt");
        Digraph digraph = new Digraph(in);
        System.out.println(digraph.toString());
    }
}
