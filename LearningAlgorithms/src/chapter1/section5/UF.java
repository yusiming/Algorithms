package chapter1.section5;

/**
 * @Auther: yusiming
 * @Date: 2018/8/20 15:47
 * @Description: 动态连通问题
 */
public class UF {
    // 使用数组存储数据，使用数组的值，作为是否是同一连通分量的依据
    private int[] id;
    // 数组中分量的个数
    private int count;

    public UF(int N) {
        count = N;
        // 初始化数组，
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // 返回数组中分量的个数
    public int count() {
        return count;
    }

    // 查询p所在的分量的标识符
    public int find(int p) {
        return id[p];
    }

    // 判断p和q是否在同一分量中
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 连接p和q
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        // 若p和q已经相连，什么都不做
        if (pId == qId) {
            return;
        }
        // 若p和q不相连，将p所在的分量的标识符改为q所在分量的标识符
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
        // 分量数量减一
        count--;
    }
}
