package com.ycz.data.structure.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description:观察者
 * @Author: hz18123767/ycz
 * @Date:2019/8/1
 */
public class UserObserver implements Observer {

    private String observerName;

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof UserObserver) {
            UserObserver userObserver = (UserObserver) arg;
            System.out.println("UserObserver,收到通知更新");
            System.out.println(userObserver.getObserverName());
        }
    }

    public String getObserverName() {
        return observerName;
    }

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }
}
