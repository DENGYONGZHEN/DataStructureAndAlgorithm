package com.deng;

/**
 * @Classname Test08InsertSort
 * @Description 插入排序  维护一个有序区，把元素一个个插入有序区的适当位置，直到所有元素都有序为止。
 * @Version 1.0.0
 * @Date 2023/4/19 22:12
 * @Created by helloDeng
 */
public class Test08InsertSort {
    public static void main(String[] args) {
        int[] nums = {10,3,5,8,2,6,-1,35};
        sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }

    }
    public static void sort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int index = -1;
            for (int j = 0; j < i; j++) {
                if(arr[i] < arr[j]){
                   index = j;
                   break;
                }
            }
            if(index != -1){
                int temp = arr[i];
                for (int j = i - 1; j >= index; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[index] = temp;
            }
        }
    }

    /**
     * 优化
     * @param nums
     */
    public static void sort2(int[] nums){
        for (int i = 1; i < nums.length; i++) {
            int insertValue = nums[i];
            int j = i -1;
            //从右想做比较元素的同时，进行元素复制
            for (; (j>=0) && (insertValue <nums[j]) ; j--) {
                nums[j+1] = nums[j];
            }
            nums[j+1] = insertValue;
        }

    }
}
