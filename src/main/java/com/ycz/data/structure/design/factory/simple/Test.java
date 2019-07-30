package com.ycz.data.structure.design.factory.simple;

/**
 * @Description:简单工厂类测试
 * @Author: hz18123767/ycz
 * @Date:2019/7/29
 */
public class Test {

    public static void main(String[] args) {
        Product product=SimpleFactory.createProduct("A");
        product.display();
    }

}
