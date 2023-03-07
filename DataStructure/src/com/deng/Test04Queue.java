package com.deng;

/**
 * @Classname Test04Queue
 * @Description 队列
 * @Version 1.0.0
 * @Date 2023/3/7 10:20
 * @Created by helloDeng
 * 队列（queue）是一种线性数据结构，不同于栈的先入后出，队列中的元素只能先入先出（First In First Out，
 * 简称FIFO）。队列的出口端叫作队头（front），队列的入口端叫作队尾（rear）。
 *
 * 用数组实现时，为了入队操作的方便，把队尾位置规定为最后入队元素的下一个位置。
 *
 */
public class Test04Queue {
    public static void main(String[] args) throws Exception {

        QueueFromArray queue = new QueueFromArray(5);
        queue.enqueue(9);
        queue.enqueue(8);
        queue.enqueue(7);
        queue.dequeue();
        queue.enqueue(3);
        queue.output();
        System.out.println("========================================");
        QueueFromLinkedList queueFromLinkedList = new QueueFromLinkedList();
        queueFromLinkedList.enqueue(6);
        queueFromLinkedList.enqueue(7);
        queueFromLinkedList.enqueue(8);
        queueFromLinkedList.enqueue(9);
        queueFromLinkedList.dequeue();
        queueFromLinkedList.dequeue();
        queueFromLinkedList.enqueue(3);
        queueFromLinkedList.output();
    }
}

/**
 * 用数组实现时，为了入队操作的方便，把队尾位置规定为最后入队元素的下一个位置
 *
 * 用数组实现的队列可以采用循环队列的方式来维持队列容量的恒定。
 *
 *
 */
abstract class Queue{

    public abstract void enqueue(int data) throws Exception;
    public abstract int dequeue() throws Exception;
}

/**
 * 一直到（队尾下标+1）%数组长度=队头下标时，代表此队列真的已经满了。需要注意的是，
 * 队尾指针指向的位置永远空出1位，所以队列最大容量比数组长度小1。
 */
class QueueFromArray extends Queue{

    private int front;        //队头
    private int rear;         //对尾
    private int[]arr;
    private int size;

    public QueueFromArray(int size) {
        this.arr = new int[size];
        front = 0;
        rear = 0;
    }

    @Override
    public void enqueue(int data) throws Exception {   //入队操作
        if((rear + 1)%arr.length == front){
            throw new Exception("队列已满");
        }
        arr[rear] = data;
        rear = (rear + 1)%arr.length;
    }

    @Override
    public int dequeue() throws Exception {              //出队操作
        if(front == rear){
            throw new Exception("对列已空");
        }
        int temp = arr[front];
        front = (front+1)%arr.length;
        return temp;
    }
    public void output(){
        for (int i = front; i != rear ; i = (i+1)% arr.length) {
            System.out.println(arr[i]);
        }
    }
}

/**
 * 链表实现队列
 */
class QueueFromLinkedList extends Queue{
    private QueueNode front;
    private QueueNode last;


    @Override
    public void enqueue(int data) throws Exception {         //入队操作
        QueueNode queueNode = new QueueNode(data);
        if(front == null){
            front = queueNode;
            last = queueNode;
        }
        last.setNext(queueNode);
        last = queueNode;
    }

    @Override
    public int dequeue() throws Exception {             //出队操作
        if(front == null){
           throw new Exception("队列已空");
        }
        int data = front.getData();
        front = front.getNext();
        return data;
    }
    public void output(){
        QueueNode temp = front;
        while (temp != null){
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }
}
class QueueNode{
    private int data;
    private QueueNode next;

    public QueueNode(int data) {
        this.data = data;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }

    public QueueNode getNext() {
        return next;
    }

    public int getData() {
        return data;
    }
}