package com.deng.interview;
/**
 * @Classname Test04PowerOf2
 * @Description 验证一个整数是否是2的整数幂结果，例如16是2的4次方
 * @Version 1.0.0
 * @Date 2023/4/8 0:22
 * @Created by helloDeng
 * @author
 */
public class Test04PowerOf2 {
    public static void main(String[] args) {
        System.out.println(isPowerOf2(32));
        System.out.println(isPowerOf2(19));
        System.out.println(isPowerOf2v2(32));
        System.out.println(isPowerOf2v2(19));
    }

    /**
     * 第一种方法，复杂度为 O（logn）
     * @param num
     * @return
     */
    public static boolean isPowerOf2(int num ){
        int temp = 1;
        while (temp <= num){
            if(temp == num){
               return true;
            }
            temp = temp << 1;
        }
        return false;
    }

    /**
     * 第二种利用位计算
     * @param num
     * @return
     * 如果一个整数是2的整数次幂，那么当它转化成二进制时，只有最高位是1，其他位都是0
     *  2的整数次幂一旦减1，它的二进制数字就全部变成了1
     */
    public static boolean isPowerOf2v2(int num ){
        return ((num-1) & num) == 0;
    }
}
