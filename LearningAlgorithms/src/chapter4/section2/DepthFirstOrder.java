package chapter4.section2;

import chapter1.section3.Queue;
import chapter1.section3.Stack;

/**
 * 基于深度优先搜索的顶点排序
 *
 * @Auther: yusiming
 * @Date: 2018/10/3 17:35
 */
public class DepthFirstOrder {
    private boolean[] marked;
    /**
     * 前序排列的顶点
     */
    private Queue<Integer> pre;
    /**
     * 后序排列的顶点
     */
    private Queue<Integer> post;
    /**
     * 逆后序排列的顶点
     */
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph digraph) {
        marked = new boolean[digraph.V()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        for (int i = 0; i < digraph.V(); i++) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
        }
    }

    private void dfs(Digraph digraph, int s) {
        pre.enqueue(s);
        marked[s] = true;
        for (int i : digraph.adj(s)) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
        }
        post.enqueue(s);
        reversePost.push(s);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
