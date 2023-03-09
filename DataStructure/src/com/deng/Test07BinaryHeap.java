package com.deng;

import java.util.Arrays;

/**
 * @Classname Test07BinaryHeap
 * @Description 二叉堆
 * @Version 1.0.0
 * @Date 2023/3/8 14:14
 * @Created by helloDeng
 * @author
 *
 * 二叉堆本质上是一种完全二叉树，它分为两个类型。
 * 最大堆的任何一个父节点的值，都大于或等于它左、右孩子节点的值。
 * 最小堆的任何一个父节点的值，都小于或等于它左、右孩子节点的值。
 * 二叉堆的根节点叫作堆顶,最大堆的堆顶是整个堆中的最大元素,最小堆的堆顶是整个堆中的最小元素。
 *
 *
 * 二叉堆虽然是一个完全二叉树，但它的存储方式并不是链式存储，
 * 而是顺序存储。换句话说，二叉堆的所有节点都存储在数组中。
 *
 * 二叉堆是实现堆排序及优先队列的基础。
 */
public class Test07BinaryHeap {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,2,6,5,7,8,9,10,0};
        BinaryHeap.upAdjust(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("======================");

        int[] array = new int[]{7,1,3,10,5,2,8,9,6};
        BinaryHeap.buildHeap(array);
        System.out.println(Arrays.toString(array));
    }
}

/**
 * 最小堆
 */
class BinaryHeap{
    /**
     * "上浮调整"
     * @param array      待调整的堆
     */
    public static void upAdjust(int[] array){
        int childIndex = array.length - 1;
        int parentIndex = (childIndex -1)/2;
        //temp保存插入的叶子节点值，用于最后的赋值
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]){
            //无需真正的交换，单项赋值即可
            array[childIndex]  = array[parentIndex];  //把父节点的值赋给当前子节点的位置
            childIndex = parentIndex;                 //因为temp中保存着子节点的值，所以直接把父节点的位置赋给子节点
            parentIndex = (parentIndex - 1) / 2;      //父节点位置变为原来父节点的父节点，保持"上浮"的比较
        }
        array[childIndex] = temp;
    }

    /**
     * "下沉调整"
     * @param array                  待调整的堆
     * @param parentIndex            要"下沉"的父节点
     * @param lenght                 堆的有效大小
     */
    public static void downAdjust(int[] array,int parentIndex,int lenght){
        //temp保存父节点的值，用于最后的赋值
        int temp = array[parentIndex];
        int childIndex = parentIndex * 2 + 1;
        while (childIndex < lenght){
            //如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            if(childIndex + 1 < lenght && array[childIndex + 1] < array[childIndex]){
                childIndex++;
            }
            //如果父节点小于任何一个子节点的值，退出调整
            if(temp < array[childIndex]){
                break;
            }
            //无需真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex * 2 + 1;
        }
        array[parentIndex] = temp;
    }
    public static void buildHeap(int [] array){

        //从最后一个非叶子节点开始，依次做"下沉"调整
        for (int i = (array.length - 2) / 2; i >= 0 ; i--) {
            downAdjust(array,i, array.length);
        }
    }

}
