package com.ccb.sort;

import com.ccb.def.Graph;
import com.ccb.def.Node;

import java.util.*;

/**
 * 拓扑排序实现
 */
public class TopologicalSort {

    /**
     * 依次找到入度为0的点，弹出
     * @param graph 图
     */
    public static void sort(Graph graph) {
        boolean[] isVisit = new boolean[graph.nodes.size()];
        List<Node> zeroInNodes = findZeroInNode(graph, isVisit);
        while (zeroInNodes.size() != 0) {
            for (int i = 0; i < zeroInNodes.size(); i++) {
                // 处理节点
                System.out.println(zeroInNodes.get(i));
                isVisit[zeroInNodes.get(i).value] = true;
                // 连接点入度-1
                for (int j = 0; j < zeroInNodes.get(i).nexts.size(); j++) {
                    zeroInNodes.get(i).nexts.get(j).in--;
                }
            }
            zeroInNodes = findZeroInNode(graph, isVisit);
        }
    }

    /**
     * 找到入度为0的所有点
     * @param graph 图
     * @param isVisit 是否访问标识数组
     * @return 点集
     */
    private static List<Node> findZeroInNode(Graph graph, boolean[] isVisit) {
        List<Node> res = new ArrayList<>();
        for (int i = 0; i < graph.nodes.size(); i++) {
            Node node = graph.nodes.get(i);
            if (node.in == 0 && ! isVisit[node.value]) {
                res.add(node);
            }
        }
        return res;
    }

    /**
     * 依次找到入度为0的点，弹出
     * 优化时间复杂度并且不改变图的结构
     * @param graph 图
     */
    public static void sort0(Graph graph) {
        Queue<Node> queue = new LinkedList<>();
        // key:节点 value:入度值
        Map<Node, Integer> map = new HashMap<>();
        for (int i = 0; i < graph.nodes.size(); i++) {
            map.put(graph.nodes.get(i), graph.nodes.get(i).in);
            if (0 == graph.nodes.get(i).in) {
                queue.add(graph.nodes.get(i));
            }
        }
        while (! queue.isEmpty()) {
            Node node = queue.poll();
            // 处理节点
            System.out.println(node);
            for (int i = 0; i < node.nexts.size(); i++) {
                map.put(node.nexts.get(i), map.get(node.nexts.get(i)) - 1);
                if (0 == map.get(node.nexts.get(i))) {
                    queue.add(node.nexts.get(i));
                }
            }
        }
    }


    private TopologicalSort() {}

}
