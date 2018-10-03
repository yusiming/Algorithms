package chapter4.section1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 使用邻接表数组实现Graph数据结构
 * <p>
 * 数组中存储了Integer类型的背包，其中数组的下标代表了顶点
 * 每个位置的背包中的数据代表了于该顶点直接相连的顶点
 *
 * @Auther: yusiming
 * @Date: 2018/10/1 16:40
 */
public class Graph {
    /**
     * 顶点的数量
     */
    private final int V;
    /**
     * 边的条数
     */
    private int E;
    /**
     * 使用背包数组存储数据
     */
    private Bag<Integer>[] adj;

    /**
     * 构造一副参数大小的图
     */
    @SuppressWarnings("unchecked")
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        // 在数组的每个位置存储一个背包，用来保存于顶点直接的相连的顶点
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    /**
     * 从输入流中读取一副图，其中第一个int值代表了图的顶点数
     * 第二个int值代表了边的数量
     * 其余的整数对，代表的那些顶点是相连的
     */
    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            // 连接各个顶点
            addEdge(in.readInt(), in.readInt());
        }
    }

    /**
     * 方法返回顶点的个数
     *
     * @return 返回的顶点的个数
     */
    public int V() {
        return V;
    }

    /**
     * 方法返回边的数量
     *
     * @return 边的数量
     */
    public int E() {
        return E;
    }

    /**
     * 在顶点v和w之间添加一条边
     *
     * @param v 顶点v
     * @param w 顶点w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * 返回和顶点v相邻的所有顶点
     *
     * @param v 顶点
     * @return 和顶点v相邻的结点
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 打印每个顶点的相邻顶点
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
    }
}
