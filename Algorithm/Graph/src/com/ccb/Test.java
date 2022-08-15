package com.ccb;

import com.ccb.change.MatrixToGraph;
import com.ccb.def.Graph;
import com.ccb.minpath.Dijkstra;
import com.ccb.mst.MultipleSpanningTree;
import com.ccb.sort.TopologicalSort;
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
//        testSort();
//        testDijkstra();
        testMst();
    }

    public static void testMst() {
        Integer[][] matrix = new Integer[][]{
                {0,1,17},
                {1,0,17},
                {1,2,6},
                {2,1,6},
                {2,3,10},
                {3,2,10},
                {1,3,5},
                {3,1,5},
                {3,4,4},
                {4,3,4},
                {0,4,16},
                {4,0,16},
                {0,5,1},
                {5,0,1},
                {1,5,11},
                {5,1,11},
                {4,5,33},
                {5,4,33},
                {5,3,14},
                {3,5,14},
                {6,7,67}
        };
        Graph graph0 = MatrixToGraph.change0(matrix);
        System.out.println(graph0);
        System.out.println(MultipleSpanningTree.prim(graph0));
        System.out.println(MultipleSpanningTree.Kruskal(graph0));
    }

    public static void testDijkstra() {
        Integer[][] matrix = new Integer[][]{
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
        System.out.println(Dijkstra.find(graph0.nodes.get(0)));
    }

    /**
     * check
     */
    public static void testSort() {
        Integer[][] matrix = new Integer[][]{
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
//        TopologicalSort.sort(graph0);
        TopologicalSort.sort0(graph0);
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
