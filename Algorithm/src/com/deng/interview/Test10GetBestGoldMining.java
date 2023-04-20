package com.deng.interview;

/**
 * @Classname Test10GetBestGoldMining
 * @Description  求解金矿的问题              这个不懂
 * @Version 1.0.0
 * @Date 2023/4/9 18:38
 * @Created by helloDeng
 *
 * 动态规划，就是把复杂的问题简化成规模较小的子问题，再从简单的子问题自底向上一步一步递推，最终得到复杂问题的最优解
 *
 *
 *
 */
public class Test10GetBestGoldMining {
    public static void main(String[] args) {

        int w = 10;
        int[] p = {5,5,5,4,3};
        int[] g = {400,500,200,300,350};
        System.out.println("最优收益：" + getBestGoldMining(w,g.length,p,g) );
    }

    /**
     * 获得金矿最优收益 使用递归实现
     * @param w  工人数量
     * @param n  金矿数量
     * @param p  每个金矿开采所需的工人数量的数组
     * @param g  每个金矿储量的数组
     * @return
     */
    public static int getBestGoldMining(int w,int n,int[] p,int[] g){

        if(w == 0 || n == 0){
            return 0;
        }
        if(w < p[n-1]){
            return getBestGoldMining(w,n-1,p,g);
        }
        return Math.max(getBestGoldMining(w,n-1,p,g),
        getBestGoldMining(w-p[n-1],n-1,p,g) + g[n - 1]);
    }

    /**
     * 获得金矿最优收益 优化
     * @param w 工人数量
     * @param p 每个金矿开采所需的工人数量的数组
     * @param g 每个金矿储量的数组
     * @return
     */
    public static int getBestGoldMining1(int w,int[] p,int[] g){

        //创建表格
        int[][] resultTable = new int[g.length + 1][w+1];
        //填充表格
        for (int i = 1; i <= g.length; i++) {
            for (int j = 1; j <= w ; j++) {
                if(j < p[i-1]){
                    resultTable[i][j] = resultTable[i-1][j];
                }else {
                    resultTable[i][j] = Math.max(resultTable[i-1][j],resultTable[i-1][j-p[i-1]] + g[i-1]);
                }
            }
        }
        return  resultTable[g.length][w];
    }
    /**
     * 获得金矿最优收益 进一步优化
     * @param w 工人数量
     * @param p 每个金矿开采所需的工人数量的数组
     * @param g 每个金矿储量的数组
     * @return
     */
    public static int getBestGoldMining2(int w,int[] p,int[] g){
        //创建当前结果
        int[] results = new int[w+1];
        //填充一维数组
        for (int i = 1; i < g.length; i++) {
            for (int j = w; j >= 1 ; j--) {
                if(j >= p[i-1]){
                    results[j] = Math.max(results[j],results[j-p[i-1]] + g[i-1]);
                }
            }
        }
        //返回最后一个格子的值
        return results[w];
    }
}
