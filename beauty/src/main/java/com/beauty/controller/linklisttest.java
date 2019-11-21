package com.beauty.controller;

/**
 * @author Huangxulin
 * @date 2019/11/10 - 10:38
 */
public class linklisttest {
    public static void main(String[] args) {
        //进行测试
        System.out.println("---------------------------链表测试反转--------------------------");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");


        SingleLinkList singleLinkList = new SingleLinkList();
        //按照编号添加英雄人物
        singleLinkList.addByOrder(hero1);
        singleLinkList.addByOrder(hero4);
        singleLinkList.addByOrder(hero3);
        singleLinkList.addByOrder(hero2);

        singleLinkList.show();

        //测试反转
        System.out.println("反转后~~~~~~~");
        reserveList(singleLinkList.getHead());
        singleLinkList.show();

    }

    //链表反转测试
    protected static void reserveList(HeroNode head){
        HeroNode cur = head.next;
        if (cur == null || cur.next == null){
            return;
        }
        //定义一个新的头节点
        HeroNode newHead = new HeroNode(0,"","");
        //定义一个新的指针（变量），避免出现以前链表中的cur.next节点无法查询
        HeroNode nextList;
        while (cur != null){
            nextList= cur.next;
            cur.next = newHead.next;
            newHead.next = cur;
            cur = nextList;
        }
        //之前的头节点head指向newHead.next
        head.next = newHead.next;
        newHead.next = null;
    }
}
