package chapter4.section1;

import chapter1.section3.Queue;

/**
 * 广度优先搜索
 *
 * @Auther: yusiming
 * @Date: 2018/10/2 16:39
 */
public class BreadthFirstSearch {
    /**
     * 使用一个boolean数组来表示到达该顶点的最短路径是否已知
     */
    private boolean[] marked;
    /**
     * 到达该顶点的已知路径上的最后一个顶点
     */
    private int[] edgeTo;
    /**
     * 起点的位置
     */
    private final int s;

    public BreadthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.s = s;
        bfs(graph, s);
    }

    /**
     * 方法将记录从起点s到其他顶点的最短路径
     *
     * @param graph 图
     * @param s     起点s
     */
    public void bfs(Graph graph, int s) {
        // 使用队列来保证进行广度搜素，按照与起点的距离来遍历所有顶点
        Queue<Integer> queue = new Queue<>();
        // 标记起点
        marked[s] = true;
        // 将起点送入队列
        queue.enqueue(s);
        // 若队列不为空，一直遍历
        while (!queue.isEmpty()) {
            // 删除与起点距离近的顶点
            Integer v = queue.dequeue();
            // 若顶点v还有相邻的顶点没有被标记，那么将顶点插入队列的末尾
            for (int i : graph.adj(v)) {
                if (!marked[i]) {
                    marked[i] = true;
                    edgeTo[i] = v;
                    queue.enqueue(i);
                }
            }
        }
    }

    /**
     * 是否有到顶点v的最短路径
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }
}
