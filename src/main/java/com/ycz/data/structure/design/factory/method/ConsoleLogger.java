package com.ycz.data.structure.design.factory.method;

/**
 * @Description:控制台日志
 * @Author: hz18123767/ycz
 * @Date:2019/7/29
 */
public class ConsoleLogger implements Logger {

    @Override
    public void writeLog() {
        System.out.println("控制台日志输出");
    }
}
