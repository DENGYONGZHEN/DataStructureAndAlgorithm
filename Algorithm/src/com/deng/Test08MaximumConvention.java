package com.deng;

/**
 * @Classname Test08MaximumConvention
 * @Description 求两整数的最大公约数
 * @Version 1.0.0
 * @Date 2023/3/31 16:08
 * @Created by helloDeng
 */
public class Test08MaximumConvention {
    public static void main(String[] args) {
        System.out.println(gcd(25,5));
        System.out.println(gcd(100,80));
        System.out.println(gcd(27,14));
    }

    /**
     * 暴力枚举法
     * @param a
     * @param b
     * @return
     */
    public static int getGreatestCommonDivisor(int a,int b){
        int big = a > b ? a:b;
        int small = a < b ? a:b;
        if(big%small == 0){
            return small;
        }
        for(int i = small/2; i > 1;i--){
            if(small%i==0 && big%i == 0){
                return i;
            }
        }
        return 1;
    }

    /**
     * 辗转相除法/欧几里得算法
     * 两个正整数a和b（a＞b），它们的最大公约数等于a除以b的余数c和b之间的最大公约数。
     * @param a
     * @param b
     * @return
     */
    public static int getGreatestCommonDivisor1(int a,int b){
        int big = a > b ? a:b;
        int small = a < b ? a:b;
        if(big%small == 0){
            return small;
        }
        return getGreatestCommonDivisor1(big%small,small);
    }

    /**
     * 更相减损术
     * 两个正整数a和b（a＞b），它们的最大公约数等于a-b的差值c和较小数b的最大公约数。
     * @param a
     * @param b
     * @return
     */
    public static int getGreatestCommonDivisor2(int a,int b){
        if(a == b ){
            return a;
        }
        int big = a > b ? a:b;
        int small = a < b ? a:b;
        return getGreatestCommonDivisor2(big - small,small);
    }

    /**
     * 最优解
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a,int b){
       if(a == b){
           return a;
       }
       if((a&1) == 0 && (b&1)== 0){     //（a&1）==0，则说明整数a是偶数；偶数最后一位为0，1前面的位都是0
           return gcd(a >> 1,b >> 1) << 1;
       }else if((a&1) == 0 && (b&1) != 0){  //如果（a&1）！=0，则说明整数a是奇数。
           return gcd(a >> 1, b);
       } else if ((a&1) != 0 && (b&1) == 0) {
           return gcd(a,b >> 1);
       }else {
           int big = a > b ? a:b;
           int small = a < b ? a:b;
           return gcd(big - small,small);
       }

    }
}
