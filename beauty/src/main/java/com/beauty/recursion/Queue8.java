package com.beauty.recursion;

/**
 * @author Huangxulin
 * @date 2019/11/16 - 20:46
 * 经典问题------> 八皇后
 */
public class Queue8 {

    static int max = 8; //定义数组大小
    static int[] array = new int[max];
    static int count = 0;//定义摆法
    static int checkNo = 0;//定义访问次数

    public static void main(String[] args) {
        check(0);
        System.out.println("总共有"+count+"种摆法");
        System.out.println("判断皇后位置次数"+checkNo);
    }

    //回溯和输出正确的摆法
    private static void check(int n) {
        if (n == max) { //数组性质，当下标n=8时，有此可见，此时已经摆好了八个皇后，正在摆放第九个皇后，此时直接输出就行
            print();
            count++;
            return;
        } else {
            for (array[n] = 0; array[n] < max; array[n]++) {
                if (judge(n)) { //当前位置的皇后不冲突，则摆放下一个皇后
                    check(n + 1);
                }
            }
        }
    }

    //判断当前的皇后与前面所有的皇后是否位置冲突
    private static boolean judge(int n) {
        checkNo++;
        for (int i = 0; i < n; i++) {
            //判断是否在同一行，或者在同意斜线，因为是数组，所以皇后一定不在同一行，在这就不用判断了
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //输出当前8皇后正确的摆法
    private static void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.printf(array[i] + " ");
        }
        System.out.println();
    }
}
