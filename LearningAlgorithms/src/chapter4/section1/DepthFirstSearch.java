package chapter4.section1;

import chapter1.section3.Stack;
import edu.princeton.cs.algs4.In;

/**
 * 深度优先搜索
 *
 * @Auther: yusiming
 * @Date: 2018/10/1 17:47
 */
public class DepthFirstSearch {
    /**
     * 使用一个boolean数组标记一个顶点是否已经被访问过了
     * 若被访问过了，那么相应的值为true，否则为false
     */
    private boolean[] marked;
    /**
     * 和指定顶点连通的顶点的个数
     */
    private int count;
    /**
     * 使用数组来记录，每一个顶点到起点的路径
     */
    private int[] edgeTo;
    /**
     * 起点的位置
     */
    private final int s;

    /**
     * 找到图graph中，所有和s顶点连通的顶点
     */
    public DepthFirstSearch(Graph graph, int v) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.s = v;
        dfs(graph, v);
    }

    /**
     * 寻找所有与顶点s连通的结点
     */
    private void dfs(Graph graph, int v) {
        marked[v] = true;
        count++;
        for (int i : graph.adj(v)) {
            while (!marked[i]) {
                edgeTo[i] = v;
                dfs(graph, i);
            }
        }
    }

    /**
     * 判断顶点s和顶点v是否是联通的
     *
     * @param v 顶点
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * 返回与顶点s连通的顶点数量
     *
     * @return 顶点个数
     */
    public int count() {
        return count;
    }

    /**
     * 方法返回从起点s是否有到指定顶点v的路径
     *
     * @param v 顶点v
     * @return 是否有去顶v的路径
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i]) {
            stack.push(i);
        }
        // 最后再加上起点
        stack.push(s);
        return stack;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In("tinyCG.txt"));
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph, 0);
        int s = 0;
        Iterable<Integer> integers = depthFirstSearch.pathTo(s);
        for (int i : integers) {
            if (i == s) {
                System.out.print(i);
            } else {
                System.out.print(i + "-");
            }
        }
    }
}
