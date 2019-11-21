package com.beauty.controller;

import java.util.Queue;
import java.util.Stack;

/**
 * @author Huangxulin
 * @date 2019/11/10 - 11:19
 * 进行栈的基础测试
 */
public class testStack {
    public static void main(String[] args) {
        //进行测试
        System.out.println("---------------------------测试逆序打印单链表--------------------------");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");

        SingleLinkList singleLinkList = new SingleLinkList();

        singleLinkList.add(hero1);
        singleLinkList.add(hero3);
        singleLinkList.add(hero2);
        singleLinkList.add(hero4);
        System.out.println("输出~~~~~~~~~");
        singleLinkList.show();
        System.out.println("倒叙输出~~~~~~~~~~");
        reserveStack(singleLinkList.getHead());
    }

    //倒叙输出链表
    //使用栈的特性：先进后出，输出链表
    protected static void reserveStack(HeroNode head){
        if (head.next == null){
            System.out.println("链表为空，无法倒叙");
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}
