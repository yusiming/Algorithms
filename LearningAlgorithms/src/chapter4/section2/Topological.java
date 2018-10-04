package chapter4.section2;

/**
 * 拓扑排序
 * <p>
 * 将所有的顶点排序，使得所有的有向边从排在前面的元素指向排在后面的元素，
 * 拓扑排序能够完成的前提是图中不存在环，所以在排序时要检测是否存在环
 *
 * @Auther: yusiming
 * @Date: 2018/10/4 15:55
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph digraph) {
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        // 若不存在环，将顶点排序
        if (!directedCycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
            // 返回逆后序的顶点排列
            order = depthFirstOrder.reversePost();
        }
    }

    /**
     * 返回拓扑有序的所有顶点
     */
    public Iterable<Integer> order() {
        return order;
    }

    /**
     * 判断图是否时有向无环图
     *
     * @return boolean
     */
    public boolean isDAG() {
        return order != null;
    }

}
