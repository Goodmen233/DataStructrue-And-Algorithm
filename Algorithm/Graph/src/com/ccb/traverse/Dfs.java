package com.ccb.traverse;

import com.ccb.def.Graph;
import com.ccb.def.Node;

/**
 * 深度优先遍历
 */
public class Dfs {

    /**
     * 递归实现
     * @param graph 图
     * @param startNum 开始点
     */
    public static void dfs(Graph graph, Integer startNum) {
        boolean[] isVisit = new boolean[graph.nodes.size()];
        // 为了防止图不连通，多加一层for来全部遍历
        for (int i = 0; i < graph.nodes.size(); i++) {
            if (isVisit[startNum]) {
                startNum++;
                continue;
            }
            dfs(graph, startNum++, isVisit);
            if(startNum >= graph.nodes.size()) {
                startNum %= graph.nodes.size();
            }
        }
    }

    /**
     * 递归实现
     * @param graph 图
     * @param startNum 起始点
     * @param isVisit 访问标识数组
     */
    public static void dfs(Graph graph, Integer startNum, boolean[] isVisit) {
        Node start = graph.nodes.get(startNum);
        // 处理节点
        isVisit[startNum] = true;
        System.out.println(start + " ");
        for (int i = 0; i < start.nexts.size(); i++) {
            if (! isVisit[start.nexts.get(i).value]) {
                dfs(graph, start.nexts.get(i).value, isVisit);
            }
        }
    }



    private Dfs() {}
}
