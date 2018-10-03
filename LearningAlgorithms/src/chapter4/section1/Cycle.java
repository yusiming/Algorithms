package chapter4.section1;

import edu.princeton.cs.algs4.In;

/**
 * 判断一个图中是否存在环
 *
 * @Auther: yusiming
 * @Date: 2018/10/2 17:59
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph graph) {
        int v = graph.V();
        marked = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!marked[i]) {
                dfs(graph, 0, 0);
            }
        }
    }

    /**
     * 方法将使用深度搜索来查找是否图中含有环
     * <p>
     * 若在递归搜索的时候，有一个顶点的邻接结点被标记了，
     * 但是那个顶点不是递归搜索时它的上一层顶点,那么图中就存在环
     *
     * @param graph 图
     * @param v     顶点v
     * @param u     递归搜索时v的上一层顶点
     */
    private void dfs(Graph graph, int v, int u) {
        marked[v] = true;
        for (int i : graph.adj(v)) {
            if (!marked[i]) {
                dfs(graph, i, v);
            } else if (i != u) {
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In("tinyG.txt"));
        Cycle cycle = new Cycle(graph);
        System.out.println(cycle.hasCycle());
    }
}
