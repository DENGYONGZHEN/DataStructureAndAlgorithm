package com.deng;

/**
 * @Classname Test02LinkedList
 * @Description 链表
 * @Version 1.0.0
 * @Date 2023/3/6 12:29
 * @Created by helloDeng
 *
 * 链表（linked list）是一种在物理上非连续、非顺序的数据结构，由若干节点（node）所组成。
 *
 */
public class Test02LinkedList {
    public static void main(String[] args) {

        //单项链表
        OneWayLinkedList linkedList = new OneWayLinkedList();
        linkedList.insert(5,0);
        linkedList.insert(6,1);
        linkedList.insert(7,2);
        linkedList.insert(8,3);
        linkedList.output();
        System.out.println("");
        linkedList.insert(2,0);
        linkedList.remove(1);
        linkedList.output();

        System.out.println("");
        System.out.println("=====================================================");
        //双向链表
        BidirectionalLinkedList bidirectionalLinkedList = new BidirectionalLinkedList();
        bidirectionalLinkedList.insert(9,0);
        bidirectionalLinkedList.insert(8,1);
        bidirectionalLinkedList.insert(7,2);
        bidirectionalLinkedList.insert(6,3);
        bidirectionalLinkedList.insert(5,4);
        bidirectionalLinkedList.output();
        System.out.println("");
        bidirectionalLinkedList.removeNode(3);
        bidirectionalLinkedList.output();
    }
}

class OneWayLinkedList{
    private Node head;    //头节点
    private Node last;    //尾节点
    private int size;     //链表实际长度

    public OneWayLinkedList() {
    }

    /**
     * 插入节点
     * @param data         节点数据
     * @param index        插入的地址索引
     */
    public void insert(int data,int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node insertNode = new Node(data);
        if(size == 0){           //空链表
           head = insertNode;
           last = insertNode;
        } else if (index == 0) {   //插入头部
            insertNode.setNext(head);
            head = insertNode;
        } else if (index == size) { //插入尾部
            last.setNext(insertNode);
            last = insertNode;
        }else {                     //插入中间
            Node preNode = getNode(index - 1);
            insertNode.setNext(preNode.getNext());
            preNode.setNext(insertNode);
        }
        size++;
    }

    /**
     * 删除节点
     * @param index   要删除的节点所在的索引
     * @return
     */
    public Node remove(int index){

        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("超出链表节点范围！");
        }
        Node removeNode = null;
        if(index == 0){                    //删除头节点
            removeNode = head;
            head = head.getNext();
        }else if (index == size - 1){       //删除尾节点
            Node preLast = getNode(index - 1);
            removeNode = last;
            preLast.setNext(null);
            last = preLast;
        }else {                            //删除中间节点
            Node preNode = getNode(index - 1);
            removeNode = preNode.getNext();
            preNode.setNext(preNode.getNext().getNext());
        }
        size--;
        return removeNode;
    }

    private Node getNode(int index) {       //获取节点，索引从0开始，获取index处的节点
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("超出链表节点范围！");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp;

    }

    /**
     * 输出链表
     */
    public void output(){
        Node temp = head;
        while (temp != null){
            System.out.print(temp.getData());
            temp = temp.getNext();
        }
    }
}

//双向链表
class BidirectionalLinkedList{

    private Node head;
    private Node last;
    private int size;

    /**
     * 插入数据
     * @param data
     * @param index
     */
    public void insert(int data,int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node insertNode = new Node(data);
        if(size == 0){                //空链表时的插入
            head = insertNode;
            last = insertNode;
        } else if (index == 0) {      //插入链表头
            head.setPre(insertNode);
            insertNode.setNext(head);
            head = insertNode;
        } else if (index == size) {    //插入链表尾
            last.setNext(insertNode);
            insertNode.setPre(last);
            last = insertNode;
        }else {                        //插入链表中间
            Node preNode = getNode(index - 1);
            insertNode.setNext(preNode.getNext());
            preNode.getNext().setPre(insertNode);
            preNode.setNext(insertNode);
            insertNode.setPre(preNode);
        }
        size++;
    }

    /**
     * 删除节点并返回被删的节点
     * @param index
     * @return
     */
    public Node removeNode(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("超出链表索引范围");
        }
        Node removeNode;
        if(index == 0){                   //删除头节点
            removeNode = head;
            head.getNext().setPre(null);
            head = head.getNext();
        } else if (index == size) {       //删除尾节点
            removeNode = last;
            last.getPre().setNext(null);
            last = last.getPre();
        }else {                           //删除中间节点
            Node preNode = getNode(index - 1);
            removeNode = preNode.getNext();
            preNode.getNext().getNext().setPre(preNode);
            preNode.setNext(preNode.getNext().getNext());
        }
        size--;
        return removeNode;
    }

    /**
     * 获取节点
     * @param index   要获取的节点的索引
     * @return
     */
    private Node getNode(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("超出链表索引范围");
        }
        Node node = null;
        if(index <= size/2){                    //当索引在整个链表的前半部分时，从前往后遍历
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
            return node;
        }else {
            node = last;                      //反之，从后向前遍历
            for (int i = size - 1; i >index ; i--) {
                node = node.getPre();
            }
            return node;
        }
    }

    public void output() {
        Node temp = head;
        while (temp != null){
            System.out.print(temp.getData());
            temp = temp.getNext();
        }
    }
}


class Node{                     //节点类
    private int data;

    private Node pre;
    private Node next;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public Node getPre() {
        return pre;
    }
}