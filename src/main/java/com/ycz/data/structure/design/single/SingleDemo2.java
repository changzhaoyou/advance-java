package com.ycz.data.structure.design.single;

/**
 * @Description:双检锁模式
 * @Author: hz18123767/ycz
 * @Date:2019/6/20
 */
public class SingleDemo2 {
    /**
     * 注意使用volatile
     */
    private volatile static SingleDemo2 singleDemo = null;

    private SingleDemo2() {

    }

    public static SingleDemo2 getInstance() {
        if (singleDemo == null) {
            synchronized (SingleDemo2.class) {
                if (singleDemo == null) {
                    singleDemo = new SingleDemo2();
                }
            }
        }
        return singleDemo;
    }
}
