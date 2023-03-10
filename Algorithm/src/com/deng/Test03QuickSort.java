package com.deng;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Classname Test03QuickSort
 * @Description 快速排序
 * @Version 1.0.0
 * @Date 2023/3/9 13:51
 * @Created by helloDeng
 * @author
 * 同冒泡排序一样，快速排序也属于交换排序，通过元素之间的比较和交换位置来达到排序的目的。
 *
 * 不同的是，冒泡排序在每一轮中只把1个元素冒泡到数列的一端，而快速排序则在每一轮挑选一个基准元素，
 * 并让其他比它大的元素移动到数列一边，比它小的元素移动到数列的另一边，从而把数列拆解成两个部分
 * 这种思路就叫作分治法。
 * 基准元素，英文是pivot
 * 快 速 排 序 的 平 均 时 间 复 杂 度 是O（nlogn），
 * 在极端情况下，快速排序需要进行n轮，时间复杂度退化成了O（n2）。
 * 可以随机选择一个元素作为基准元素，并且让基准元素和数列首元素交换位置
 *
 * 这里直接把首元素作为基准元素
 */
public class Test03QuickSort {
    public static void main(String[] args) {
        int[]arr = new int[]{4,4,6,5,3,2,8,1};
//        QuickSort.sort(arr,0,arr.length - 1);                 //基于递归的双边循环发
//        System.out.println(Arrays.toString(arr));
//        QuickSort.sort2(arr,0,arr.length - 1);                 //基于递归的单边循环法
//        System.out.println(Arrays.toString(arr));
        QuickSort.sort3(arr,0,arr.length - 1);  //基于栈的单边循环法
        System.out.println(Arrays.toString(arr));

    }
}

class QuickSort{
    /**
     * 1.双边循环法。   基于递归实现
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    public static void sort(int[]arr,int startIndex,int endIndex){
        //递归结束条件：startIndex大于或等于endIndex时
        if(startIndex >= endIndex){
            return;
        }
        //得到基准元素位置
        int pivotIndex = partition(arr,startIndex,endIndex);
        //根据基准元素，分成两部分进行递归排序
        sort(arr,startIndex,pivotIndex - 1);
        sort(arr,pivotIndex + 1,endIndex);
    }

    /**
     * 分治（双边循环法）
     * @param arr        待交换的数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     * @return
     */
    private static int partition(int[] arr, int startIndex, int endIndex) {
        //取第一个位置（也可以选择随机位置）的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right){
            //控制right指针比较并右移
            while (left < right && arr[right] > pivot){
                right--;
            }
            //控制left指针比较并右移
            while (left < right && arr[left] <= pivot){
                left++;
            }
            //交换left和right指针所指向的元素
            if(left < right){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        //pivot指向的元素和 left和right两个指针重合点指向的元素进行交换
        arr[startIndex] = arr[left];
        arr[left] = pivot;
        return left;
    }

    /**
     * 2.基于递归实现的单边循环法
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    public static void sort2(int[]arr,int startIndex,int endIndex){
        //递归结束条件：startIndex大于或等于endIndex时
        if(startIndex >= endIndex){
            return;
        }
        int pivotIndex = partition2(arr,startIndex,endIndex);
        sort2(arr,startIndex,pivotIndex - 1);
        sort2(arr,pivotIndex + 1,endIndex);

    }

    /**
     * 分治，单边循环法
     * @param arr
     * @param startIndex
     * @param endIndex
     * @return
     */
    private static int partition2(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int mark = startIndex;
        for(int i = startIndex + 1;i <= endIndex;i++){
            if(arr[i] < pivot){
                mark ++;
                int temp = arr[mark];
                arr[mark] = arr[i];
                arr[i] = temp;
            }
        }
        arr[startIndex] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }

    /**
     * 基于栈的单边循环法
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    public static void sort3(int[]arr,int startIndex,int endIndex){

        //用一个集合栈代替递归的函数栈
        Stack<Map<String,Integer>> quickSortStack = new Stack<Map<String,Integer>>();
        //整个数列的起始下标，以哈希的形式入栈
        Map rootParam = new HashMap();
        rootParam.put("startIndex",startIndex);
        rootParam.put("endIndex",endIndex);
        quickSortStack.push(rootParam);

        //循环结束条件，栈为空时
        while (!quickSortStack.isEmpty()){
            //栈顶元素出栈，得到起止下标
            Map<String, Integer> param = quickSortStack.pop();
            //得到基准元素位置
            int pivotIndex = partition2(arr,param.get("startIndex"),param.get("endIndex"));
            //根据基准元素分成两部分，把每一部分的起止下标入栈
            if(param.get("startIndex") < pivotIndex -1){
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("startIndex",param.get("startIndex"));
                leftParam.put("endIndex",pivotIndex - 1);
                quickSortStack.push(leftParam);
            }
            if(pivotIndex + 1 < param.get("endIndex")){
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("startIndex",pivotIndex + 1);
                rightParam.put("endIndex",param.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }

    }

}