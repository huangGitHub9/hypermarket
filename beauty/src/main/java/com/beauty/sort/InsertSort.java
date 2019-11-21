package com.beauty.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Huangxulin
 * @date 2019/11/17 - 17:28
 * 直接插入排序还是有点问题，后面有对这个问题进行优化======>希尔排序
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 8, 0};
//        System.out.println("排序前 "+ Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 700000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(date1);
        System.out.println(format1);

        insertSort(arr);

        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println(format2);
//        System.out.println("排序后 "+ Arrays.toString(arr));
    }

    //插入排序方法
    public static void insertSort(int[] arr) {
        int insertIndex = 0;
        int insertVal = 0;
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertVal = arr[i];
            while (insertIndex >= 0 && arr[insertIndex] > insertVal) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;

//            System.out.println("第" + i + "趟排序");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
