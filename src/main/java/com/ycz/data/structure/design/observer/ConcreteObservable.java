package com.ycz.data.structure.design.observer;

import java.util.Observable;

/**
 * @Description:具体被观察者
 * @Author: hz18123767/ycz
 * @Date:2019/8/1
 */
public class ConcreteObservable extends Observable {

    public void updateMessage(Object obj) {
        System.out.println("----更新消息------");
        super.setChanged();
        notifyObservers(obj);
    }
}
