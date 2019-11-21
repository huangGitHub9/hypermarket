package com.beauty.recursion;

/**
 * @author Huangxulin
 * @date 2019/11/21 - 13:40
 * 数字循环输出
 * 1	2	3	4	5
 * 16	17	18	19	6
 * 15	24	25	20	7
 * 14	23	22	21	8
 * 13	12	11	10	9
 */
public class numberAdd {

    static int count = 0; //定义当前的数组的个数
    static int direction = 1; //定义方向，1 为右走，2 为下走， 3 为左走， 4 为上走

    public static void main(String[] args) {
        int[][] arr = new int[5][5];

        numberadd(arr, 0, 0);

        //遍历数组
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * @param arr 数组
     * @param i   数组开始的位置
     * @param j   数组开始的位置
     * @return boolean返回值
     */
    public static boolean numberadd(int[][] arr, int i, int j) {

        if (count == 25) {
            return true;
        } else {
            if (i <= 4 && i >= 0 && j <= 4 && j >= 0 && arr[i][j] == 0) {
                count++;
                arr[i][j] = count;
                if (direction == 1 && numberadd(arr, i, j + 1)) {
                    return true;
                }
                if (direction == 2 && numberadd(arr, i + 1, j)) {
                    return true;
                }
                if (direction == 3 && numberadd(arr, i, j - 1)) {
                    return true;
                }
                if (direction == 4 && numberadd(arr, i - 1, j)) {
                    return true;
                } else {
                    numberadd(arr, i, j + 1);
                    return true;
                }

            } else {
                if (direction == 4) {
                    direction = 1;
                } else {
                    direction++;
                }
                return false;
            }
        }
    }
}
