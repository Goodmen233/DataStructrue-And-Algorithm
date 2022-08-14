package com.ccb.def;

import java.util.*;

/**
 * 图 总结构
 */
public class Graph {
    /**
     * key：点编号
     * value：实际点
     */
    public Map<Integer, Node> nodes;

    /**
     * 边集
     */
    public Set<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }

}
