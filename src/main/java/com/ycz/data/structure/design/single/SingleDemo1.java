package com.ycz.data.structure.design.single;

/**
 * @Description:懒汉模式
 * @Author: hz18123767/ycz
 * @Date:2019/6/20
 */
public class SingleDemo1 {

    private static SingleDemo1 singleDemo = null;

    private SingleDemo1() {

    }

    public static synchronized SingleDemo1 getInstance() {
        if (singleDemo == null) {
            singleDemo = new SingleDemo1();
        }
        return singleDemo;
    }
}
