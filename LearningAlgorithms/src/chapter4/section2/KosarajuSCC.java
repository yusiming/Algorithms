package chapter4.section2;

/**
 * kosaraju算法
 * <p>
 * 要证明kosaraju算法的正确性，我们需要证明两点：
 * 设原图为G，取反之后的图为[G]
 * 1.每个和s强连通的顶点v在dfs(G,s)中都会被访问到，证明如下：
 * 若有一个顶点未被访问到，那么这个顶点之前就被标记过了，
 * 但是如果被标记过了，那么s必定会被访问到，因为是强连通，也就不存在调用dfs(G,s)了，
 * 所以能够调用dfs(G,s)，这个强连通分量中的任意一个顶点都未被访问过。
 * 2.构造函数调用用的dfs(G,s),所到达的任意顶点v必然和s是强连通的，证明如下：
 * 因为构造函数调用的dfs(G,s)，是按照[G]的逆后序排列进行调用的，
 * 所以这个序列中出现的顶点，在[G]中进行的深度搜索，后面的顶点必然会在前面的顶点的调用结束之后才会结束
 * 假设dfs(G,s)，到达了某个顶点v，那么在原图中存在 s-->v的路径,[G]中存在v--s的路径，
 * 但是在[G]中，dfs([G],v)必然在dfs([G],s)结束前结束，因为是逆后序排列，所以存在两种情况：
 * 1.dfs([G],v)在调用dfs([G],s)之前调用，也在它调用之前结束，这种情况不可能，因为[G]中存在v--s的路径，
 * 2.dfs([G],v)在调用dfs([G],s)之后调用，并且在它结束前结束，这种情况证明了，[G]中存在s-->v的路径，
 * <p>
 * 因为[G]中存在s-->v的路径，G中存在s-->v的路径，所以这两个顶点是强连通的，
 * 证明完毕
 *
 * @Auther: yusiming
 * @Date: 2018/10/4 18:48
 */
public class KosarajuSCC {
    /**
     * 标志是否被访问过了
     */
    private boolean[] marked;
    /**
     * 记录每个顶点所属强连通分量的id
     */
    private int[] id;
    /**
     * 强连通分量的个数
     */
    private int count;

    public KosarajuSCC(Digraph digraph) {
        int v = digraph.V();
        marked = new boolean[v];
        id = new int[v];
        DepthFirstOrder order = new DepthFirstOrder(digraph.reverse());
        // 按照原图取反的逆后序排列的顺序遍历
        for (int i : order.reversePost()) {
            if (!marked[i]) {
                dfs(digraph, i);
                count++;
            }
        }
    }

    /**
     * 深度优先搜索
     *
     * @param digraph 图
     * @param s       顶点
     */
    private void dfs(Digraph digraph, int s) {
        marked[s] = true;
        id[s] = count;
        for (int i : digraph.adj(s)) {
            if (!marked[i]) {
                marked[i] = true;
            }
        }
    }

    /**
     * 判断v和s是否是强连通的
     *
     * @param v 顶点
     * @param w 顶点
     */
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * @return 强连通分量的个数
     */
    public int count() {
        return count;
    }

    /**
     * @param v 顶点v
     * @return 顶点v所属的强连通分量的标志
     */
    public int id(int v) {
        return id[v];
    }
}
