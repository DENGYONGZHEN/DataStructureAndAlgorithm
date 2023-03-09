package com.deng;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Classname Test06BinaryTree
 * @Description    二叉树
 * @Version 1.0.0
 * @Date 2023/3/7 22:07
 * @Created by helloDeng
 * @author deng
 */
public class Test06BinaryTree {
    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<Integer>(Arrays.asList
                                                  (new Integer[]{3,2,9,null,null,10,null,null,8,null,4}));
        MyTreeNode treeNode = createBinaryTree(inputList);
        System.out.println("前序遍历");
        preOrderTravel(treeNode);
        System.out.println("中序遍历");
        midOrderTravel(treeNode);
        System.out.println("后序遍历");
        postOrderTravel(treeNode);
    }

    /**
     * 构建二叉树
     * @param inputList     输入序列
     * @return
     */
    public static MyTreeNode createBinaryTree(LinkedList<Integer> inputList){
        MyTreeNode node = null;
        if(inputList == null ||inputList.isEmpty()){
            return null;
        }
        Integer data = inputList.removeFirst();
        if(data != null){              //当递归到data为null时，会弹栈，退出当前方法，运行上一个递归方法
            node = new MyTreeNode(data);
            node.setLeftChild(createBinaryTree(inputList));
            node.setRightChild(createBinaryTree(inputList));
        }
        return node;
    }

    /**
     * 二叉树前序遍历       及根节点最先输出
     * @param node 二叉树节点
     */
    public static void preOrderTravel(MyTreeNode node) {
        if(node == null){
            return;
        }
        System.out.println(node.getData());
        preOrderTravel(node.getLeftChild());
        preOrderTravel(node.getRightChild());
    }

    /**
     * 二叉树 中序遍历               即根节点在中间输出
     * @param node 二叉树节点
     */
    public static void midOrderTravel(MyTreeNode node){
        if(node == null){
           return;
        }
        midOrderTravel(node.getLeftChild());
        System.out.println(node.getData());
        midOrderTravel(node.getRightChild());
    }

    /**
     * 二叉树   后序遍历        即根节点最后输出
     * @param node      二叉树节点
     */
    public static void postOrderTravel(MyTreeNode node){
        if(node == null){
            return;
        }
        postOrderTravel(node.getLeftChild());
        postOrderTravel(node.getRightChild());
        System.out.println(node.getData());
    }

    /**
     * 二叉树非递归前序遍历   用栈遍历
     * @param root   二叉树根节点
     */
    public static void preOrderTravelWithStack(MyTreeNode root){
        Stack<MyTreeNode> stack = new Stack<>();
        MyTreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()){
            //迭代访问节点的左孩子，并入栈
            while (treeNode != null){
                System.out.println(treeNode.getData());
                stack.push(treeNode);
                treeNode = treeNode.getLeftChild();
            }
            if(!stack.isEmpty()){               //如果节点没有左孩子，则弹出栈顶结点，访问节点右孩子
                treeNode = stack.pop();
                treeNode = treeNode.getRightChild();
            }
        }

    }

    /**
     * 二叉树层序遍历            利用队列
     * @param root 二叉树根节点
     */
    public static void levelOrderTravel(MyTreeNode root){
        Queue<MyTreeNode> queue = new LinkedList<>();
        //Inserts the specified element into this queue if it is possible to do
        // so immediately without violating capacity restrictions.
        queue.offer(root);
        while (!queue.isEmpty()){
            MyTreeNode node = queue.poll();     //Retrieves and removes the head of this queue, or returns null if this queue is empty.
            System.out.println(node.getData());
            if(node.getLeftChild() != null){
                queue.offer(node.getLeftChild());
            }
            if(node.getRightChild() != null){
                queue.offer(node.getRightChild());
            }
        }
    }
}
class MyTreeNode{

    private Integer data;
    private MyTreeNode leftChild;
    private MyTreeNode rightChild;

    public MyTreeNode(Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public MyTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(MyTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public MyTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(MyTreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
