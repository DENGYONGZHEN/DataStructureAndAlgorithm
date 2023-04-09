package com.deng.interview;

/**
 * @Classname Test09BigNumberSum
 * @Description   大整数求和
 * @Version 1.0.0
 * @Date 2023/4/9 13:27
 * @Created by helloDeng
 * @author
 */
public class Test09BigNumberSum {
    public static void main(String[] args) {

        System.out.println(bigNumberSum("426709752318","95481253129"));
    }

    /**
     * 大整数求和
     * @param num1
     * @param num2
     * @return
     */
    public static String bigNumberSum(String num1,String num2){

        //1.得到最大整数的位数
        int maxLength = num1.length() > num2.length() ? num1.length() : num2.length();
        //2.整数转化为数组
        int[] bigNum1 = numToArray(num1,maxLength);
        int[] bigNum2 = numToArray(num2,maxLength);
        int[] result = new int[maxLength + 1];
        //两数组相加获得结果数组
        for (int i = 0; i < result.length; i++) {
            int temp = result[i]+bigNum1[i] + bigNum2[i];
            if(temp > 9){
                result[i] = temp - 10;
                result[i + 1] = 1;
            }else {
                result[i] = temp;
            }
        }
        //把结果数组进行倒序遍历，获得结果
        StringBuilder sb = new StringBuilder();
        //是否找到大整数的最高有效位
        boolean findNotZero = false;
        for (int i = result.length - 1; i >= 0 ; i--) {
            if(!findNotZero){
                if(result[i] == 0){
                    continue;
                }
                findNotZero = true;
            }
            sb.append(result[i]);
        }
        return sb.toString();

    }

    private static int[] numToArray(String num1, int maxLength) {
        int[] num = new int[maxLength + 1];          //数组长度等于最大整数的长度+1
        for (int i = 0; i < num1.length(); i++) {
            //整数对应的各个位上的值倒序存到数组上
            num[i] = (num1.charAt(num1.length() - 1 - i) - '0'); //转换为ASCII码进行运算,字符0为48,1为49
        }
        return num;
    }


}
