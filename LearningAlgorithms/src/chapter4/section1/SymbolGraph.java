package chapter4.section1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * 符号图数据结构的实现
 * <p>
 * 使用符号表来存储字符串，使用顶点为键，值为一个唯一的int值
 * 在keys数组的对应的位置上存放表示顶点的字符串，数组的索引就是字符串对应的int值，
 * 使用字符串对应的int值来构造一副图
 *
 * @Auther: yusiming
 * @Date: 2018/10/2 19:22
 */
public class SymbolGraph {
    /**
     * 符号名 --> 索引
     */
    private ST<String, Integer> st;
    /**
     * 符号名索引 --> 符号名
     */
    private String[] keys;
    /**
     * 表示顶点关系的图
     */
    private Graph graph;

    /**
     * 构造函数，在构造函数中，从指定的文件读取字符串，
     * 构造符号表、反向索引表和图
     *
     * @param stream 文件的位置
     * @param sp     分割字符串的符号
     */
    public SymbolGraph(String stream, String sp) {
        // 初始化符号表
        st = new ST<>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            // 为每一个字符产生一个唯一的int值，
            String[] a = in.readLine().split(sp);
            for (int i = 0; i < a.length; i++) {
                // 若符号表中不存在该键，那么插入符号表中
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        int size = st.size();
        keys = new String[size];
        for (String name : st.keys()) {
            // 符号表的key就是顶点对应的字符串，将唯一的int值，当作数组的下标，并在相应的位置插入对应的字符串
            keys[st.get(name)] = name;
        }
        graph = new Graph(size);
        // 流只能被读取一次
        in = new In(stream);
        while (in.hasNextLine()) {
            // 将每一行开始的字符串对应的顶点，与其他顶点连接起来
            String[] a = in.readLine().split(sp);
            // 第一个字符串对应的int值，从符号表中取,因为符号表已经为每一个字符串分配了一个唯一的int值
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                // 连接顶点
                graph.addEdge(v, st.get(a[i]));
            }
        }
    }

    /**
     * 判断符号图中是否包含顶点s
     *
     * @param s 顶点s对应的字符串
     * @return boolean值
     */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * 返回顶点s对应的索引
     *
     * @param s 顶点s对应的字符串
     * @return 顶点s对应的索引
     */
    public int index(String s) {
        return st.get(s);
    }

    /**
     * 返回索引v对应的顶点的字符串表示
     *
     * @param v 索引
     * @return 顶点对应的字符串的表示
     */
    public String name(int v) {
        return keys[v];
    }

    /**
     * 返回由指定文件构造出来的图
     *
     * @return 图
     */
    public Graph G() {
        return graph;
    }
}
