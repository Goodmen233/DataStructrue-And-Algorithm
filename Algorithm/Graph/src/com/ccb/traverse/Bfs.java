package com.ccb.traverse;

import com.ccb.def.Graph;
import com.ccb.def.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先遍历
 */
public class Bfs {

    /**
     * 队列实现
     * 如果图不连通，加层for--暂未写
     * @param graph 图
     */
    public static void bfs(Graph graph, Integer startNum) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] isVisit = new boolean[graph.nodes.size()];
        Node node = graph.nodes.get(startNum);
        queue.add(node);
        while (! queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                // 处理节点
                System.out.println(node);
                isVisit[node.value] = true;
                // 将当前节点所有子节点加入队列
                for (int j = 0; j < node.nexts.size(); j++) {
                    if (isVisit[node.nexts.get(j).value] || queue.contains(node.nexts.get(j))) {
                        continue;
                    }
                    queue.add(node.nexts.get(j));
                }
            }
        }
    }

    private Bfs() {}
}
