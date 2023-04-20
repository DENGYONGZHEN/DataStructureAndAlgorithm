package com.deng;

/**
 * @Classname Test07SelectionSort
 * @Description 选择排序 每一轮选出最小元素直接交换到左侧的思路
 * @Version 1.0.0
 * @Date 2023/4/19 21:53
 * @Created by helloDeng
 */
public class Test07SelectionSort {
    public static void main(String[] args) {
        int[] nums = {10,3,5,8,2,6,-1,35};
        sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
    }
    public static void sort(int[] nums){
        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[minIndex] > nums[j]){
                  minIndex = j;
                }
            }
            if(i != minIndex){
                int temp = nums[minIndex];
                nums[minIndex] = nums[i];
                nums[i] = temp;
            }
        }
    }
}
