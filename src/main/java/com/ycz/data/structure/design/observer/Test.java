package com.ycz.data.structure.design.observer;

/**
 * @Description:测试
 * @Author: hz18123767/ycz
 * @Date:2019/8/1
 */
public class Test {

    public static void main(String[] args) {
        ConcreteObservable observable = new ConcreteObservable();
        UserObserver userObserver = new UserObserver();
        userObserver.setObserverName("用户观察者得到通知了");
        OrderObserver orderObserver = new OrderObserver();
        observable.addObserver(userObserver);
        observable.addObserver(orderObserver);
        observable.updateMessage(userObserver);
    }
}
