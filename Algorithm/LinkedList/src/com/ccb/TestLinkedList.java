package com.ccb;

import com.ccb.def.Node;
import com.ccb.partition.Partition;

public class TestLinkedList {
    public static void main(String[] args) {
        Node<Integer> head = new Node<Integer>(new Integer[]{1,99,3,4,12,6,8,9,0,13});
//        Node<Integer> head = new Node<Integer>(new Integer[]{23,34,56,78,56,8,9});
//        Node<Integer> head = new Node<Integer>(new Integer[]{1,2,3,4,5,6,7});
        head.show();
//        System.out.println("指向前一个：" + DoublePointer.quickSlowPointer1(head).getValue());
//        System.out.println("指向后一个：" + DoublePointer.quickSlowPointer2(head).getValue());
        Node<Integer> par = Partition.partition(head, new Node(7));
        par.show();

    }
}
