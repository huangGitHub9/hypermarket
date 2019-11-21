package com.beauty.exercise;

import java.util.Scanner;

/**
 * @author Huangxulin
 * @date 2019/11/12 - 13:53
 * 使用数组实现栈的操作流程
 */
public class ArrayStackTest {
    public static void main(String[] args) {
        System.out.println("-----------入栈出栈小游戏-------------");
        ArrayStack stack = new ArrayStack(4);//特别注意，栈的大小一定要定义
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("请输入指令，pop：出栈，push：入栈，show：遍历栈，exit：退出程序");
            key = scanner.next();
            switch(key){
                case "show":
                    try {
                        stack.showSatck();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据是："+res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "push":
                    System.out.println("请输入需要入栈的数字：");
                    int num = scanner.nextInt();
                    stack.push(num);
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("指令错误！");
                    break;
            }
        }
        System.out.println("谢谢使用，程序已经退出！");
    }
}

class ArrayStack{
    private int maxSize;//栈的大小
    private int top = -1; //定义栈顶，初始值为-1
    private int[] stack; //数组，使用数组模拟栈，数据放在数组中

    //构造方法
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];//要创建stack对象，自己忘记了，运行失败
    }

    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int num){
        //判断栈是否满
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = num;
    }
    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，请添加~~~~");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历栈
    public void showSatck(){
        if (isEmpty()){
            throw new RuntimeException("栈空，请添加~~~~");
        }

        //这里要注意，在遍历栈的时候，不能改变top的值，要不然在遍历栈的同时，也在出栈
        for (int i =top;i > -1;i--) {
            System.out.println("stack["+i+"]="+stack[i]);
        }
    }
}