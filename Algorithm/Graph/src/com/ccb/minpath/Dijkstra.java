package com.ccb.minpath;

import com.ccb.def.Edge;
import com.ccb.def.Graph;
import com.ccb.def.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 寻找最短路径-不能有负值的边
 */
public class Dijkstra {

    /**
     * 解锁距离最短的点
     * @param startNode 起始点
     * @return 起始点到每个点的最短距离
     */
    public static Map<Node, Integer> find(Node startNode) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        // 保存已经是最短距离的点
        Set<Node> selectedNode = new HashSet<>();
        distanceMap.put(startNode, 0);
        Node minNode = findMinNode(distanceMap, selectedNode);
        while (minNode != null) {
            // 解锁当前点指向的点的距离
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                Integer newDistance = distanceMap.get(minNode) + edge.weight;
                if (! distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, newDistance);
                }
                distanceMap.put(toNode, Math.min(newDistance, distanceMap.get(toNode)));
            }
            selectedNode.add(minNode);
            minNode = findMinNode(distanceMap, selectedNode);
        }
        return distanceMap;
    }

    /**
     * 找到新解锁距离中，最短距离的节点
     * @param distanceMap 到点的距离
     * @param selectedNode 已经选过的点集
     * @return 最短距离的节点
     */
    private static Node findMinNode(Map<Node, Integer> distanceMap, Set<Node> selectedNode) {
        Node minNode = null;
        Integer minDistance = Integer.MAX_VALUE;
        for (Node node : distanceMap.keySet()) {
            Integer distance = distanceMap.get(node);
            if (! selectedNode.contains(node) && distance < minDistance ) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    private Dijkstra(){}
}
