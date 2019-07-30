package com.ycz.data.structure.design.single;

/**
 * 枚举类单例模式
 */
public enum SingleDemo3 {
    instance;

    public SingleDemo3 getInstance() {
        return instance;
    }
}
