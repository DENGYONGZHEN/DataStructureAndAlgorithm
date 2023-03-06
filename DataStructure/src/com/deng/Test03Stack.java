package com.deng;

/**
 * @Classname Test03Stack
 * @Description 栈
 * @Version 1.0.0
 * @Date 2023/3/6 14:59
 * @Created by helloDeng
 * 栈和队列，这两者都属于逻辑结构，它们的物理实现既可以利用数组，也可以利用链表来完成。
 *
 *栈（stack）是一种线性数据结构栈中的元素只能先入后出（First In Last Out，简称FILO）。最早进入
 * 的元素存放的位置叫作栈底（bottom），最后进入的元素存放的位置叫作栈顶（top）。
 */
public class Test03Stack {
    public static void main(String[] args) {

        StackFromArray stackFromArray = new StackFromArray(10);
        stackFromArray.push(10);
        stackFromArray.push(9);
        stackFromArray.push(8);
        stackFromArray.push(7);
        stackFromArray.pop();
        stackFromArray.push(2);
        stackFromArray.output();

        System.out.println("===========================================");
        StackFromLinkedList stack = new StackFromLinkedList();
        stack.push(10);
        stack.push(9);
        stack.push(8);
        stack.pop();
        stack.push(4);
        stack.output();
    }
}
//栈的抽象类
abstract class Stack{
    protected Stack top; //栈顶
    public abstract void push(int data);
    public abstract int pop();
}

/**
 * 数组实现栈
 */
class StackFromArray extends Stack{

    private int[] arr;
    private int top;   //栈顶,实际栈顶元素的上一格
    public StackFromArray(int size){
        arr = new int[size];
        top = 0;
    }
    @Override
    public void push(int data) {              //入栈
        arr[top] = data;
        top++;
    }

    @Override
    public int pop() {                       //出栈
        //System.out.println(top);
        int temp = arr[top - 1];
        //System.out.println(temp);
        top--;
        return temp;
    }
    public void output(){
        for (int i = top - 1; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }
}

/**
 * 列表实现栈
 */
class StackFromLinkedList extends Stack{

    private NodeEntity top;
    @Override
    public void push(int data) {                //入栈
        NodeEntity nodeEntity = new NodeEntity(data);
        if(top == null){
            top = nodeEntity;
        }else {
            nodeEntity.setPre(top);
            top = nodeEntity;
        }
    }

    @Override
    public int pop() {                          //出栈
        int temp = top.getData();
        top = top.getPre();
        return temp;
    }

    public void output() {
        NodeEntity temp = top;
        while (temp != null){
            System.out.println(temp);
            temp = temp.getPre();
        }
    }
}
class NodeEntity{
    private int data;
    private NodeEntity pre;

    public NodeEntity(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setPre(NodeEntity pre) {
        this.pre = pre;
    }

    public NodeEntity getPre() {
        return pre;
    }

    @Override
    public String toString() {
        return "NodeEntity{" +
                "data=" + data +
                '}';
    }
}