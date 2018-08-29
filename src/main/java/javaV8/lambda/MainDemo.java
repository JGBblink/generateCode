package javaV8.lambda;

import javaV8.lambda.instance.DefindLambda;

import java.util.*;

/**
 * jdk8新特新入门示例
 *
 * @author JinGuiBo
 * @date 2018/8/16.
 */
public class MainDemo {

    public static void main(String[] args) {
       /* interfaceDefaultMethodDemo();

        lambdaDemo();

        defindLambdaDemo();*/

        new Thread(()->System.out.println("hello")).start();
    }

    /**
     * 子定义lambda示例
     */
    private static void defindLambdaDemo() {
        DefindLambda defindLambda = (a)-> a.toUpperCase();
        ;

        System.out.println(defindLambda.method("xxx"));
    }

    /**
     * lanmbda实现示例
     */
    private static void lambdaDemo() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        // 原始方式
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        System.out.println(names);

        // lambda实现方式1
        Collections.sort(names,(a,b)->{
            return b.compareTo(a);
        });
        System.out.println(names);

        // lambda实现方式2
        Collections.sort(names,(a,b)->a.compareTo(b));
        System.out.println(names);
    }

    /**
     * 接口默认方法示例
     */
    private static void interfaceDefaultMethodDemo() {
        DefaultMethod defaultMethod = new DefaultMethod() {
            @Override
            public int sum(int a, int b) {
                return a + b;
            }
        };

        System.out.println(defaultMethod.sum(2,9));
        System.out.println(defaultMethod.execute(10,5));
        System.out.println(defaultMethod.execute2(10,5));
    }
}
