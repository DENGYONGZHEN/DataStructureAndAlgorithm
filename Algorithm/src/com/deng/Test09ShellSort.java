package com.deng;

/**
 * @Classname Test09ShellSort
 * @Description 希尔排序 逐步分组进行粗调，再进行直接插入排序的思想
 * @Version 1.0.0
 * @Date 2023/4/19 22:53
 * @Created by helloDeng
 */
public class Test09ShellSort {
    public static void main(String[] args) {
        int [] arr = {5,3,9,24,65,43,8,11,26};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        
    }
    public static void sort(int [] nums){
        //希尔排序的增量
        int d = nums.length;
        while (d > 1){
            //使用希尔增量的方式，即每次折半
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < nums.length; i +=d) {
                    int temp = nums[i];
                    int j;
                    for(j = i -d;(j>=0) && (nums[j] > temp);j = j-d){
                        nums[j +d] = nums[j];
                    }
                    nums[j + d] = temp;
                }
            }
        }
    }
}
