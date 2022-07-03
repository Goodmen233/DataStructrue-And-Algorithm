package com.ccb;

import java.util.Objects;

/**
 * 单向链表的节点
 */
public class Node <T> {

    /** 值*/
    private T value;

    /** 指向的下一个指针*/
    private Node next;

    public Node() {}

    public Node(T value) {
        this.value = value;
    }

    /**
     * 根据传入的数组直接构造一个链表并返回
     * @param arr 数组
     * @return 链表的头
     */
    public Node(T[] arr){
        if(arr.length == 0){
            return;
        }
        this.value = arr[0];
        Node cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
            cur = cur.next;
        }
    }

    /**
     * 显示整个链表的值
     */
    public void show(){
        Node head = this;
        System.out.print("[");
        while(head != null){
            System.out.print(head.value);
            if((head = head.next) != null){
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    // -----------------------------getter and setter----------------------------------------------
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
