package com.beauty.exercise;

/**
 * @author Huangxulin
 * @date 2019/11/14 - 10:15
 * 使用栈完成计算器的功能(中缀表达式)
 */
public class Calculator {
    public static void main(String[] args) {
        //定义一个表达式，计算表达式的值
        String expression = "7*2*2-5+1-5*2+3-4"; //9-4*2+1这个表达式运算后还会出现一点问题，需要进行优化
        //创建两个栈，数栈和符号栈
        ArrayStackCalculator numStack = new ArrayStackCalculator(10);
        ArrayStackCalculator operStack = new ArrayStackCalculator(10);
        //定义相关的变量
        int num1,num2,oper,result;
        int index = 0;//索引,用于扫描
        char ch = ' ';//将每次扫描到的字符存放到ch中
        String keepNum = "";//用于拼接多位数
        //使用while循环扫描expression的每一个字符
        while (true){
            //一次获取expression中的每一个字符，substring()方法的作用为截取字符串
            //substring（int beginIndex）;这个的作用为截取从beginindex位置处的元素开始，默认截取至剩余所有。
            //substring（int beginIndex, int endIndex）;这个的作用为截取从beginIndex开始，截取至endIndex-1位置间的元素。
            //字符串调用charAt()方法，提取字符串中的某一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断得到的字符是否是运算符
            if (operStack.isOper(ch)){//判断是否为运算符
                if (!operStack.isEmpty()){//判断符号栈是否为空
                    //如果符号栈中有运算符，则需要判断当前的运算符的优先级是否小于等于栈中的运算符
                    //若果是则需要从数栈中pop两个数，再从符号栈中pop出一个符号，进行运算，等到的结果放入数栈，将当前的运算符放到符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.calculate(num1, num2, oper);
                        //所得到的结果加到数栈中
                        numStack.push(result);
                        if(!operStack.isEmpty()){
                            //当前的运算符加到符号栈中,在加到符号栈中的时，还要判断一下，这个运算符是否比符号栈中的栈顶符号的优先级更高
                            if (operStack.priority(ch) == operStack.priority(operStack.peek())) {
                                num1 = numStack.pop();
                                num2 = numStack.pop();
                                oper = operStack.pop();
                                result = numStack.calculate(num1, num2, oper);
                                //所得到的结果加到数栈中
                                numStack.push(result);
                            }
                        }
                        operStack.push(ch);
                    } else { //当前运算符优先级大于符号栈中栈顶的优先级，直接入栈
                        operStack.push(ch);
                    }

                }else {//符号栈为空直接入栈
                    operStack.push(ch);
                }
            }else { //为数字，则加入数栈
                //当为多位数时，不能立即入栈，需要扫描多位数后面一位数，与之进行拼接
                //多位数字直接拼接，此处的keepNum只是字符串，并不是数字，所以后面要进行转换
                keepNum += ch;

                //如果ch是expression的最后一位，直接入栈
                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }else {
                    //判断下一位字符是否是运算符，如果是，则对keepNum转换成数字
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        //清空keepNum，这里特别重要！！！！！！
                        keepNum = "";
                    }
                }
                //numStack.push(ch - 48);//这里要特别注意，因为扫描得到的数字不是真正的数字，而是一个字符，1=>'1',所以要减去48，ASCII表
            }
            index++;
            //退出循环
            if (index >= expression.length()){
                break;
            }
        }

        //当扫描结束后，就从数栈和符号栈中pop出相应的符号进行运算
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.calculate(num1,num2,oper);
            //所得到的结果加到数栈中
            numStack.push(result);
        }

        int value = numStack.pop();
        System.out.println("表达式 "+expression+"="+value);

    }
}

class ArrayStackCalculator{
    private int maxSize;//栈的大小
    private int top = -1; //定义栈顶，初始值为-1
    private int[] stack; //数组，使用数组模拟栈，数据放在数组中

    //构造方法
    public ArrayStackCalculator(int maxSize) {
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

    //查看栈的栈顶的符号，但不取出
    public int peek(){
        return stack[top];
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
    /**
     * 判断符号的优先级，这个由程序员来定义
     * @param oper 表示操作符，也可用char来声明，此处我们使用int
     * @return 返回字符的优先级，数字越大优先级越高
     */
    public int priority(int oper){
        if ( oper == '/' || oper == '*') {
            return 1;
        }else if( oper == '-' || oper == '+'){
            return 0;
        }else {
            return -1;
        }
    }

    /**
     * 判断是否是运算符
     * @param val 接收传过来的字符，进行判断是否的操作符
     * @return 返回值true或者false
     */
    public boolean isOper(char val){
        return val == '*' || val == '/' || val == '+' || val == '-';
    }

    //计算方法
    /**
     *
     * @param num1 pop出来的第一个数
     * @param num2 pop出来的第二个数
     * @param oper 运算符
     * @return 返回结果
     */
    public int calculate(int num1, int num2, int oper){
        int result = 0;//用于存放输出的结果
        switch(oper){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;//注意这里的顺序，因为这里使用了栈，应该可以理解
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}