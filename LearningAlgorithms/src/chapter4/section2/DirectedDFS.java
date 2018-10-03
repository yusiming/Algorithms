package chapter4.section2;

import edu.princeton.cs.algs4.In;
import chapter1.section3.Bag;

/**
 * 使用深度优先搜索寻找从指定顶点可达的所有顶点
 *
 * @Auther: yusiming
 * @Date: 2018/10/3 16:02
 */
public class DirectedDFS {
    /**
     * 顶点是否可到达
     */
    private boolean[] marked;

    /**
     * 从指定图中，寻找从顶点s可达的所有顶点
     *
     * @param digraph 有向图
     * @param s       顶点
     */
    public DirectedDFS(Digraph digraph, int s) {
        marked = new boolean[digraph.V()];
        dfs(digraph, s);
    }

    /**
     * 从指定图中，寻找从source中的所有顶点可达的顶点
     *
     * @param digraph 有向图
     * @param source  集合
     */
    public DirectedDFS(Digraph digraph, Iterable<Integer> source) {
        marked = new boolean[digraph.V()];
        for (int i : source) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
        }
    }

    /**
     * 寻找顶点s可达的所有的顶点
     *
     * @param digraph 图
     * @param s       顶点s
     */
    private void dfs(Digraph digraph, int s) {
        marked[s] = true;
        for (int i : digraph.adj(s)) {
            if (!marked[i]) {
                marked[i] = true;
                dfs(digraph, i);
            }
        }
    }

    /**
     * 顶点v是否可达
     *
     * @param v 顶点
     * @return boolean
     */
    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        In in = new In("tinyDG.txt");
        Digraph digraph = new Digraph(in);
        Bag<Integer> source = new Bag<>();
        source.add(1);
        source.add(2);
        source.add(6);
        DirectedDFS directedDFS = new DirectedDFS(digraph, source);
        for (int i = 0; i < digraph.V(); i++) {
            if (directedDFS.marked(i)) {
                System.out.print(i + " ");
            }
        }
    }
}
