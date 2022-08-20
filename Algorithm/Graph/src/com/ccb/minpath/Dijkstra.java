package com.ccb.minpath;

import com.ccb.def.Edge;
import com.ccb.def.Node;

import java.util.*;

/**
 * 寻找最短路径-不能有负值的边
 */
public class Dijkstra {

    /**
     * 解锁距离最短的点
     * @param startNode 起始点
     * @return 起始点到每个点的最短距离
     */
    public static Map<Node, Integer> find0(Node startNode) {
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

    /**
     * 解锁距离最短的点-使用改写堆的方式
     * @param startNode 起始点
     * @return 起始点到每个点的最短距离
     */
    public static Map<Node, Integer> find(Node startNode) {
        Map<Node, Integer> result = new HashMap<>(8);
        NodeHeap heap = new NodeHeap();
        heap.add(startNode, 0);
        while(heap.size != 0) {
            NodeRecord record = heap.pop();
            Node node = record.node;
            int dis = record.distance;
            for (Edge edge : node.edges) {
                heap.add(edge.to, edge.weight);
            }
            result.put(node, dis);
        }
        return result;
    }

    /**
     * 记录指定节点到某一节点的值
     */
    public static class NodeRecord {
        public Node node;
        public Integer distance;

        public NodeRecord(Node node, Integer distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    /**
     * 自定义小根堆堆
     */
    public static class NodeHeap {
        /**
         * 堆数组
         */
        List<NodeRecord> nodeRecordList;
        /**
         * 堆大小
         */
        int size;
        /**
         * 节点在堆数组中的位置
         */
        Map<Node, Integer> nodeIndexMap;

        public NodeHeap() {
            this.nodeRecordList = new ArrayList<>(10);
            this.size = 0;
            this.nodeIndexMap = new HashMap<>(8);
        }

        /**
         * 判断节点是否在堆里
         * @param node 节点
         * @return 是否在
         */
        public boolean isInHeap(Node node) {
            // 默认规则，如果弹出堆了，设置为-1
            return isEnter(node) && nodeIndexMap.get(node) != -1;
        }


        /**
         * 判断节点是否进入过堆里
         * @param node 节点
         * @return 是否进入
         */
        public boolean isEnter(Node node) {
            return nodeIndexMap.containsKey(node);
        }

        /**
         * 将到的节点和位置，放入堆中
         * @param node 节点
         * @param distance 距离
         */
        public void add(Node node, int distance) {
            // 如果当前节点在堆里，直接更新距离
            if (isInHeap(node)) {
                int curDistance = nodeRecordList.get(nodeIndexMap.get(node)).distance;
                if (curDistance > distance) {
                    insertHeapify(nodeIndexMap.get(node), new NodeRecord(node, distance));
                }
            }
            // 如果当前节点没有进过堆里，新建
            if (! isEnter(node)) {
                insertHeapify(size++, new NodeRecord(node, distance));
            }
        }

        /**
         * 弹出最小的距离点
         * @return 节点记录
         */
        public NodeRecord pop() {
            NodeRecord record = nodeRecordList.get(0);
            swap(0, size - 1);
            nodeIndexMap.put(record.node, -1);
            nodeRecordList.remove(size - 1);
            heapify(0, --size);
            return record;
        }


        /**
         * 插入节点记录，并调整到指定位置
         * @param nodeRecord 节点记录
         * @return 插入位置
         */
        public int insertHeapify(int pos, NodeRecord nodeRecord) {
            nodeRecordList.set(pos, nodeRecord);
            nodeIndexMap.put(nodeRecord.node, pos);
            int parent;
            int cur = pos;
            while ((parent = (cur - 1) / 2) > 0 && nodeRecordList.get(parent).distance > nodeRecord.distance) {
                swap(parent, cur);
                cur = parent;
            }
            size++;
            return cur;
        }

        /**
         * 从上面堆化
         * @param index 开始位置
         * @param size 当前堆容量
         */
        public void heapify(int index, int size) {
            int left = index * 2 + 1;
            int smallest;
            while (left < size) {
                // 比较左右子节点
                smallest = left + 1 < size && nodeRecordList.get(left + 1).distance < nodeRecordList.get(left).distance
                        ? left + 1 : left;
                // 再比较最小的与父节点
                smallest = nodeRecordList.get(smallest).distance < nodeRecordList.get(index).distance
                        ? smallest : index;
                // 如果最小的就是父节点
                if (smallest == index) {
                    break;
                }
                // 如果最小的不是父节点，交换，继续
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        /**
         * 交换指定位置上的节点记录
         * @param index1 位置1
         * @param index2 位置2
         */
        private void swap(int index1, int index2) {
            NodeRecord record1 = nodeRecordList.get(index1);
            NodeRecord record2 = nodeRecordList.get(index2);
            nodeRecordList.set(index1, record2);
            nodeRecordList.set(index2, record1);
            nodeIndexMap.put(record1.node, index2);
            nodeIndexMap.put(record2.node, index1);
        }
    }

    private Dijkstra(){}
}
