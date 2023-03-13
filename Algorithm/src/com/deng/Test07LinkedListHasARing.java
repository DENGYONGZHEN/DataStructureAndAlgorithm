package com.deng;

/**
 * @Classname Test07LinkedListHasARing
 * @Description 测试链表是否有环
 * @Version 1.0.0
 * @Date 2023/3/13 14:24
 * @Created by helloDeng
 * @author
 *
 * 创建两个指针p1和p2（在Java里就是两个对象引用），让它们同时指向这个链表的头节点。然后
 * 开始一个大循环，在循环体中，让指针p1每次向后移动1个节点，让指针p2每次向后移动2个节点，然后比
 * 较两个指针指向的节点是否相同。如果相同，则可以判断出链表有环，如果不同，则继续下一次循环。
 */
public class Test07LinkedListHasARing {
    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;

        System.out.println(isCycle(node1));
        System.out.println(isCycle2(node1));
        System.out.println(isCycle3(node1).getData());
    }

    /**
     * 是否有环
     * @param head 头节点
     * @return
     */
    public static boolean isCycle(Node head){
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
               return true;
            }
        }
        return false;
    }

    /**
     * 若有环，计算环长度
     * @param head
     * @return
     * 当两个指针首次相遇，证明链表有环的时候，让两个指针从相遇点继续循环前进，并统计前进的循环
     * 次数，直到两个指针第2次相遇。此时，统计出来的前 进次数就是环长。
     */
    public static int isCycle2(Node head){
        Node p1 = head;
        Node p2 = head;
        int length = 0;              //存储环长
        boolean b = false;
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(b){
                length++;
                if(p1 == p2){
                    break;
                }
            }
            if(p1 == p2){
               b = true;
            }

        }
        return length;
    }

    /**
     * 如果有环，获取入环节点
     * @param head
     * 当两个指针首次相遇时，各自所走的距离是多少呢？
     * 指针p1一次只走1步，所走的距离是D+S1。
     * 指针p2一次走2步，多走了n（n＞=1）整圈，所走的距离是D+S1+n（S1+S2）。
     * 由于p2的速度是p1的2倍，所以所走距离也是p1的2倍
     * 2(D+S1)=D+S1+n(S1+S2)
     * D=(n-1)(S1+S2)+S2
     *从链表头结点到入环点的距离，等于从首次相遇点绕环n-1圈再回到入环点的距离。
     *
     * 只要把其中一个指针放回到头节点位置，另一个指针保持在首次相遇点，两个指针都是每
     * 次向前走1步。那么，它们最终相遇的节点，就是入环节点。
     */
    public static Node isCycle3(Node head){
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                p1 = head;   //走的慢的回到头节点
                break;
            }
        }
        while (p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
       return p1;
    }
}
class Node{
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}