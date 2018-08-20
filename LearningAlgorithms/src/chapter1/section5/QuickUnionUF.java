package chapter1.section5;

/**
 * @Auther: yusiming
 * @Date: 2018/8/20 16:35
 * @Description: UF的改进
 */
public class QuickUnionUF {
    private int[] id;
    private int count;

    public QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    /**
     * @Description: 找到连接的根节点，
     * 树的根结点，id[root] == root，其余的结点总是指向其他的结点，最终会指向根节点，即
     * 总有一个结点id[p] = root；
     * @auther: yusiming
     * @date: 16:46 2018/8/20
     * @param: [p]
     * @return: int
     */
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    /**
     * @Description: 若p和q的根节点相同，那么p和q属于同一分量
     * @auther: yusiming
     * @date: 16:50 2018/8/20
     * @param: [p, q]
     * @return: boolean
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * @Description: 若p和q在同一分量，什么也不做
     * 若不在同一分量，将p所在的根节点，指向q所在的根节点，连接两个分量
     * @auther: yusiming
     * @date: 16:51 2018/8/20
     * @param: [p, q]
     * @return: void
     */
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (p == q) {
            return;
        }
        // pRoot不再指向自己，而是指向qRoot
        id[pRoot] = qRoot;
        count--;
    }
}
