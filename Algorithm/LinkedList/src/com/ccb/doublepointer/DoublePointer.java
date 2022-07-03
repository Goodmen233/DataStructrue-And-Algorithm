package com.ccb.doublepointer;

import com.ccb.Node;

/**
 * 双指针工具类
 */
public class DoublePointer {

    /**
     * 快慢指针-指向中点，如果是偶数，指向前一个
     * @param node 链表
     * @return 中点的指针
     */
    public static Node quickSlowPointer1(Node node){
        Node slow = node;
        Node quick = node;
        while(quick != null && quick.getNext() != null && quick.getNext().getNext() != null){
            slow = slow.getNext();
            quick = quick.getNext().getNext();
        }
        return slow;
    }

    /**
     * 快慢指针-指向中点，如果是偶数，指向后一个
     * @param node 链表
     * @return 中点的指针
     */
    public static Node quickSlowPointer2(Node node){
        Node slow = node;
        Node quick = node;
        while(quick != null && quick.getNext() != null){
            slow = slow.getNext();
            quick = quick.getNext().getNext();
        }
        return slow;
    }
}
