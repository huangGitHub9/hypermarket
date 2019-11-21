package com.beauty.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Huangxulin
 * @date 2019/11/17 - 20:01
 * 希尔排序，在插入时使用交换法，这种算法不是很完美，需要优化
 * 希尔排序，在插入时使用移动法，很完善
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort3(arr);
        System.out.println(Arrays.toString(arr));
    }

    //此种方法为希尔排序中的交换法，也不是最好的，需要优化
    public static void shellSort(int[] arr) {
        int length = arr.length / 2;
        int temp = 0;
        while (length >= 1) {
            for (int i = length; i < arr.length; i++) {
                for (int j = i - length; j >= 0; j -= length) {
                    if (arr[j] > arr[j + length]) {
                        temp = arr[j];
                        arr[j] = arr[j + length];
                        arr[j + length] = temp;
                    }
                }
            }
            length = length / 2;
            System.out.println(Arrays.toString(arr));
        }
    }

    //使用交换法的希尔排序
    public static void shellSort2(int[] arr) {

        //增量gap，并不断缩小增量，
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从gap起，逐个对其所在的组进行插入排序
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertVal = arr[insertIndex];
                if (arr[insertIndex] < arr[insertIndex - gap]) {
                    while (insertIndex - gap >= 0 && insertVal < arr[insertIndex - gap]) {
                        arr[insertIndex] = arr[insertIndex - gap];
                        insertIndex -= gap;
                    }
                    arr[insertIndex] = insertVal;
                }
            }
        }
    }

    //强化训练
    public static void shellSort3(int[] arr) {
        int insertIndex = 0;//定义索引
        int insertVal = 0;//定义索引值
        for (int gap = arr.length; gap > 0; gap /= 2) {

            for (int i = gap; i < arr.length; i++){
                insertIndex = i;
                insertVal = arr[i];
                if (insertVal < arr[insertIndex - gap]){ //当当前的数字大于前面一个数字时，在这一组数的分组中，必然大学前面所有的数字，if语句就不执行
                    while (insertIndex - gap >=0 && insertVal < arr[insertIndex - gap]){
                        arr[insertIndex] = arr[insertIndex - gap];
                        insertIndex -=gap;
                    }
                    arr[insertIndex] = insertVal;
                }
            }

        }
    }
}
