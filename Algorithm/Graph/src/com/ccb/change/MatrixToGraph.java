package com.ccb.change;

import com.ccb.def.Edge;
import com.ccb.def.Graph;
import com.ccb.def.Node;

public class MatrixToGraph {

    /**
     * 将图表示的二维矩阵转为自定义的图结构
     * @param matrix 二维数组 如果不可达值为null
     * @return 自定义图结构
     */
    public static Graph change(Integer[][] matrix) {
        Graph graph = new Graph();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null) {
                    // 构造节点
                    Node from;
                    if (graph.nodes.get(i) == null) {
                        from = new Node(i);
                        graph.nodes.put(i, from);
                    } else {
                        from = graph.nodes.get(i);
                    }
                    Node to;
                    if (graph.nodes.get(j) == null) {
                        to = new Node(j);
                        graph.nodes.put(j, to);
                    } else {
                        to = graph.nodes.get(j);
                    }
                    // 构造边
                    Edge edge = new Edge(matrix[i][j], from, to);
                    graph.edges.add(edge);
                    // 设置 节点 出度/入度 邻接点 出边
                    from.out++;
                    to.in++;
                    from.nexts.add(to);
                    from.edges.add(edge);
                }
            }
        }

        return graph;
    }

    /**
     * 将边组成的二维矩阵转为图
     * @param matrix 边组成的N*3数组
     * @return 图
     */
    public static Graph change0(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer fromNum = matrix[i][0];
            Integer toNum = matrix[i][1];
            Integer weight = matrix[i][2];
            if (! graph.nodes.containsKey(fromNum)) {
                graph.nodes.put(fromNum, new Node(fromNum));
            }
            if (! graph.nodes.containsKey(toNum)) {
                graph.nodes.put(toNum, new Node(toNum));
            }
            Node from = graph.nodes.get(fromNum);
            Node to = graph.nodes.get(toNum);
            Edge edge = new Edge(weight, from, to);
            graph.edges.add(edge);
            // 设置 节点 出度/入度 邻接点 出边
            from.out++;
            to.in++;
            from.nexts.add(to);
            from.edges.add(edge);
        }
        return graph;
    }

    private MatrixToGraph(){}
}
