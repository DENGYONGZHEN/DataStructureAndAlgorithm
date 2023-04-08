package com.deng;

import java.util.Stack;

/**
 * @Classname Test12StackImplementsQueue
 * @Description 用栈实现队列
 * @Version 1.0.0
 * @Date 2023/4/8 13:40
 * @Created by helloDeng
 * @author
 */
public class Test12StackImplementsQueue {

    public static void main(String[] args) {

        StackQueue<Integer> stackQueue = new StackQueue();
        stackQueue.enQueue(1);
        stackQueue.enQueue(2);
        stackQueue.enQueue(3);
        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());
        stackQueue.enQueue(4);
        System.out.println(stackQueue.deQueue());
    }



}

/**
 * 用栈实现的队列
 */
class StackQueue<Integer>{
    private Stack<Integer> stackIn = new Stack<>();  //元素 入栈时的栈
    private Stack<Integer> stackOut = new Stack<>(); //元素 出栈时的栈

    /**
     * 入队操作
     * @param element
     */
    public void enQueue(Integer element){
        stackIn.push(element);
    }

    /**
     * 出队操作，如果出队的栈为空，就从入队的栈取数据
     * @return
     */
    public Integer deQueue(){
        if(stackOut.isEmpty()){
            if(stackIn.isEmpty()){
                return null;
            }
            transfer();
        }
        return stackOut.pop();
    }

    /**
     * 数据转移操作，只有当stackOut是空栈时才执行
     */
    private void transfer() {
        while (!stackIn.isEmpty()){
            stackOut.push(stackIn.pop());
        }
    }
}
