package com.ycz.data.structure.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description:观察者
 * @Author: hz18123767/ycz
 * @Date:2019/8/1
 */
public class OrderObserver implements Observer {


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("通知多少用户：" + o.countObservers());
        if (arg instanceof OrderObserver) {
            System.out.println("OrderObserver,收到通知更新");
        }
    }

}
