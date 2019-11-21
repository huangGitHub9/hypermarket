package com.beauty.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Huangxulin
 * @date 2019/11/17 - 16:35
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, 20};
//        System.out.println("排序前 " + Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 900000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(date1);
        System.out.println(format1);
        selectSort(arr);
        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println(format2);
    }

    //选择排序的算法
    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            int minIndex = 0;
            int min = arr[i]; //假定当前的数组就是最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            if (min != arr[i]) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.println("第" + (i + 1) + "趟排序");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
