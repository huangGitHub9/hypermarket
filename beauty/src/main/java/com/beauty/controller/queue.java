package com.beauty.controller;

import java.util.Scanner;

/**
 * @author Huangxulin
 * @date 2019/11/8 - 20:30
 */
public class queue {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("---------------队列测试-----------------");
            System.out.println("s(show): 显示队列,e(exit): 退出程序,a(add): 添加数据到队列,g(get): 从队列取出数据,h(head): 查看队列头的数据");
            System.out.println("请输入上方指令：");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.gettQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("-------------------程序退出----------------------");
    }
}

class ArrayQueue{
    private int maxSize; //定义数组的最大容量
    private int front; //定义队列头
    private int rear; //队列尾
    private int[] arr; //该数据用于存放数据，模拟队列

    protected ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    //队列满
    protected boolean isFull(){
        return rear == maxSize - 1;
    }

    //队列空
    boolean issEmpty(){
        return rear == front;
    }

    //添加队列
    void addQueue(int num){
        if (isFull()){
            //throw new RuntimeException("队列满了------");
            System.out.println("队列满了--------");
            return;
        }
        rear++;
        arr[rear] = num;
    }

    //取出队列
    int gettQueue(){
        if (issEmpty()){
            throw new RuntimeException("队列空，无法取出------");
        }
        front++;
        return arr[front];
    }

    //获取头队列
    int headQueue(){
        if (issEmpty()){
            throw new RuntimeException("队列空，无法取出------");
        }
        return arr[front + 1];
    }

    //显示所有队列
    void showQueue(){
        if (issEmpty()){
            System.out.println("队列空-----");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

}