package com.deng;


import java.util.Arrays;

/**
 * @Classname Test01Array
 * @Description  数组
 * @Version 1.0.0
 * @Date 2023/3/5 16:35
 * @Created by helloDeng
 * 数组对应的英文是array，是有限个相同类型的变量所组成的有序集合，
 * 数组中的每一个变量被称为元素。数组是最为简单、最为常用的数据结构
 *
 * 数组的另一个特点，是在内存中连续顺序存储，因此可以很好地实现逻辑上的顺序表。
 *
 * (1) 计算机可以一步就跳到任意一个内存地址上。（就好比，要是你知道大街 123 号在哪儿，那么就可以直奔过去。）
 * (2) 数组本身会记有第一个格子的内存地址，因此，计算机知道这个数组的开头在哪里。
 * (3) 数组的索引从 0 算起。
 *
 */
public class Test01Array {
    public static void main(String[] args) {

        Array array = new Array(10);
        for (int i = 0; i < 15; i++) {
            array.insertAtTheEnd("[" + i + "]");         //插入到数组 有数据后的第一个没数据的地方
        }
        System.out.println(array);

        array.insertAtMid("甲",5);                 //插入到数组索引为5的地方
        System.out.println(array);
        array.delete(6);                               //删除索引为6的元素
        System.out.println(array);
    }
}
class Array{
    private String[] arr;
    private int size;



    public Array(int size) {
        this.arr = new String[size];
        this.size = size;
    }
    //在数组最后插入数据
    public void insertAtTheEnd(String data){
        int index = getIndex();
        if(index >= arr.length){
            resize();
        }
        arr[index] = data;
    }

    //在数组中间插入数据
    public void insertAtMid(String data,int index){
        int count = getIndex(); //数组中元素的个数
        if(index >= arr.length || count >= arr.length){               //虽然说是在中间插入数据，但也就判断一下
            resize();
        }
        for (int i = arr.length - 1; i >= 0 ; i--) {
            if(arr[i] != null){
                arr[i+1] = arr[i];
                if(i == index){
                   arr[i] = data;
                   break;
                }
            }
        }


    }
    public void delete(int index) {
            for (int i = index; i < arr.length - 1; i++) {          //注意索引越界问题
                arr[i] = arr[i + 1];
            }

    }

    //数组的扩容
    private void resize(){
        String[] array = new String[arr.length * 2];
        size *=2;
        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }
        arr = array;
    }
    private int getIndex() {       //在数组最后面插入数据时，获取该插入的索引
        int index = 0;               //数组中第一个没有存数据的地方，假设之前的索引都有存储数据
        for (int i = size-1; i >= 0; i--) {     //从后往前遍历
            if(arr[i] == null){
                if(i == 0 || arr[i-1] != null){
                    index = i;
                    break;
                }
            }else{
                index = size;
                break;
            }
        }
        return index;
    }
    @Override
    public String toString() {
        return "Array{" +
                "arr=" + Arrays.toString(arr) + '}';
    }


}

