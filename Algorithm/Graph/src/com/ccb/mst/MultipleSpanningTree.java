package com.ccb.mst;

import com.ccb.def.Edge;
import com.ccb.def.Graph;
import com.ccb.def.Node;

import java.util.*;

/**
 * 最小生成树算法
 */
public class MultipleSpanningTree {

    /**
     * prim【普里姆】算法-无向图
     * 每次选择与选择的点集中权值最小的边(用优先级队列优化)，将点加入点集中
     * @param graph 图
     * @return 边集
     */
    public static Set<Edge> prim(Graph graph) {
        Set<Edge> res = new HashSet<>();
        Set<Node> nodeSet = new HashSet<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        // 随机从某个点开始, 可能会形成森林[如果不是完全连通，所以加层for]
        for (Node node : graph.nodes.values()) {
            // 选取没有操作过的点
            if (! nodeSet.contains(node)) {
                nodeSet.add(node);
                // 将点连接的边加入优先级队列
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }
                while (! priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll();
                    if (! nodeSet.contains(edge.to)) {
                        res.add(edge);
                        nodeSet.add(edge.to);
                        for (Edge nextEdge : edge.to.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * Kruskal【克鲁斯卡尔】算法
     * 每次选择最小值的边【不能形成环】
     *                      ｜
     * 使用并查集思想-查看边连接的两个点是否在同一个集合里面，边集加入还需要合并集合
     * @param graph 图
     * @return 边集
     */
    public static Set<Edge> Kruskal(Graph graph) {
        Set<Edge> res = new HashSet<>();
        UnionSet unionSet = new UnionSet(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }
        while (! priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (! unionSet.isSameSet(edge.from, edge.to)) {
                res.add(edge);
                unionSet.union(edge.from, edge.to);
            }
        }
        return res;
    }

    private static class UnionSet {
        /**
         * key:节点 value:节点所在集合
         */
        public Map<Node, Set<Node>> setMap;

        public UnionSet(Collection<Node> nodes) {
            setMap = new HashMap<>();
            for (Node node : nodes) {
                Set<Node> set = new HashSet<>();
                set.add(node);
                setMap.put(node, set);
            }
        }

        /**
         * 判断两个节点是否在同一个集合里面
         * @param one 一个节点
         * @param two 另一个节点
         * @return
         */
        public boolean isSameSet(Node one, Node two) {
            return setMap.get(one) == setMap.get(two);
        }

        /**
         * 合并两个点对应的集合
         * @param one
         * @param two
         */
        public void union(Node one, Node two) {
            Set<Node> set = setMap.get(one);
            Set<Node> set0 = setMap.get(two);
            for(Node node : set0) {
                set.add(node);
                setMap.put(node, set);
            }
        }
    }
}
