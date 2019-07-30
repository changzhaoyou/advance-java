package com.ycz.data.structure.linear.sparse;

/**
 * @Description:线性结构--稀疏数组（五子棋）
 * @Author: hz18123767/ycz
 * @Date:2019/6/12
 */
public class SparseArray {

    public static void main(String[] args) {
        //1、创建原始二维数组
        int[][] chessArr = new int[11][11];
        //初始化五子棋元素
        // 0：表示没有棋子，1：表示白子，2：表示黑子
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[3][4] = 1;
        //遍历原始二维数组（%d\t）
        System.out.println("================原始二维数组===============");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println("================原始二维数组===============");
        //2、将二维数组转稀疏数组
        //（1）、先遍历原始二位数组，取出有效数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr.length; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }
        /**
         * （2）、创建稀疏数组(行 列 值)
         *  例如：
         *     行       列      值
         *     11      11    sum个数
         *   有效行值  有效值列   值
         *
         */
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组初始化值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //遍历原始二维数组，将非0的数据赋值到稀疏数组中
        //指的是有效值位置
        int count = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr.length; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }
        System.out.println();
        System.out.println("======稀疏数组========");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println("======稀疏数组========");
        //3、将稀疏数组恢复原始数组
        //1、创建原始二维数组
        int[][] chessArr1 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr1[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("================恢复二维数组===============");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println("================恢复二维数组===============");
    }
}
