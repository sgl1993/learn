package com.sgl.learn.jdk.jdk8.lambda;

import com.sgl.learn.jdk.jdk8.lambda.testinterface.FunctionInterface;
import com.sgl.learn.jdk.jdk8.lambda.testinterface.FunctionInterface1;
import com.sgl.learn.jdk.jdk8.lambda.testinterface.FunctionInterface2;
import com.sgl.learn.jdk.jdk8.lambda.testinterface.FunctionInterface3;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Description：Lambda表达式
 *
 * 一、是什么：
 * 在JDK8之前，Java是不支持函数式编程的，所谓的函数编程，即可理解是将一个函数（也称为“行为”）作为一个参数进行传递。
 * 通常我们提及得更多的是面向对象编程，面向对象编程是对数据的抽象（各种各样的POJO类），而函数式编程则是对行为的抽象（将行为作为一个参数进行传递）。
 * 在JavaScript中这是很常见的一个语法特性，但在Java中将一个函数作为参数传递这却行不通，好在JDK8的出现打破了Java的这一限制。
 *
 * 二、应用场景：能够接收Lambda表达式的参数类型，是一个只包含一个方法的接口。只包含一个方法的接口称之为"函数接口", eg：Runnable接口只包含一个方法，所以它被称为“函数接口”
 *
 * 三、怎么做：
 * 1.语法风格：(parameters) -> expression 或 (parameters) ->{ statements;}
 *
 * 2.重要特征：
 *      eg:
 *      interface MathOperation {
 *       int operation(int a, int b);
 *    }
 *
 *  > 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。    eg:(a, b) -> a - b;
 *  > 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。    eg:a -> a-3;
 *  > 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。 eg:(int a, int b) -> a / b;
 *  > 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。 eg:(int a, int b) -> a / b; (int a, int b) -> {int x = a-b ;return x;}
 *
 * 3.变量作用域：lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。
 *
 * @author shaoguoli
 * @date 10:35 2018/6/29
 */
public class LambdaDemo {

    private void func(FunctionInterface functionInterface) {
        functionInterface.test();
    }

    private void func(FunctionInterface1 functionInterface1) {
        functionInterface1.test(13);
    }

    private void func(FunctionInterface2<Integer> functionInterface2){
        functionInterface2.test(13);
    }

    private void func(FunctionInterface3<String> functionInterface3){
        functionInterface3.test("shaoguoli_fdfed");
    }

    //泛型有参，有返回值
    @Test
    public void testFunctionInterface3() {
        func((String x) -> {int a = 1; int b = 2; System.out.println(a==b); return a == b;});
    }

    // 泛型有参，无返回值
    @Test
    public void testfunctionInterface2() {
        func((Integer x) -> System.out.println(x));
    }

    // 有参，无返回值
    @Test
    public void testFunctionInterface1() {
        func(new FunctionInterface1() {
            @Override
            public void test(int param) {
                System.out.println("print test normal param:" + param);
            }
        });

        func((int x) -> System.out.println("print test lambda param:" + x));
    }

    @Test
    public void testFunctionInterface() {
        func(new FunctionInterface() {
            @Override
            public void test() {
                System.out.println("print test normal");
            }
        });

        func(() -> System.out.println("print test lambda"));
    }

    public static void main(String[] args) {
        // part1 start---------------------

        //定义字符串数组
        String[] strArr = {"abc", "cd", "abce", "a"};

        //传统方法
        //匿名内部类
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o2.length(), o1.length());
            }
        });

        for (String str : strArr) {
            System.out.println(str);
        }

        System.out.println("----------------------------------------");

        // lambda表达式
        Arrays.sort(strArr, (o1, o2) -> Integer.compare(o2.length(), o1.length()));

        for (String str : strArr) {
            System.out.println(str);
        }
        // part1 end---------------------
    }
}
