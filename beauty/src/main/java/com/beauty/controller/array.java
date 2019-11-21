package com.beauty.controller;

/**
 * @author Huangxulin
 * @date 2019/10/30 - 21:29
 */
public class array {
    public static void main(String[] args) {

        int arr[][] = new int[11][11];
        arr[1][2] = 1;
        arr[2][3] = 2;
        arr[3][4] = 1;
        System.out.println("----二维数组-----");
        for(int[] row : arr){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        int sum = 0;
        for(int i = 0;i < 11;i++){
            for (int j = 0;j < 11;j++){
                if(arr[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("sum="+sum);

        int[][] chessArr = new int[sum+1][3];
        chessArr[0][0] = 11;
        chessArr[0][1] = 11;
        chessArr[0][2] = sum;

        int count = 0;
        for(int i = 0;i < 11;i++){
            for (int j = 0;j < 11;j++){
                if(arr[i][j] != 0){
                    count++;
                    chessArr[count][0] = i;
                    chessArr[count][1] = j;
                    chessArr[count][2] = arr[i][j];
                }
            }
        }

        System.out.println("------稀疏数组-----");
        System.out.println("行\t列\t值");
        for (int i = 0;i < chessArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",chessArr[i][0],chessArr[i][1],chessArr[i][2]);
        }
        System.out.println();

        System.out.println("恢复成二维数组--------------");
        int[][] arr1 = new int[chessArr[0][0]][chessArr[0][1]];
        for (int i = 1;i < chessArr.length;i++){
            arr1[chessArr[i][0]][chessArr[i][1]] = chessArr[i][2];
        }
        for (int[] row1 : arr1){
            for (int data : row1){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("使用for循环遍历二维数组----------");
        for (int i = 0;i < 11;i++){
            for (int j = 0;j <11;j++){
                System.out.printf("%d\t",arr[i][j]);
            }
            System.out.println();
        }
        /*--------------------------------------------------------------------------------------------------------------------------*/


    }
}
