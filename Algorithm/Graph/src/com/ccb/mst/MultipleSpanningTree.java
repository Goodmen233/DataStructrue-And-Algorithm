package com.ccb.mst;

import com.ccb.def.Edge;
import com.ccb.def.Graph;

import java.util.HashSet;
import java.util.Set;

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

        return res;
    }
}
