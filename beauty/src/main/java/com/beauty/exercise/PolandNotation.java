package com.beauty.exercise;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Huangxulin
 * @date 2019/11/14 - 20:29
 * 逆波兰表达式计算器
 */
public class PolandNotation {
    public static void main(String[] args) {

        String infixExpression = "10+((2+4)*3)-5";//定义一个中缀表达式
        List<String> infixExpressionList = toInfixExpressionList(infixExpression);
        System.out.println("中缀表达式"+infixExpressionList);

        List<String> suffixExpressionList = parseSuffixExpression(infixExpressionList);
        System.out.println("后缀表达式"+suffixExpressionList);
        int result = calculate(suffixExpressionList);
        System.out.println("结果"+infixExpression+"="+result);
        //先定义逆波兰表达式
        // (3+4)*5-6 => 3 4 + 5 * 6 -
        //4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / +
        /*String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        List<String> strings = getListString(suffixExpression);
        System.out.println("逆波兰表达式="+strings);
        int result = calculate(strings);
        System.out.println(suffixExpression+"的结果是"+result);*/
    }

    //将逆波兰表达式，依次将数字和运算符放入ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression按照空格进行分割
        String[] split = suffixExpression.split(" ");
        List<String> strings = new ArrayList<>();
        //遍历split数组，添加到ArrayList中
        for (String str : split){
            strings.add(str);
        }
        return strings;
    }

    //将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String infixExpression){
        List<String> list = new ArrayList<>();
        char c = ' ';//扫面得到的一个字符串
        int i = 0;//扫描的索引
        String str = ""; //用于拼接数字，多位数
        do {
            //判断扫描到的是数字还是符号
            if ((c=infixExpression.charAt(i)) < 48 || (c=infixExpression.charAt(i)) > 57){//此时若为true，则表示字符为非数字
                list.add("" + c);
                i++;
            }else { //为数字
                str = "";//清空原来里面的数据
                while (i < infixExpression.length() && (c=infixExpression.charAt(i)) >= 48 && (c=infixExpression.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }while (i < infixExpression.length());
        return list;
    }

    //将中缀表达式的list转换成逆波兰表达式list
    public static List<String> parseSuffixExpression(List<String> infixExpression){
        //定义栈
        Stack<String> operStack = new Stack<>(); //符号栈
        //定义一个list
        List<String> list = new ArrayList<>();//储存中间结果的list
        //遍历中准表达式
        for (String str : infixExpression){
            if (str.matches("\\d+")){//正则表达式判断str是否是数字
                list.add(str);
            }else if (str.equals("(")){
                operStack.push(str);
            }else if (str.equals(")")){
                //如果是右括号")",一次弹出栈operStack栈顶的运算符，直到遇到左括号"(",然后弹出左括号
                while (!operStack.peek().equals("(")){
                    list.add(operStack.pop());
                }
                operStack.pop();//这个时候将小括号"("出栈，也就是消除它
            }else{
                //当str的优先级小于等于operStack栈顶的优先级，将栈中的运算符弹出加到list中，再与栈顶继续比较，直到栈为空或者优先级大于栈顶的优先级
                while (operStack.size() != 0 && Operation.getValue(str) <= Operation.getValue(operStack.peek())){
                    list.add(operStack.pop());
                }
                operStack.push(str);
            }
        }
        //将栈中所有的运算符依次加到list中
        while (operStack.size() != 0){
            list.add(operStack.pop());
        }
        return list;
    }

    //计算
    public static int calculate(List<String> strings){
        Stack<String> stack = new Stack<>();
        //遍历stack
        for (String stackItem : strings){
            //使用正则表达式，将数字加到栈中
            if (stackItem.matches("\\d+")){//匹配的是多位数
                stack.push(stackItem);
            }else {
                int num1 = Integer.parseInt(stack.pop());//pop出的第一个数
                int num2 = Integer.parseInt(stack.pop());//pop出的第二个数
                int result = 0;//定义计算结果
                switch(stackItem){
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num2 - num1;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num2 / num1;
                        break;
                    default:
                       throw  new RuntimeException("输入的运算符有误，请重新添加！");
                }
                stack.push("" + result);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//添加一个类Operation,返回运算符对应的优先级
class Operation{
    private static int ADD = 1; //加
    private static int SUB = 1; //减
    private static int MUL = 2; //乘
    private static int DIV = 2; //除

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation){
        int result = 0;
        switch(operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            case "(":
                break;
            default:
                System.out.println("运算符不正确！");
                break;
        }
        return result;
    }
}