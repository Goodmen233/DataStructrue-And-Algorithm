package com.ccb;

import com.ccb.change.MatrixToGraph;
import com.ccb.def.Graph;
import com.ccb.traverse.Bfs;
import com.ccb.traverse.Dfs;

/**
 * 图：
 * 1、DFS、BFS
 * 2、最小生成树：Prim，Kruskal
 * 3、最短路径：Dijkstra
 * 4、拓扑排序
 * 5、关键路径？？？？
 */
public class Test {
    public static void main(String[] args) {
//        testMatrixToGraph();
//        testDfs();
//        testBfs();

    }

    /**
     * check
     */
    public static void testBfs() {
        Integer[][] matrix = new Integer[][]{
                {0,0,1},
                {0,1,3},
                {0,2,4},
                {1,3,10},
                {2,3,7},
                {3,4,100},
                {5,6,56},
                {6,7,67}
        };
        Graph graph0 = MatrixToGraph.change0(matrix);
        System.out.println(graph0);
        Bfs.bfs(graph0, 0);
    }


    /**
     * check
     */
    public static void testDfs() {
        Integer[][] matrix0 = new Integer[][]{
                {0,0,1},
                {0,1,3},
                {0,2,4},
                {1,3,10},
                {2,3,7},
                {3,4,100},
                {5,6,56}
        };
        Graph graph0 = MatrixToGraph.change0(matrix0);
        System.out.println(graph0);
        Dfs.dfs(graph0, 0);
    }

    /**
     * check
     */
    public static void testMatrixToGraph() {
        Integer[][] matrix = new Integer[][]{
                {1,3,4,null,null},
                {null,null,null,10,null},
                {null,null,null,7,null},
                {null,null,null,null,100},
                {null,null,null,null,null}
        };
        Graph graph = MatrixToGraph.change(matrix);
        Integer[][] matrix0 = new Integer[][]{
                {0,0,1},
                {0,1,3},
                {0,2,4},
                {1,3,10},
                {2,3,7},
                {3,4,100}
        };
        Graph graph0 = MatrixToGraph.change0(matrix0);
        System.out.println(graph);
        System.out.println(graph0);
    }

}
