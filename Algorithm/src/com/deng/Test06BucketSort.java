package com.deng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @Classname Test06BucketSort
 * @Description 桶排序
 * @Version 1.0.0
 * @Date 2023/3/12 14:28
 * @Created by helloDeng
 * @author
 * 桶排序同样是一种线性时间的排序算法。类似于计数排序所创建的统计数组，桶排序需要创建若干个桶来协助排序。
 *
 *每一个桶（bucket）代表一个区间范围，里面可以承载一个或多个元素。
 *
 *
 * 所有的桶都保存在ArrayList集合中，每一个桶都被定义成一个链表（LinkedList＜Double＞），这样便于在尾部插入元素。
 *
 *  桶排序的性能并非绝对稳定。如果元素的分布极不均衡，在极端情况下，第一个桶中有n-1个元素，
 * 最后一个桶中有1个元素。此时的时间复杂度将退化为O（nlogn），而且还白白创建了许多空桶。
 */
public class Test06BucketSort {
    public static void main(String[] args) {
        double[] array = new double[]{4.12,6.421,0.0023,3.0,2.123,8.122,4.12,10.09};
        double[] sortedArray = bucketSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }

    /**
     * 桶排序
     * @param arr
     */
    public static double[] bucketSort(double[] arr){
        //获取无序数列的最大值和最小值,并算出差值d
        double min = arr[0];
        double max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(min > arr[i]){
                min = arr[i];
            }
            if(max < arr[i]){
                max = arr[i];
            }
        }
        double d = max - min;

        //2.初始化桶
        int bucketNum = arr.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<LinkedList<Double>>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<Double>());
        }
        //3.遍历原始数组，将每个元素放入桶中
        for (int i = 0; i < arr.length; i++) {
            int num = (int) ((arr[i] - min) * (bucketNum - 1) / d);
            bucketList.get(num).add(arr[i]);
        }
        //4.对每个桶内部进行排序
        for (int i = 0; i < bucketList.size(); i++) {
            //JDK底层采用了归并排序或归并的优化版本
            Collections.sort(bucketList.get(i));
        }
        //5.输出全部元素
        double[] sortedArray = new double[arr.length];
        int index = 0;
        for (LinkedList<Double> doubles : bucketList) {
            for (Double element : doubles) {
                sortedArray[index] = element;
                index ++;
            }
        }
        return sortedArray;

    }
}

