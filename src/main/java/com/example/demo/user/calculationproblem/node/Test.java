package com.example.demo.user.calculationproblem.node;

public class Test {

    // 节点类
    static class Node {

        // 数值域
        public int data;

        // 引用类型
        public Node next;

        // 链表
        public Node(int data) {
            this.data = data;
        }
    }

    public static class MyLinkedList {

        // 标识单链表的头节点
        public Node head;

        //穷举的方式创建链表
        public void createList() {

            // 创建链表
            Node first = new Node(13);
            Node second = new Node(2);
            Node third = new Node(5);

            first.next = second;
            second.next = third;

            this.head = first;

        }

        @org.junit.Test
        public void show(){
            //打印链表
             while(this.head!=null){
                 System.out.print(this.head.data+" ");
                 this.head=this.head.next;
             }
        }
    }

}
