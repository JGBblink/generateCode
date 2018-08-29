package javaV8.lambda.instance;

/**
 * lambda接口定义
 * @FunctionalInterface : 保证此接口只有唯一的方法
 * @author JinGuiBo
 * @date 2018/8/16.
 */
@FunctionalInterface
public interface DefindLambda {

    String method(String s);
}
