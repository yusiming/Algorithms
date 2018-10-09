package chapter4.section4;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * @Auther: yusiming
 * @Date: 2018/10/7 16:22
 */
public class Dijkstra {
    private DirectedEdge[] edgeTo;
    private double[] disTo;
    private IndexMinPQ<Double> pq;

    public Dijkstra(EdgeWeightedDigraph G, int s) {
        int v = G.V();
        edgeTo = new DirectedEdge[v];
        disTo = new double[v];
        pq = new IndexMinPQ<>(v);
        for (int i = 0; i < v; i++) {
            disTo[i] = Double.POSITIVE_INFINITY;
        }
        disTo[s] = 0.0;
        edgeTo[s] = null;
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    public void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge edge : G.adj(v)) {
            int to = edge.to();
            if (disTo[to] > disTo[v] + edge.weight()) {
                disTo[to] = disTo[v] + edge.weight();
                edgeTo[to] = edge;
                if (pq.contains(to)) {
                    pq.changeKey(to, disTo[to]);
                } else {
                    pq.insert(to, disTo[to]);
                }
            }
        }
    }

    public double disTo(int v) {
        return disTo[v];
    }

    public boolean hasPathTo(int v) {
        return disTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.form()]) {
            path.push(edge);
        }
        return path;
    }
}
