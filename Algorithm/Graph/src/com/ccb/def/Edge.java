package com.ccb.def;

/**
 * 图 边
 */
public class Edge {
    /**
     * 权重
     */
    public int weight;

    /**
     * 起点
     */
    public Node from;

    /**
     * 终点
     */
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "weight=" + weight +
                ", from=" + from.value +
                ", to=" + to.value +
                '}';
    }
}
