package com.beauty.controller;

/**
 * @author Huangxulin
 * @date 2019/11/11 - 15:47
 */
public class josephu {
    public static void main(String[] args) {

        CricleSingLinkList cricleSingLinkList = new CricleSingLinkList();
        cricleSingLinkList.addBoy(125);//选择添加小孩的数量
        //显示所有的小孩
        cricleSingLinkList.showBoy();
        //小孩出圈的顺序
        cricleSingLinkList.countBoy(10,20,125);
    }

}

class CricleSingLinkList{

    Boy first;//单项环形链表，指定的第一个节点
    Boy boy;//辅助节点，添加进来的节点（小孩）
    Boy curBoy;//相当于在环形链表中的最后一个节点
    public void addBoy(int nums){

        //小孩数量不能小于2，要不然无法玩
        if (nums < 2) {
            System.out.println("小孩数量太少无法进行游戏");
            return;
        }
        for (int i = 1;i <= nums;i++){
            if (i == 1){
                first = new Boy(i);
                first.setNext(first);
                curBoy = first;
            }else {
                boy = new Boy(i);
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
        boy = null;
        curBoy = first;
    }

    public void showBoy(){
        if (first == null){
            System.out.println("链表中无节点");
            return;
        }

        while (true){
            System.out.println("小孩的编号："+curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     *
     * @param startNo  从第几位开始数
     * @param countNum  数几下出圈
     * @param nums  总共又多少人
     *              特别说明，因为first和curBoy是全局变量，所以在运行countBoy（）时，他们两个变量是有值的
     */
    public void countBoy(int startNo, int countNum, int nums){
        if (startNo < 1 || startNo > nums || countNum < 1){
            System.out.println("数据不合理，请重新输入");
            return;
        }

        //调整辅助指针至从第几位开始数
        for (int i = 0; i < startNo -1;i++){
            first = first.getNext();
            curBoy = curBoy.getNext();
        }

        while (true){
            if (first == curBoy){
                break;
            }
            for (int i = 0; i < countNum - 1; i++){
                first = first.getNext();
                curBoy = curBoy.getNext();
            }
            System.out.println("出来小孩的编号："+first.getNo());
            first = first.getNext();
            curBoy.setNext(first);
        }
        System.out.println("最后一个小孩的编号："+first.getNo());
    }
}

class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}