package com.ycz.data.structure.design.factory.simple;

/**
 * @Description:简单工厂
 * @Author: hz18123767/ycz
 * @Date:2019/7/5
 */
public class SimpleFactory {

    /**
     * 获取对象工厂
     *
     * @param type
     * @return
     */
    public static Product createProduct(String type) {
        Product product = null;
        if ("A".equals(type)) {
            product = new ConcreteProductA();
        } else {
            product = new ConcreteProductB();
        }
        return product;
    }

}
