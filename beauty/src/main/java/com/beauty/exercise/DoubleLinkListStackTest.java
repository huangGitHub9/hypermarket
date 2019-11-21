package com.beauty.exercise;

/**
 * @author Huangxulin
 * @date 2019/11/12 - 15:17
 * 使用链表实现栈的演示
 */
public class DoubleLinkListStackTest {
    public static void main(String[] args) {
        DoubleLinkListStack stack = new DoubleLinkListStack(4);

        //入栈
        Boy boy1 = new Boy(1,"huangxulin");
        Boy boy2 = new Boy(2,"huangxulin");
        Boy boy3 = new Boy(3,"huangxulin");
        Boy boy4 = new Boy(4,"huangxulin");
        Boy boy5 = new Boy(5,"huangxulin");
        stack.push(boy1);
        stack.push(boy2);
        stack.push(boy3);
        stack.push(boy4);
        stack.show();
        System.out.println("出栈");
        try {
            stack.pop();
            stack.pop();
            stack.pop();
            stack.pop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class DoubleLinkListStack{
    private int maxSize;
    private int size;//定义当前栈的大小
    private Boy head = new Boy(0,"");
    private Boy helper = head;
    private Boy popHelper = head;
    public DoubleLinkListStack(int maxSize) {
        this.maxSize = maxSize;
    }


    //入栈
    public void push(Boy boy) {
        if (size > maxSize - 1) {
            System.out.println("栈满了");
            return;
        } else {
            helper.setNext(boy);
            if (size > 0) {
                boy.setPre(helper);
            }
            helper = helper.getNext();
            popHelper = popHelper.getNext();
            size++;
        }
    }

    //出栈
    public void pop(){
        if (popHelper == null){
            throw new RuntimeException("栈为空~");
        }
        System.out.println("出栈："+popHelper);
        popHelper = popHelper.getPre();
    }

    //遍历栈
    public void show(){
        if (size==0){
            System.out.println("栈为空");
            return;
        }
        while (true){
            System.out.println(helper);
            if (helper.getPre() == null){
                break;
            }
            helper = helper.getPre();
        }
    }
}

class Boy{
    private Boy pre;
    private int age;
    private String name;
    private Boy next;

    public Boy(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Boy getPre() {
        return pre;
    }

    public void setPre(Boy pre) {
        this.pre = pre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}