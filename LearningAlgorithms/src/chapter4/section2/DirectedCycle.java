package chapter4.section2;

import chapter1.section3.Stack;
import edu.princeton.cs.algs4.In;

/**
 * 寻找有向图中的有向环
 *
 * @Auther: yusiming
 * @Date: 2018/10/3 16:57
 */
public class DirectedCycle {
    /**
     * 用来标记是否被检测过了
     */
    private boolean marked[];
    /**
     * 到每一个顶点的路径的最后一个顶点
     */
    private int[] edgeTo;
    /**
     * 有向中的所有的顶点
     */
    private Stack<Integer> cycle;
    /**
     * 处于递归调用栈上的所有的顶点
     */
    private boolean[] onStack;

    /**
     * 在构造对象的同时，判断图中是否含有环
     *
     * @param digraph 有向图
     */
    public DirectedCycle(Digraph digraph) {
        int v = digraph.V();
        marked = new boolean[v];
        edgeTo = new int[v];
        onStack = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
        }
    }

    /**
     * 从顶点v开始寻找环
     *
     * @param digraph 有向图
     * @param v       顶点
     */
    private void dfs(Digraph digraph, int v) {
        // 标记被检测过了
        marked[v] = true;
        // 标记此顶点在栈中
        onStack[v] = true;
        for (int i : digraph.adj(v)) {
            // 若存在环，方法结束
            if (this.hasCycle()) {
                return;
            } else if (!marked[i]) {
                // 记录路径
                edgeTo[i] = v;
                dfs(digraph, i);
            } else if (onStack[i]) {
                /*
                 * 如果一个顶点被标记了，还在栈中，那么就存在环
                 * 将环的路径写入cycle
                 */
                cycle = new Stack<>();
                for (int w = v; w != i; w = edgeTo[w]) {
                    cycle.push(w);
                }
                cycle.push(i);
                cycle.push(v);
            }
        }
    }

    /**
     * 图中是否存在环
     *
     * @return boolean
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * 返回环中的所有顶点,如果存在的话
     *
     * @return 可迭代的栈
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        In in = new In("tinyDG.txt");
        Digraph digraph = new Digraph(in);
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        Iterable<Integer> cycle = directedCycle.cycle();
        for (int i : cycle) {
            System.out.println(i);
        }
    }
}
