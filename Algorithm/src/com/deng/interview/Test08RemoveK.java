package com.deng.interview;



/**
 * @Classname Test08RemoveK
 * @Description  删去k个数字后的最小值
 * @Version 1.0.0
 * @Date 2023/4/8 17:59
 * @Created by helloDeng
 * @author
 *
 * 给出一个整数541 270 936，要求删去1个数字，让剩下的整数尽可能小。
 * 删除一个数字所能得到的最小值是41 270 936。
 * 再删除一个数字所能得到的最小值是1 270 936
 * 再删除一个数字，删除7所能得到的最小值是 12 093
 * 每一步都要求得到删除一个数字后的最小值，经历3次，相当于求出了删除k（k=3）个数字后的最小值。
 * 像这样依次求得局部最优解，最终得到全局最优解的思想，叫作贪心算法。
 *
 *
 * substring()中包括开始位置的元素，不包括结束位置的元素
 */
public class Test08RemoveK {
    public static void main(String[] args) {
        System.out.println(removeKDigits("1593212",3));
        System.out.println(removeKDigits("30200",1));
        System.out.println(removeKDigits("10",2));
        System.out.println(removeKDigits("541270936",3));

    }

    /**
     * 移除k个数字，获得最小值
     * @param num  给定的数
     * @param k    移除的数字个数
     * @return
     */
    public static String removeKDigits(String num,int k ){
        String numNew = num;
        for (int i = 0; i < k; i++) {
            boolean hasCut = false;
            //从右向左遍历，找到比自己右侧数字大的数字并删除
            for (int j = 0; j < numNew.length() - 1; j++) {
                if(numNew.charAt(j) > numNew.charAt(j + 1)){
                    numNew = numNew.substring(0,j) + numNew.substring(j+1,numNew.length());
                    hasCut = true;
                    break;
                }
            }
            //如果没有找到要删除的数字，则删除最后一个数字
            if(!hasCut){
                numNew = numNew.substring(0,numNew.length() - 1);
            }
            //清除整数左侧的数字0
            numNew = removeZero(numNew);
        }
        //如果整数的所有数字都被删除了，直接返回0
        if(numNew.length() == 0){
            return "0";
        }
        return numNew;
    }

    private static String removeZero(String numNew) {
       /* Integer num = Integer.parseInt(numNew);
        return num.toString();*/
        for (int i = 0; i < numNew.length() - 1; i++) {   //这里numNew.length() - 1 ，在只剩下一个数字时，直接退出，防止空指针异常
            if(numNew.charAt(0) != '0'){
                break;
            }
            numNew = numNew.substring(1,numNew.length());
        }
        return numNew;
    }

    /**
     * 优化算法,删除整数的k个数字，获得删除后的最小值
     * @param num 原整数
     * @param k   删除数字的数量
     * @return
     */
    public static String removeKDigits2(String num,int k ){
        //新整数长度 = 原整数长度 - k
        int newLength = num.length() - k;
        //创建一个栈，用于接收所有的数字
        char[] stack = new char[num.length()];
        int top = 0;
        for (int i = 0; i < num.length(); ++i) {
            //遍历当前数字
            char c = num.charAt(i);
            //当栈顶数字大于遍历到的当前数字时，栈顶数字出栈（相当于删除数字）
            while (top > 0 && stack[top - 1] > c && k > 0 ){
                top -= 1;
                k -= 1;
            }
            //遍历到的当前数字入栈
            stack[top++] = c;
        }
        //找到栈中第一个非零数字的位置，以此构建新的整数字符串
        int offset = 0;
        while (offset < newLength && stack[offset] == '0'){
            offset++;
        }
        return offset == newLength ? "0" :new String(stack,offset,newLength - offset);
    }
}
