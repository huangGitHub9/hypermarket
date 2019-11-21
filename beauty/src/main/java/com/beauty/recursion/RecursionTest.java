package com.beauty.recursion;

/**
 * @author Huangxulin
 * @date 2019/11/16 - 11:10
 * 递归
 */
public class RecursionTest {
    public static void main(String[] args) {
        //通过打印问题，回顾递归的机制
        test(6);
    }
    public static void test(int n){
        if (n > 2){
            test(n-1);
        }
        System.out.println("n="+n);
    }
}
