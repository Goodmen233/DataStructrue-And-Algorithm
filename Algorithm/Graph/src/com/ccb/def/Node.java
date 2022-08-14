package com.ccb.def;

import java.util.ArrayList;
import java.util.List;

/**
 * 图 节点结构
 */
public class Node {
    /**
     * 点对应的编号
     */
    public int value;

    /**
     * 入度
     */
    public int in;

    /**
     * 出度
     */
    public int out;

    /**
     * 下一个节点集合
     */
    public List<Node> nexts;

    /**
     * 出边的集合（入边不算）
     */
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder nextsStr = new StringBuilder();
        nextsStr.append("[");
        for (int i = 0; i < nexts.size(); i++) {
            nextsStr.append(nexts.get(i).value);
            nextsStr.append(" ");
        }
        nextsStr.replace(nextsStr.length() - 1, nextsStr.length(), "]");
        StringBuilder edgesStr = new StringBuilder();
        edgesStr.append("[");
        for (int i = 0; i < edges.size(); i++) {
            edgesStr.append(edges.get(i).from.value + "->" + edges.get(i).to.value + "(" + edges.get(i).weight + ")");
            edgesStr.append(" ");
        }
        edgesStr.replace(edgesStr.length() - 1, edgesStr.length(), "]");
        return "Node{" +
                "value=" + value +
                ", in=" + in +
                ", out=" + out +
                ", nexts=" + nextsStr.toString() +
                ", edges=" + edgesStr.toString() +
                '}';
    }
}
