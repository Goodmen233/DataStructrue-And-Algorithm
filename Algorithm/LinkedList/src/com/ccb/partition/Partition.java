package com.ccb.partition;

import com.ccb.Node;

/**
 * 根据传入数值进行分区
 */
public class Partition {

    /**
     * 根据传入数值进行分区----只处理值是数值的链表
     * @param node 链表
     * @param target 目标节点
     * @return 分区的链表
     */
    public static Node partition(Node<Integer> node, Node<Integer> target){
        Node smallStart = null;
        Node smallEnd = null;
        Node equalStart = null;
        Node equalEnd = null;
        Node biggerStart = null;
        Node biggerEnd = null;
        while(node != null){
            if(node.getValue() < target.getValue()){
                if(smallStart == null){
                    smallStart = new Node(node.getValue());
                    smallEnd = smallStart;
                }else{
                    smallEnd.setNext(new Node(node.getValue()));
                    smallEnd = smallEnd.getNext();
                }
            }else if(node.getValue() > target.getValue()){
                biggerStart = biggerStart == null ? new Node(node.getValue()) : biggerStart;
                if(biggerEnd == null){
                    biggerEnd = biggerStart;
                }else{
                    biggerEnd.setNext(new Node(node.getValue()));
                    biggerEnd = biggerEnd.getNext();
                }
            }else{
                equalStart = equalStart == null ? new Node(node.getValue()) : equalStart;
                if(equalEnd == null){
                    equalEnd = equalStart;
                }else{
                    equalEnd.setNext(new Node(node.getValue()));
                    equalEnd = equalEnd.getNext();
                }
            }
            node = node.getNext();
        }

        if(smallStart != null){
            System.out.print("小于区域：");
            smallStart.show();
        }
        if(biggerStart != null){
            System.out.print("大于区域：");
            biggerStart.show();
        }
        if(equalStart != null){
            System.out.print("等于区域：");
            equalStart.show();
        }

        // 边界处理
//        Node res = smallStart;
//        if(smallEnd != null){
//            smallEnd.setNext(equalStart);
//        }else{
//            res = equalStart;
//        }
//        if(equalEnd != null){
//            equalEnd.setNext(biggerStart);
//        }else{
//            res = biggerStart;
//        }
//        return res;

        // 如果有小于区域，小于区域的下一个连接等于区域，等于区域的结束点如果为空(没有等于区域)设置为小于区域的结束点
        if(smallEnd != null){
            smallEnd.setNext(equalStart);
            equalEnd = equalEnd == null ? smallEnd : equalEnd;
        }
        if(equalEnd != null){
            equalEnd.setNext(biggerStart);
        }
        return smallStart != null ? smallStart : (equalStart != null ? equalStart : biggerStart);
    }

}
