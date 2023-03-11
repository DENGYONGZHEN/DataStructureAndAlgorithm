package com.deng;


import java.util.Arrays;

/**
 * @Classname Test04HeapSort
 * @Description 堆排序
 * @Version 1.0.0
 * @Date 2023/3/10 17:28
 * @Created by helloDeng
 * @author
 *
 *
 * 1.把无序数组构建成二叉堆。需要从小到大排序，则构建成最大堆；需要从大到小排序，则构建成最小堆。
 *
 * 2.循环删除堆顶元素，替换到二叉堆的末尾，调整堆产生新的堆顶。
 *
 *
 * 堆排序和快速排序的平均时间复杂度都是O（nlogn），并且都是不稳定排序。
 * 至于不同点，快速排序的最坏时间复杂度是O（n2），
 * 而堆排序的最坏时间复杂度稳定在O（nlogn）。
 *
 * 快速排序递归和非递归方法的平均空间复杂度都是O （ logn ） ，而堆排序的空间复杂度是O（1）
 */
public class Test04HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,2,6,5,7,8,9,10,0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * "下沉"调整           构建最大堆
     * @param arr               待调整的堆
     * @param parentIndex       要"下沉"的父节点
     * @param length            堆的有效大小
     */
    public static void downAdjust(int[]arr,int parentIndex,int length){

        //temp保存父节点的值，用于最后的赋值
        int temp = arr[parentIndex];
        int childIndex = 2 * parentIndex + 1;           //记录左孩子索引
        while (childIndex < length){
            //如果存在右孩子，并且右孩子的值大于左孩子，就记录右孩子的索引
            if(childIndex + 1 < length &&arr[childIndex] < arr[childIndex + 1]){
                childIndex++;
            }
            //当父节点大于任何一个孩子的值，直接跳出循环
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

    /**
     *  堆排序         按从小到大排
     * @param arr     无序数组
     */
    public static void heapSort(int arr[]){
        //构建最大堆
        for (int i = (arr.length - 2) / 2 ; i >= 0 ; i--) {
            downAdjust(arr,i,arr.length);
        }
        System.out.println(Arrays.toString(arr));
        //依次删除堆顶元素，移到集合尾部，调整堆产生新的堆顶
        for (int i = arr.length - 1; i > 0 ; i--) {
            //最后一个元素和第一个交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //"下沉"，调整为最大堆,调整堆时，最后一个元素，也就是确定的最大元素不再参与调整
            downAdjust(arr,0,i);   //所以长度为i，参与调整的随着元素的确定而减少
        }
    }

}
