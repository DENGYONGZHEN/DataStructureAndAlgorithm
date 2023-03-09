package com.deng;

/**
 * @Classname Test02BubbleSort
 * @Description 冒泡排序
 * @Version 1.0.0
 * @Date 2023/3/8 17:25
 * @Created by helloDeng
 *
 * 冒泡排序是一种稳定排序，值相等的元素并不会打乱原本的顺序。由于该排序算法的每一轮都要遍历所有元素，总共遍历（元素数量-1）轮
 *
 *
 *
 *
 */
public class Test02BubbleSort {
    public static void main(String[] args) {
        int [] arr = new int[]{3,2,4,3,8,1,9,0};
        //sort(arr);
        sort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

    /**
     * 使用双循环进行排序。外部循环控制所有的回合，内部循环实现每一轮的冒泡处理，先进行元素比较，再进行元素交换。
     * @param arr
     */
    public  static void sort(int [] arr){                 //冒泡排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序的优化
     * @param arr
     */
    public static void sort2(int [] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            //有序标记,每一轮的初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSorted = false;     //如果交换位置了，就把排序标记设置尾false
                }
            }
            if(isSorted){         //如果有序标记为true,说明本轮循环没有元素交换位置，说明都是有序的，排序完成
                break;
            }
        }
    }

    /**
     * 冒泡排序的进一步优化,记录上一轮的最后元素交换的位置，说明此位置以后的元素都是有序的
     * @param arr
     */
    public static void sort3(int [] arr){

        //记录最后一次交换的位置
        int lastChangIndex = 0;
        //无需数列的边界，每次比较只需要比到这里为止
        int sortBorder = arr.length - 1;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isSorted = true; //有序标记
            for (int j = 0; j < sortBorder; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j+1] = temp;
                    lastChangIndex = j;
                    isSorted = false;
                }
            }
            sortBorder = lastChangIndex;
            if(isSorted){
                break;
            }
        }

    }

    /**
     * 鸡尾酒排序：鸡尾酒排序的元素比较和交换过程是双向的。即第一轮排序是从左开始分别比较两个元素并交换，第二轮就从右边开始分别比较两个元素
     * 第三轮又从左边开始。。。以此类推
     * @param arr
     */
    public static void sort4(int[]arr){

        int temp = 0;
        for (int i = 0; i < arr.length / 2; i++) {
            boolean isSorted = true;                //有序标记
            //奇数轮，从左向右比较和交换
            for (int j = i; j < arr.length - i - 1; j++) {  //这里j=i,是因为就像从左往右遍历时，每一轮都会确定一个值
                if(arr[j] > arr[j+1]){                      //从右往左也是每一轮可以确定一个值，所以第几轮：索引就从轮数-1次开始遍历
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted){
                break;
            }
            //在偶数轮前，将isSorted标记为true
            isSorted = true;
            //偶数轮，从右向左开始遍历
            for (int j = arr.length - i - 1; j > i; j--) { //这里j>i，也是同理。i= 轮数 -1，从最前面开始依次被确定有序
                if(arr[j-1] > arr[j]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted){
                break;
            }
        }
    }

    /**
     *  TODO 在鸡尾酒排序的基础上，加入有序区的优化  sort3
     * @param arr
     */
    public static void sort5(int[]arr){

    }

}
