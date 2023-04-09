package com.deng.interview;

/**
 * @Classname Test05MaxSortedDistance
 * @Description 求无序数组排序后相邻两个元素之间差值最大为多少
 * @Version 1.0.0
 * @Date 2023/4/8 0:40
 * @Created by helloDeng
 */
public class Test05MaxSortedDistance {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,5,9,10,12,19};
        System.out.println(getMaxSortedDistance(arr));

        System.out.println(getMaxSortedDistance1(arr));

    }

    /**
     * 使用计数排序的思想，先找到最大值和最小值，创建一个最大值-最小值+1 的数量的数组，
     * 遍历原数组，数组[原数组元素-最小值]++
     * @param arr
     * @return
     */
    public static int getMaxSortedDistance(int[] arr){
        //1.得到数列的最大值和最小值
        int maximum = arr[0];
        int minimum = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > maximum){
                maximum = arr[i];
            }
            if(arr[i] < minimum){
                minimum = arr[i];
            }
        }
        int d = maximum - minimum; //如果maximum和minimum相等，返回0
        if(d == 0){
            return 0;
        }
        int[] array = new int[d + 1];
        for (int i = 0; i < arr.length; i++) {
            array[arr[i] - minimum]++;
        }
        int count = 0;
        int biggerCount = 0;
        for (int i = 0; i < array.length; i++) {
          if(array[i] == 0) {
              count++;
          }else{
              biggerCount = Math.max(count,biggerCount);
              count = 0;
          }
        }
        return biggerCount + 1;
    }

    /**
     * 最优解
     * 使用桶排序的思想
     * @param arr
     * @return
     */
    public static int getMaxSortedDistance1(int[] arr){
        //1.得到数列的最大值和最小值
        int maximum = arr[0];
        int minimum = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > maximum){
                maximum = arr[i];
            }
            if(arr[i] < minimum){
                minimum = arr[i];
            }
        }
        int d = maximum - minimum; //如果maximum和minimum相等，返回0
        if(d == 0){
            return 0;
        }
        //2.初始化桶
        int bucketNum = arr.length;
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i] = new Bucket();
        }
        //3.遍历原始数组，确定每个桶的最大最小值
        for (int i = 0; i < arr.length; i++) {
            int index = (int)((arr[i] - minimum) * (bucketNum - 1)/d) ;
            if(buckets[index].min == null || buckets[index].min > arr[i]){
                buckets[index].min = arr[i];
            }
            if(buckets[index].max == null || buckets[index].max < arr[i]){
                buckets[index].max = arr[i];
            }
        }
        //4.遍历桶，找到最大差值
        int leftMax = buckets[0].max;
        int maxDistance = 0;
        for (int i = 1; i < bucketNum; i++) {
            if(buckets[i].min == null){
                continue;
            }
            if(maxDistance < buckets[i].min - leftMax){
                maxDistance = buckets[i].min - leftMax;
            }
            leftMax = buckets[i].max;
        }
        return maxDistance;
    }
    private static class Bucket{
        Integer max;
        Integer min;
    }
}
