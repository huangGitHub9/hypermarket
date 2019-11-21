package com.beauty.controller;

/**
 * @author Huangxulin
 * @date 2019/11/10 - 16:10
 * 合并两个有序的单链表，合并之后还是有序的
 */
public class twolinklisttest {
    public static void main(String[] args) {

        //添加对象节点
        People p1 = new People(1,"a",21);
        People p2 = new People(2,"b",22);
        People p3 = new People(3,"c",25);
        People p4 = new People(4,"d",20);
        People p5 = new People(5,"e",18);
        People p6 = new People(6,"f",21);
        People p7 = new People(7,"g",22);
        People p8 = new People(8,"h",25);

        TwoLinklist twoLinklist = new TwoLinklist();
        //链表1
//        twoLinklist.setOneHead(p1);
//        twoLinklist.setOneHead(p6);
        twoLinklist.setOneHead(p1);

        //链表2
        twoLinklist.setTwoHead(p5);
        twoLinklist.setTwoHead(p2);
        twoLinklist.setTwoHead(p3);
        twoLinklist.setTwoHead(p4);

        System.out.println("-------------链表1------------");
        twoLinklist.showOne();
        System.out.println("-------------链表2------------");
        twoLinklist.showTwo();

        //和并两个链表
        System.out.println("----------合并链表------------");
        OneAndTwo(twoLinklist.getOneHead(),twoLinklist.getTwoHead());

        twoLinklist.showOne();

    }

    //合并链表
    private static void OneAndTwo(People oneHead,People twoHead){
        People temp1 = oneHead;
        People temp2 = twoHead.next;
        People nextList2;
        if (temp1.next != null && temp2 != null){

            while (true){
                if (temp1.next == null){
                    temp1.next = temp2;
                    break;
                }
                if (temp2 == null){
                    break;
                }
                if (temp2.num < temp1.next.num){
                    nextList2 = temp2.next;
                    temp2.next = temp1.next;
                    temp1.next = temp2;
                    temp2 = nextList2;//指针后移
                }else{
                    temp1 = temp1.next;
                }

            }

        }else{
            System.out.println("有一个或两个链表无节点");
            return;
        }
    }
}

class TwoLinklist{
    private People twoHead = new People(0,"",0);
    private People oneHead = new People(0,"",0);

    public People getOneHead() {
        return oneHead;
    }

    public People getTwoHead() {
        return twoHead;
    }

    public void setOneHead(People head1) {

        boolean flag = false;
        People temp1 = oneHead;
        while (true){
            if (temp1.next == null){//判断链表是否为空
                break;
            }
            if (temp1.next.num > head1.num){
                break;
            }
            if (head1.num == temp1.next.num){
                flag = true;
                break;
            }
            temp1 = temp1.next;
        }
        if (flag){
            System.out.println("已有编号，请修改");
            return;
        }else {
            head1.next = temp1.next;
            temp1.next = head1;
        }
    }

    public void showOne(){
        if (oneHead.next == null){
            System.out.println("链表无节点");
            return;
        }
        People temp1 = oneHead.next;
        while (temp1 != null){
            System.out.println(temp1);
            temp1 = temp1.next;
        }
    }

    public void setTwoHead(People head) {
        People temp = twoHead;
        boolean flag = false;
        while (true){
            if (temp.next == null){//判断链表是否为空
                break;
            }
            if (head.num == temp.next.num){
                flag = true;
                break;
            }
            if (temp.next.num > head.num){
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.println("已有编号，请修改");
            return;
        }else {
            head.next = temp.next;
            temp.next = head;
        }
    }

    public void showTwo(){
        if (twoHead.next == null){
            System.out.println("链表为空");
            return;
        }
        People temp = twoHead.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class People{
    protected int num;
    protected String name;
    protected int age;
    public People next;


    public People(int num, String name, int age) {
        this.num = num;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}