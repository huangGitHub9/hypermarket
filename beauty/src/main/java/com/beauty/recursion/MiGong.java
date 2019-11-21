package com.beauty.recursion;

/**
 * @author Huangxulin
 * @date 2019/11/16 - 16:41
 *迷宫问题
 */
public class MiGong {
    public static void main(String[] args) {
        //创建一个迷宫，8行7列
        int[][] map = new int[8][7];
        //设置挡板为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 1; i < 7; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[1][3] = 1;
        map[4][3] = 1;
        //map[2][3] = 1;
        //遍历二维数组
        System.out.println("二维数组迷宫");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("二维数组迷宫解法");
        setWay(map,1,1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param map 表示地图
     * @param i  二维数组的横坐标
     * @param j 二维数组的纵坐标
     * @return 如果找到通路就返回 true
     * 设置小球的开始位置(1,1)和出口位置(6,5)
     * 当map[i][j]为1时代表遇到墙，证明不能从这走，2代表已经走过，3代表是死路无法走，0代表该点可以走
     * 设定小球的走法优先级   右>下>左上
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[4][1] == 2){
            return true;
        }else {
            if (map[i][j] == 0){
                map[i][j] = 2;//设定小球的初始位置就是一个正确的点
                if (setWay(map,i,j+1)){  //试探向右走
                    return true;
                }else if(setWay(map,i+1,j)){ //向右走不行，试探向下走
                    return true;
                }else if(setWay(map,i,j-1)){ //向下走也不行，试探向左走
                    return true;
                }else if (setWay(map,i-1,j)){ //向左走还不行，试探向上走
                    return true;
                }else{ //为何会进到此处，就是此时的map[i][j]无论怎么走都不等于0，即被返回false
                    map[i][j] = 3;  //将该点设为死路，即这点无法到达粗口点
                    return false;
                }
            }else{
                return false;//表示该点已经被走过或者是死路，该点为1或者为2或者为3
            }
        }
    }
}
