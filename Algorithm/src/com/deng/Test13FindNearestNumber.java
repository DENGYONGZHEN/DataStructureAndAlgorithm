package com.deng;

import java.util.Arrays;

/**
 * @Classname Test13FindNearestNumber
 * @Description 在一个整数所包含数字的全部组合中，找到一个大于且仅大于原数的新整数
 *                                       如果输入12345，则返回12354。
 *                                       如果输入12354，则返回12435。
 *                                       如果输入12435，则返回12453。
 * @Version 1.0.0
 * @Date 2023/4/8 14:56
 * @Created by helloDeng
 * @author
 *
 * 给出1、2、3、4、5这几个数字。
 * 最大的组合：54321。 数字的逆序
 * 最小的组合：12345。 数字的顺序
 *
 * 为了和原数接近，我们需要尽量保持高位不变，低位在最小的范围内变换顺序
 * 至于变换顺序的范围大小，则取决于当前整数的逆序区域
 */
public class Test13FindNearestNumber {
    public static void main(String[] args) {
        int num = 12345;
        int[] ints = valueToArray(num);
        // 找出接下来10个最接近数
        for (int i = 0; i < 10; i++) {
            ints = findNearestNumber(ints);
            outPutNumbers(ints);
        }

    }

    /**
     * 输出数组
     * @param ints
     */
    private static void outPutNumbers(int[] ints) {
        for (int i : ints) {
            System.out.print(i);
        }
        System.out.println();
    }

    /**
     * 把一个整数各个位分别存进数组
     * @param num
     * @return
     */
    public static int[] valueToArray(int num){
        String s = String.valueOf(num);
        int[] numArr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            numArr[i] = Integer.parseInt(ch.toString());
        }
        return numArr;
    }
    public static int[] findNearestNumber(int[] numbers){
        int index = findTransferPoint(numbers);
        //如果数字置换边界为0，说明整个数组已经逆序，无法得到更大的相同数字组成的整数，返回null
        if(index == 0){
            return null;
        }
        //2。把逆序区域中的前一位数和后面只比它大的数字交换位置
        //复制并入参，避免直接修改入参
        int[] numbersCopy = Arrays.copyOf(numbers,numbers.length);
        exchangeHead(numbersCopy,index);
        //3. 把原来的逆序区域转变为顺序
        reverse(numbersCopy,index);
        return numbersCopy;
    }

    /**
     * 把交换后的逆序区域从前开始从小到大顺序化
     * @param numbersCopy
     * @param index
     */
    private static int[] reverse(int[] numbersCopy, int index) {
        for(int i = index,j=numbersCopy.length - 1;i < j;i++,j--){   //双指针同时变化
            int temp = numbersCopy[i];
            numbersCopy[i] = numbersCopy[j];
            numbersCopy[j] = temp;
        }
        return numbersCopy;
    }

    /**
     * 把逆序中交换点的值和后面比他大的值进行交换
     * @param numbers
     * @param index
     */
    private static int[] exchangeHead(int[] numbers, int index) {
        int head = numbers[index - 1];
        for (int i = numbers.length - 1; i > 0 ; i--) {
            if(head < numbers[i]){
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    /**
     * 从后向前查看逆序区域。找到逆序区域的前一位，也就是数字置换的边界
     * @param numbers
     * @return
     */
    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if(numbers[i] > numbers[i - 1]){
                return i;
            }
        }
        return 0;
    }
}
