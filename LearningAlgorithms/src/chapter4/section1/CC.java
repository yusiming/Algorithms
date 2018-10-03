package chapter4.section1;

/**
 * 连通分量问题
 *
 * @Auther: yusiming
 * @Date: 2018/10/2 17:17
 */
public class CC {
    /**
     * 使用一个boolean数组来表示一个顶点是否被探测过了
     */
    private boolean[] marked;
    /**
     * 每个顶点对应的连通分量的标识符的值
     */
    private int[] id;
    /**
     * 用来表示连通分量
     */
    private int count;

    public CC(Graph graph) {
        int v = graph.V();
        marked = new boolean[v];
        id = new int[v];
        for (int s = 0; s < v; s++) {
            if (!marked[s]) {
                dfs(graph, s);
                // 每次找到一个连通分量将count的值加1，最后一次加一其实是为了返回连通分量个数
                count++;
            }
        }

    }

    /**
     * 找出所有与顶点s连通的顶点
     *
     * @param graph 图
     * @param s     顶点s
     */
    private void dfs(Graph graph, int s) {
        marked[s] = true;
        id[s] = count;
        for (int i : graph.adj(s)) {
            if (!marked[i]) {
                marked[i] = true;
                dfs(graph, i);
            }
        }
    }

    /**
     * 判断两个顶点是否是连通的
     *
     * @param v 顶点v
     * @param w 顶点w
     * @return 是否连通
     */
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * 返回顶点v对于的连通分量的标识符
     *
     * @param v 顶点v
     * @return 标识符的值
     */
    public int id(int v) {
        return id[v];
    }

    /**
     * @return 连通分量的个数
     */
    public int count() {
        return count;
    }
}
