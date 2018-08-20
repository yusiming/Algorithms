package chapter1.section5;

/**
 * @Auther: yusiming
 * @Date: 2018/8/20 17:43
 * @Description:
 */
public class WeightedQuickUnionUF {
    // 使用数组保存数据
    private int[] id;
    // 记录树的个数
    private int count;
    // 记录树的大小
    private int[] size;

    /**
     * @Description: 初始化两个数组，
     * @auther: yusiming
     * @date: 17:56 2018/8/20
     * @param: [N]
     * @return:
     */
    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * @Description: 当p和q在一个树中时，什么也不做
     * 当p和q不在一个树中时，获取p和q所在的两个树的大小，将较小的树，连接到较大的树上，
     * @auther: yusiming
     * @date: 17:58 2018/8/20
     * @param: [p, q]
     * @return: void
     */
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        // 获取树的大小
        int pSize = size[pRoot];
        int qSize = size[qRoot];
        // 比较大小，
        if (pSize > qSize) {
            // 将较小的树，连接到较大的树上
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        } else {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];

        }
        count--;
    }
}
