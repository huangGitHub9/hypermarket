package com.beauty.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Huangxulin
 * @date 2019/11/17 - 15:14
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        //定义一个数组
//        int[] arr = {3, 9, -1, 10, 20};
//        System.out.println("排序前 " + Arrays.toString(arr));
        //测试80000条数据花费的时间对比
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        //创建当前的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Time = simpleDateFormat.format(date1);
        System.out.println("运行前的时间 " + date1Time);
        bubbleSort(arr);
        //运行后的时间
        Date date2 = new Date();
        String date2Time = simpleDateFormat.format(date2);
        System.out.println("运行后的时间 " + date2Time);
    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;//标识符，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;//在此进行优化算法，当排序不在进行交换时提前结束循环
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (!flag) {
                break;
            } else {
                flag = false; //重置flag，进行下次判断
            }
//            System.out.println("第" + (i + 1) + "趟排序");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
