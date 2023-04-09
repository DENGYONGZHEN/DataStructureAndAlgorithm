package com.deng.interview;

import java.util.Stack;

/**
 * @Classname Test08MinimumStack
 * @Description 最小栈
 * @Version 1.0.0
 * @Date 2023/3/13 17:01
 * @Created by helloDeng
 * @author
 */
public class Test08MinimumStack {
    public static void main(String[] args) throws Exception {
        MiniStack miniStack = new MiniStack();
        miniStack.push(4);
        miniStack.push(9);
        miniStack.push(7);
        miniStack.push(3);
        miniStack.push(8);
        miniStack.push(5);
        System.out.println(miniStack.getMin());
        miniStack.pop();
        miniStack.pop();
        miniStack.pop();
        System.out.println(miniStack.getMin());
    }
}

class MiniStack{

    private Stack<Integer> mainStack = new Stack<Integer>();
    private Stack<Integer> minStack = new Stack<Integer>();

    /**
     * 入栈操作
     * @param element 入栈的元素
     */
    public void push(int element){

        mainStack.push(element);
        //如果辅助栈为空，或者新元素小于或等于辅助栈栈顶，则将新元素压入辅助栈
        if(minStack.empty() || element <= minStack.peek()){
            minStack.push(element);
        }
    }

    /**
     * 出栈操作
     * @return 返回出栈的元素
     */
    public Integer pop(){
        //如果出栈元素和辅助栈的栈顶元素值相等，辅助栈的栈顶元素也出栈
        if(mainStack.peek().equals(minStack.peek())){
            minStack.pop();
        }
        return mainStack.pop();
    }

    /**
     * 获取栈的最小元素
     * @return
     * @throws Exception
     */
    public int getMin() throws Exception {
        if(mainStack.empty()){
            throw new Exception("stack is empty");
        }
        return minStack.peek();
    }
}
