package javaV8.lambda;

/**
 * 接口默认方法测试
 *  使用default关键字
 *  可以有多个默认实现方法
 *
 *
 * @author JinGuiBo
 * @date 2018/8/16.
 */
public interface DefaultMethod {

    int sum(int a,int b);

    default int execute(int a,int b) {
        return a * b;
    }

    default int execute2(int a,int b) {
        return a * b;
    }
}
