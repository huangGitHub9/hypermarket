package com.beauty.controller;

/**
 * @author Huangxulin
 * @date 2019/11/9 - 11:11
 */
public class linklist {
    public static void main(String[] args) {

        //进行测试
        System.out.println("---------------------------链表测试--------------------------");
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkList singleLinkList = new SingleLinkList();

//        singleLinkList.add(hero4);
//        singleLinkList.add(hero1);
//        singleLinkList.add(hero2);
//        singleLinkList.add(hero3);

        singleLinkList.addByOrder(hero4);
        singleLinkList.addByOrder(hero1);
        singleLinkList.addByOrder(hero3);
        singleLinkList.addByOrder(hero2);
        //遍历链表
        singleLinkList.show();

        //修改之后的数据
        HeroNode hero5 = new HeroNode(4, "林冲11", "豹子头11");
        System.out.println("修改后~~~~~~");
        singleLinkList.update(hero5);
        //遍历链表
        singleLinkList.show();

        //删除节点
        System.out.println("删除后~~~~~~");
        singleLinkList.delete(1);
//        singleLinkList.delete(3);
//        singleLinkList.delete(2);
//        singleLinkList.delete(4);
        singleLinkList.show();

        //查询节点
//        System.out.println("--------查询节点-----------");
//        singleLinkList.select(5);

        //求链表有效节点的个数
        //通过头节点查询链表的个数
        System.out.println("有效节点个数："+getLength(singleLinkList.getHead()));

        //查找单链表中的倒数第k个结点 【新浪面试题】
        System.out.println("--------查找单链表中的倒数第k个结点--------");
        HeroNode result = findLastIndexNode(singleLinkList.getHead(),3);
        System.out.println("result="+result);
    }
    //查找单链表中的倒数第k个结点 【新浪面试题】
    private static HeroNode findLastIndexNode(HeroNode head,int index){
        HeroNode temp = head.next;
        int size = getLength(head);
        if (temp == null || index <=0 || index > size){
            return null;
        }
        for (int i = 0;i < size - index;i++){
            temp = temp.next;
        }
        return temp;
    }

    /**
     *
     * @param head 链表头节点
     * @return 返回有效节点个数
     */
    //求链表有效节点的个数
    static int getLength(HeroNode head){
        HeroNode temp = head;
        if (temp.next == null){
            return 0;
        }
        int length = 0;
        while (temp.next != null){
            length++;
            temp = temp.next;
        }
        return length;
    }
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
//定义一个SingleLinkList管理英雄
class SingleLinkList{
    //先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next 指向 新的节点
    protected void add(HeroNode heroNode){

        //因为head节点不能动，因此我们需要一个辅助变量 temp
        HeroNode temp = head;
        //遍历链表，找到最后的节点
        while (true){
            //找到最后的节点
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //当循环结束时，temp指向链表最后一个节点（next为空）
        //将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    //根据序号添加
    public void addByOrder(HeroNode heroNode){
        //定义指针
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){//判断链表是否为空
                break;
            }
            if (temp.next.no > heroNode.no){//判断当前指针的next编号是否大于添加节点的编号
                break;
            }else if(temp.next.no == heroNode.no){//判断当前指针的next编号是否等于添加节点的编号
                flag = true;
                break;
            }
            //指针后移
            temp = temp.next;
        }
        if (flag){
            System.out.printf("添加的编号%d重复，请从新添加",heroNode.no);
            System.out.println();
            return;
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点信息
    protected void update(HeroNode newHeroNode){
        HeroNode temp = head.next;
        if (temp == null){
            System.out.println("链表为空,请先添加节点");
            return;
        }
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            System.out.println("未找到编号%d"+newHeroNode.no);
        }
    }

    //删除节点
    protected void delete(int no){
        HeroNode temp = head;
        if (temp.next == null){
            System.out.println("链表为空，请先添加节点~~~~");
            return;
        }
        //boolean flag = false;
        while (true){
            if (temp.next == null){//此时已近遍历到最后一个节点，并不是链表为空
                System.out.printf("未找到需要删除的节点编号%d\n",no);
                break;
            }
            if (temp.next.no == no){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

    //查询节点
    protected  void  select(int no){
        HeroNode temp = head.next;
        if (temp == null) {
            System.out.println("链表为空，请先添加节点~~~~");
            return;
        }
        while (true){
            if (temp == null) {
                System.out.printf("未找到查询的节点%d\n",no);
                break;
            }
            if (temp.no == no){
                System.out.println(temp.toString());
                break;
            }
            temp = temp.next;
        }
    }

    //显示链表(遍历)
    protected void show(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空,请添加节点！");
            return;
        }
        //辅助变量
        HeroNode temp = head.next;
        while (true){
            //判断temp是否知道最后节点
            if (temp == null){
                break;
            }
            //遍历输出
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}


/*----------------------------------------------------------------------------------------------------------------------------------------*/
//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode{
    protected int no;
    protected String name;
    protected String nickname;
    protected HeroNode next; //指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}