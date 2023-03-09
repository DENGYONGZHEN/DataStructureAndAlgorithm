package com.deng;

import java.util.Arrays;


/**
 * @Classname Test08PriorityQueue
 * @Description 优先队列    用二叉堆实现
 * @Version 1.0.0
 * @Date 2023/3/9 12:13
 * @Created by helloDeng
 * @author
 *
 * 最大优先队列，无论入队顺序如何，都是当前最大的元素优先出队
 * 最小优先队列，无论入队顺序如何，都是当前最小的元素优先出队
 *
 * 用最大堆来实现最大优先队列，这样的话，每一次入队操作就是堆的插入操作，每一次出队操作就是删除堆顶节点
 */
public class Test08PriorityQueue {
    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);
        System.out.println("出队元素" + priorityQueue.deQueue());
        System.out.println("出队元素" + priorityQueue.deQueue());
    }
}

class PriorityQueue{
    private int[] arr;
    private int size;

    public PriorityQueue() {
        //队列初始长度为32
        arr = new int[32];
    }

    /**
     * 入队操作，在数组最后一个位置添加数据，然后比较大小，进行上浮调整，形成最终最大堆的方法
     * @param data
     */
    public void enQueue(int data){
        //如果下一个存储的索引超出范围，扩容
        if(size >= arr.length){
            resize();
        }
        arr[size++] = data;  //先赋值，然后size再自增
        upAdjust();
    }
    public int deQueue() throws Exception {
        if(size <= 0){
            throw new Exception("the Queue is empty");
        }
        //获取堆顶元素
        int head = arr[0];
        //让最后一个元素移到堆顶
        arr[0] = arr[--size];
        downAdjust();
        return head;
    }

    /**
     * 数组扩容
     */
    private void resize() {
        /* 第一种写法
        int[]array = new int[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }
        arr = array;*/
        //第二种写法
        int newSize = size * 2;
        this.arr = Arrays.copyOf(this.arr,newSize);
    }
    /**
     * "上浮调整"
     */
    public void upAdjust(){     //最大堆时的上浮
        int childIndex = size - 1;
        int parentIndex = (childIndex -1)/2;
        //temp保存插入的叶子节点值，用于最后的赋值
        int temp = arr[childIndex];
        while (childIndex > 0 && temp > arr[parentIndex]){
            //无需真正的交换，单项赋值即可
            arr[childIndex]  = arr[parentIndex];  //把父节点的值赋给当前子节点的位置
            childIndex = parentIndex;                 //因为temp中保存着子节点的值，所以直接把父节点的位置赋给子节点
            parentIndex = (parentIndex - 1) / 2;      //父节点位置变为原来父节点的父节点，保持"上浮"的比较
        }
        arr[childIndex] = temp;
    }
    /**
     * "下沉调整"     最大堆的下沉调整
     */
    public void downAdjust(){
        //temp保存父节点的值，用于最后的赋值
        int parentIndex = 0;
        int temp = arr[parentIndex];
        int childIndex = parentIndex * 2 + 1;
        while (childIndex < size){
            //如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if(childIndex + 1 < size && arr[childIndex + 1] > arr[childIndex]){
                childIndex++;
            }
            //如果父节点大于等于任何一个子节点的值，退出调整
            if(temp >= arr[childIndex]){
                break;
            }
            //无需真正交换，单向赋值即可
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex * 2 + 1;
        }
        arr[parentIndex] = temp;
    }
}