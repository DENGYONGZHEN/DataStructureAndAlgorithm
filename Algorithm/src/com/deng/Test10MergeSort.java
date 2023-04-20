package com.deng;

import java.util.Arrays;

/**
 * @Classname Test10MergeSort
 * @Description 归并排序
 * @Version 1.0.0
 * @Date 2023/4/20 21:29
 * @Created by helloDeng
 */
public class Test10MergeSort {
    public static void main(String[] args) {
        int [] arr = {5,3,9,24,65,43,8,11,26};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int [] arr,int start,int end){
        if(start < end) {
            int mid = (start + end) / 2;
            sort(arr,start,mid);
            sort(arr,mid+1,end);
            merge(arr,start,mid,end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        //开辟额外大集合，设置指针
        int [] tempArr = new int[end - start + 1];
            int p1 = start;
            int p2 = mid + 1;
            int p = 0;
            //比较两个小集合的元素，依次放入大集合
        while ((p1<=mid)&&(p2<=end)) {
            if (arr[p1] <= arr[p2]) {
                tempArr[p++] = arr[p1++];
            } else {
                tempArr[p++] = arr[p2++];
            }
        }
        //左侧小集合还有剩余，依次放入大集合尾部
        while(p1 <= mid){
            tempArr[p++] = arr[p1++];
        }
        //右侧小集合还有剩余，依次放入大集合
        while (p2 <= end){
            tempArr[p++] = arr[p2++];
        }
        //把大集合的元素复制回原数组
        for (int i = 0; i < tempArr.length; i++) {
            arr[i + start] = tempArr[i];
        }

    }
}
