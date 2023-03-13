package com.deng;

import java.util.Arrays;

/**
 * @Classname Test05CountSort
 * @Description 计数排序
 * @Version 1.0.0
 * @Date 2023/3/12 12:03
 * @Created by helloDeng
 * @author
 *
 * 假设数组中有20个随机整数，取值范围为0～10，要求用最快的速度把这20个整数从小到大进行排序。
 *
 * 所以，可以根据这有限的范围，建立一个长度为11的数组。数组下标从0到10，元素初始值全为0。
 *
 * 开始遍历这个无序的随机数列，对应数组下标的元素进行加1操作。
 *
 *计数排序有它的局限性：1.当数列最大和最小值差距过大时，并不适合用计数排序。
 *                  2.当数列元素不是整数时，也不适合用计数排序。
 *
 * 优化版本的计数排序属于稳定排序。
 *
 */
public class Test05CountSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 4, 6, 1, 7, 8, 3, 5, 6, 2, 3, 8, 0, 9, 3, 5, 4, 10, 3};
//        int[] sortedArray = countSort(arr);
//        System.out.println(Arrays.toString(sortedArray));
        int[] sort2 = countSort2(arr);
        System.out.println(Arrays.toString(sort2));


    }
    public static int[] countSort(int[]arr){
        //求出无序数组中的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(max < arr[i]){
                max = arr[i];
            }
        }
        //根据数组最大值确定统计数组的长度
        int[] countArray = new int[max + 1];
        //遍历无序数组，填充统计数组
        for (int i = 0; i < arr.length; i++) {
            countArray[arr[i]]++;
        }
        //遍历统计数组，输出结果
        int index = 0;
        int[] sortedArray = new int[arr.length];
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i] ; j++) {
                sortedArray[index++]=i;
            }
        }
        return sortedArray;
    }

    /**
     * 计数排序的优化
     * @param array
     */
    public static int[] countSort2(int[] array){
        //1.求出无序数组的最大值和最小值
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if(max < array[i]){
                max = array[i];
            }
            if(min > array[i]){
                min = array[i];
            }
        }
        //创建统计数组
        int[] countArray = new int[max - min + 1];
        //2.遍历无序数组，填充统计数组
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min] ++;
        }
        //3.从统计数组的第2个元素开始，每一个元素都加上前面所有元素之和
        //例如下标是9的元素值为5，代表原始数列的整数9，最终的排序在第5位
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] +=countArray[i-1];
        }
        //4.倒叙遍历原有数列，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0 ; i--) {
            //countArray[array[i] - min] 意思是在无序数组中，i指向的元素在统计数组中的排名，
            //比如countArray[array[i] - min]的值为3，代表排名第3，后面减1，是因为数组起始索引为0
            sortedArray[countArray[array[i] - min]-1] = array[i];
            countArray[array[i] - min]--;     //为了分辨相同值的先后顺序
        }
        return sortedArray;
    }

}